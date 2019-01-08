package com.mamoru.imagestorage.repository;

import com.mamoru.imagestorage.dto.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<File, Long> {
        public List<File> findFirstByName(String file);
        }
