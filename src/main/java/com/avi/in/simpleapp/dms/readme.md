# DMS (Document Managment System)
# -------
A Document Management System (DMS) in Java can be built to manage, store, and retrieve documents, track versions, and ensure security for document handling. Below is a simple example that demonstrates how to build a basic DMS with the following features:

* Upload Document: Store files in a specific directory.
* Download Document: Retrieve stored files.
* List All Documents: View available documents.
* Delete Document: Remove files.
* Versioning: Manage file versions.

This basic implementation will store the files locally using the filesystem.


## Running the Application
* Run: You can run this Spring Boot application using mvn spring-boot:run or directly from your IDE.
* API Testing: Use Postman or curl to test the REST API:
* Upload: POST http://localhost:8080/api/documents/upload with a file.
* List all documents: GET http://localhost:8080/api/documents
* Download a file: GET http://localhost:8080/api/documents/download/{fileName}
* Delete a file: DELETE http://localhost:8080/api/documents/delete/{fileName}
## Possible Enhancements
Database storage for documents.
User authentication and roles to control access to documents.
Version control for uploaded documents.
Search functionality for easy document retrieval.

## Key Changes for Versioning:
* Versioning: When uploading a new file with the same name, a version number is incremented, and the file is stored as filename_v1.ext, filename_v2.ext, etc.
* Document Version Entity: The DocumentVersion entity tracks metadata for each file version, such as version number, file path, and upload time.
* API Changes: New endpoints allow users to list all versions of a document, download a specific version, and delete specific versions.

## How to Test:
* Upload Multiple Versions: Upload the same file multiple times, and check how versioning is handled.
* Download Specific Version: Use the /download/{documentId}/version/{version} endpoint to download a specific version of a file.
* List Versions: Use /versions to list all versions of a document.
* Delete Version: Use /delete/{documentId}/version/{version} to remove a specific file version.

## Explanation:
* @Transactional(rollbackFor = Exception.class): This ensures that any method annotated with @Transactional will automatically roll back all operations if an exception (either checked or unchecked) occurs. The rollbackFor attribute indicates that even for checked exceptions (like IOException), the transaction should be rolled back.
* Exception Handling: If any error occurs during the file storage or rollback process (like an I/O error, database failure, etc.), the transaction will be marked for rollback. This prevents partial updates to the database or file system.
##Key Points:
* File System and Database Consistency: Since both the database (for metadata) and file system (for actual file content) are involved in the transaction, Spring will ensure consistency between them. If the file write operation fails, any database changes will also be rolled back.
* Transaction Rollback: If any part of the method fails, all changes made within the transaction will be undone. For example, if file write fails, the document metadata won't be saved.
## Testing Rollback:
* Upload Scenario: Simulate an exception while uploading a file (e.g., disk space issues or file system permission problems) and ensure that the transaction is rolled back, meaning no partial file or metadata is saved.

* Rollback Scenario: Try rolling back to a previous version, and in case of an exception (e.g., the version doesn’t exist), ensure no partial rollback occurs.

