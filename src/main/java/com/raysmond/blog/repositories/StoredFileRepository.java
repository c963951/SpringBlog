package com.raysmond.blog.repositories;

import com.raysmond.blog.models.StoredFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredFileRepository extends JpaRepository<StoredFile, Long> {
    public StoredFile findById(Long id);
}
