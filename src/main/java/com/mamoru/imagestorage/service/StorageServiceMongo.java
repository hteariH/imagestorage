package com.mamoru.imagestorage.service;


import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class StorageServiceMongo implements StorageService{

    private GridFsTemplate gridFsTemplate;

    @Autowired
    public StorageServiceMongo(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    @Override
    public void init() {

    }

    public void store(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String[] str = file.getOriginalFilename().split("\\\\");
            String name = str[str.length-1];
            String s = gridFsTemplate.store(inputStream, name, file.getContentType()).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public GridFsResource load(String filename) {
        GridFSFile filename1 = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(filename)));
        return gridFsTemplate.getResource(filename1);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
