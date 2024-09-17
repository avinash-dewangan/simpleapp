package com.avi.in.simpleapp.dms.controller;

import com.avi.in.simpleapp.dms.entity.DocumentVersion;
import com.avi.in.simpleapp.dms.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // Upload document as MultipartFile
    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            documentService.storeFile(file);
            return ResponseEntity.ok("File uploaded successfully with versioning.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file.");
        }
    }

    // Upload document as Base64 encoded string
    @PostMapping("/upload/base64")
    public ResponseEntity<String> uploadDocumentBase64(
            @RequestParam("base64") String base64Content,
            @RequestParam("fileName") String fileName,
            @RequestParam("contentType") String contentType) {
        try {
            documentService.storeFileBase64(base64Content, fileName, contentType);
            return ResponseEntity.ok("Base64 encoded file uploaded successfully with versioning.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload Base64 file.");
        }
    }

    // List all versions of a document
    @GetMapping("/{documentId}/versions")
    public ResponseEntity<List<DocumentVersion>> listVersions(@PathVariable Long documentId) {
        return ResponseEntity.ok(documentService.listVersions(documentId));
    }

    // Download specific version
    @GetMapping("/download/{documentId}/version/{version}")
    public ResponseEntity<Resource> downloadDocumentVersion(@PathVariable Long documentId, @PathVariable int version) {
        try {
            Path file = documentService.getVersionedFile(documentId, version);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName().toString() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Delete specific version
    @DeleteMapping("/delete/{documentId}/version/{version}")
    public ResponseEntity<String> deleteDocumentVersion(@PathVariable Long documentId, @PathVariable int version) {
        try {
            documentService.deleteVersion(documentId, version);
            return ResponseEntity.ok("File version deleted successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to delete file version.");
        }
    }
    // Rollback a document to a specific version
    @PostMapping("/{documentId}/rollback/{version}")
    public ResponseEntity<String> rollbackDocumentToVersion(@PathVariable Long documentId, @PathVariable int version) {
        try {
            documentService.rollbackDocumentToVersion(documentId, version);
            return ResponseEntity.ok("Document rolled back to version " + version + " successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to rollback document: " + e.getMessage());
        }
    }
    // List all documents
    @GetMapping
    public ResponseEntity<List<String>> listDocuments() {
        try {
            return ResponseEntity.ok(documentService.listAllFiles());
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
