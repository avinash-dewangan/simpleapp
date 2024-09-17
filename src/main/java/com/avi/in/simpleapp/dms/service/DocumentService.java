package com.avi.in.simpleapp.dms.service;

import com.avi.in.simpleapp.dms.entity.Document;
import com.avi.in.simpleapp.dms.entity.DocumentVersion;
import com.avi.in.simpleapp.dms.repository.DocumentRepository;
import com.avi.in.simpleapp.dms.repository.DocumentVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private static final String STORAGE_DIR = "documents/";

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentVersionRepository documentVersionRepository;

    // Method to store a file with versioning (MultipartFile)
    public Document storeFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        return saveDocument(file.getBytes(), fileName, file.getContentType());
    }

    // Method to store a file with versioning (Base64 encoded string)
    public Document storeFileBase64(String base64Content, String fileName, String contentType) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Content);
        return saveDocument(decodedBytes, fileName, contentType);
    }

    // Common method to save document (used by both MultipartFile and Base64 methods)
    // Store file with transaction management
    @Transactional(rollbackFor = Exception.class)
    public Document saveDocument(byte[] fileData, String fileName, String contentType) throws IOException {
        try {
            // Ensure the directory exists or create it
            Path storageDirPath = Paths.get(STORAGE_DIR);
            if (Files.notExists(storageDirPath)) {
                Files.createDirectories(storageDirPath);  // Create the directory if it doesn't exist
            }

            Optional<Document> existingDocument = documentRepository.findByName(fileName);
            Document document;
            int version;

            if (existingDocument.isPresent()) {
                document = existingDocument.get();
                version = document.getVersions().size() + 1; // Increment version
            } else {
                document = new Document();
                document.setName(fileName);
                version = 1;
            }

            // Create versioned filename, e.g., "file_v1.txt"
            String versionedFileName = fileName.replace(".", "_v" + version + ".");
            Path path = Paths.get(STORAGE_DIR + versionedFileName);
            Files.write(path, fileData);

            // Save version information
            DocumentVersion documentVersion = new DocumentVersion();
            documentVersion.setVersion(version);
            documentVersion.setVersionedName(versionedFileName);
            documentVersion.setPath(path.toString());
            documentVersion.setUploadTime(LocalDateTime.now());
            documentVersion.setDocument(document);

            document.getVersions().add(documentVersion);

            // Save the document and its versions
            documentRepository.save(document);
            documentVersionRepository.save(documentVersion);

            return document;
        } catch (IOException e) {
            throw new IOException("Failed to store file due to I/O error", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    // List all versions of a document
    public List<DocumentVersion> listVersions(Long documentId) {
        return documentVersionRepository.findByDocumentId(documentId);
    }

    // Download specific version
    public Path getVersionedFile(Long documentId, int version) throws IOException {
        Optional<DocumentVersion> documentVersion = documentVersionRepository.findByVersionAndDocumentId(version, documentId);
        if (documentVersion.isPresent()) {
            return Paths.get(documentVersion.get().getPath());
        } else {
            throw new IOException("Version not found.");
        }
    }

    // Delete specific version
    public void deleteVersion(Long documentId, int version) throws IOException {
        Optional<DocumentVersion> documentVersion = documentVersionRepository.findByVersionAndDocumentId(version, documentId);
        if (documentVersion.isPresent()) {
            Files.deleteIfExists(Paths.get(documentVersion.get().getPath()));
            documentVersionRepository.delete(documentVersion.get());
        } else {
            throw new IOException("Version not found.");
        }
    }

    // Rollback document to a previous version by creating a new version with the same content as the previous version
    // Rollback method, wrapped in a transaction
    @Transactional(rollbackFor = Exception.class)
    public Document rollbackDocumentToVersion(Long documentId, int versionToRollbackTo) throws IOException {

        try {
            // Find the document
            Optional<Document> documentOptional = documentRepository.findById(documentId);
            if (!documentOptional.isPresent()) {
                throw new IOException("Document not found.");
            }

            Document document = documentOptional.get();

            // Find the version to rollback to
            Optional<DocumentVersion> versionOptional = documentVersionRepository.findByVersionAndDocumentId(versionToRollbackTo, documentId);
            if (!versionOptional.isPresent()) {
                throw new IOException("Version not found.");
            }

            DocumentVersion versionToRollback = versionOptional.get();
            Path versionedFilePath = Paths.get(versionToRollback.getPath());

            // Read the content of the version to rollback to
            byte[] fileContent = Files.readAllBytes(versionedFilePath);

            // Create a new version with the same content as the selected version
            int newVersionNumber = document.getVersions().size() + 1;
            String newVersionedFileName = document.getName().replace(".", "_v" + newVersionNumber + ".");
            Path newVersionedFilePath = Paths.get(STORAGE_DIR + newVersionedFileName);

            // Save the new file content as a new version
            Files.write(newVersionedFilePath, fileContent);

            // Save the new version metadata in the database
            DocumentVersion newVersion = new DocumentVersion();
            newVersion.setVersion(newVersionNumber);
            newVersion.setVersionedName(newVersionedFileName);
            newVersion.setPath(newVersionedFilePath.toString());
            newVersion.setUploadTime(java.time.LocalDateTime.now());
            newVersion.setDocument(document);

            document.getVersions().add(newVersion);

            // Save the document and the new version
            documentRepository.save(document);
            documentVersionRepository.save(newVersion);

            return document;
        } catch (IOException e) {
            throw new IOException("Failed to rollback due to I/O error", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to rollback document", e);
        }
    }
    // List all documents (for demo, using file names)
    public List<String> listAllFiles() throws IOException {
        return Files.list(Paths.get(STORAGE_DIR))
                .map(Path::getFileName)
                .map(Path::toString)
                .toList();
    }

    // Method to download a file by name
    public Path getFile(String fileName) {
        return Paths.get(STORAGE_DIR).resolve(fileName);
    }
}
