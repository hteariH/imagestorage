package com.mamoru.imagestorage.service;

import com.mamoru.imagestorage.dto.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    File store(MultipartFile file);

    Stream<Path> loadAll();

    File load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
