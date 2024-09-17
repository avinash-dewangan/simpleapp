package com.avi.in.simpleapp.dms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DocumentVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String versionedName; // e.g., "document_v1.pdf"
    private String path;           // Path where the file is stored
    private int version;           // Version number of the document
    private LocalDateTime uploadTime; // Timestamp when the file was uploaded

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
}
