package com.mamoru.imagestorage.repository;

import com.mamoru.imagestorage.dto.File;

import java.util.List;

public interface MongoRepository {

    File saveFile(File file);

    List<File> findByName(String name);

    File find(String id);
}
