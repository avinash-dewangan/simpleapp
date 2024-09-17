package com.avi.in.simpleapp.dms.repository;


import com.avi.in.simpleapp.dms.entity.DocumentVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, Long> {
    List<DocumentVersion> findByDocumentId(Long documentId);
    Optional<DocumentVersion> findByVersionAndDocumentId(int version, Long documentId);
}
