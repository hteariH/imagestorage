package com.mamoru.imagestorage.repository;

import com.mamoru.imagestorage.dto.File;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoRepositoryImpl implements MongoRepository {

    private final MongoTemplate mongoTemplate;

    public MongoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public File saveFile(File file) {
        return mongoTemplate.save(file);
    }

    @Override
    public List<File> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return mongoTemplate.find(query,File.class);
    }

    public File find(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, File.class);
    }
}
