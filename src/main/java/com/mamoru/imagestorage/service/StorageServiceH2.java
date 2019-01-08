package com.mamoru.imagestorage.service;


import com.mamoru.imagestorage.dto.File;
import com.mamoru.imagestorage.repository.ImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class StorageServiceH2 implements StorageService{


    private ImageRepository imageRepository;

    public StorageServiceH2(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void init() {

    }

    public File store(MultipartFile file) {
        try {

            String[] str = file.getOriginalFilename().split("\\\\");
            String name = str[str.length-1];
            System.out.println("name" + name);

            byte[] bytes = file.getBytes();
            File file1 = new File();
            file1.setName(name);
            file1.setFile(bytes);
            file1.setContentType(file.getContentType());
            File file2 = imageRepository.save(file1);
            System.out.println("file id="+file2.getId());
            return file1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public File load(String filename) {
        return imageRepository.findFirstByName(filename).get(0);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
