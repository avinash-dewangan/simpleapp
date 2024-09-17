package com.avi.in.simpleapp.dms.repository;


import com.avi.in.simpleapp.dms.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByName(String name);
}
