package com.mamoru.imagestorage.service;


import com.mamoru.imagestorage.dto.File;
import com.mamoru.imagestorage.repository.MongoRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class StorageServiceMongo implements StorageService{

    private GridFsTemplate gridFsTemplate;

    private MongoRepository mongoRepository;
//    @Autowired
//    public StorageServiceMongo(GridFsTemplate gridFsTemplate) {
//        this.gridFsTemplate = gridFsTemplate;
//    }


    public StorageServiceMongo(MongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
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
            File file2 = mongoRepository.saveFile(file1);
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
        return mongoRepository.findByName(filename).get(0);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
