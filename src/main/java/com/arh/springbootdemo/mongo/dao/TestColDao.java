package com.arh.springbootdemo.mongo.dao;

import com.arh.springbootdemo.mongo.entity.TestCol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;

@Repository
public class TestColDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addTestCol(TestCol testCol) {
        mongoTemplate.save(testCol);
    }

    public void updateTestCol(TestCol testCol) {
        Query query = Query.query(Criteria.where("name").is(testCol.getName()));
        Update update = Update.update("info", testCol.getInfo()).set("updateDate", new Date());
        mongoTemplate.updateFirst(query, update, TestCol.class);
    }

    public void deleteTestColByName(String name) {
        Query query = Query.query(Criteria.where("name").is(name));
        mongoTemplate.remove(query, TestCol.class);
    }
}
