package com.mmotiy.mongo.sample.repository;

import com.mmotiy.mongo.sample.entity.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

@Profile("mongodb")
@Repository
public class HelloRepositoryImpl implements HelloRepository
{

    private final MongoOperations operations;

    @Autowired
    public HelloRepositoryImpl(MongoOperations operations) {

        Assert.notNull(operations);
        this.operations = operations;
    }

    @Override
    public HelloWorld findTopByEmailEquals(String email) {
        Query query = query(where("email").is(email));
        return operations.findOne(query,HelloWorld.class);
    }

    @Override
    public HelloWorld save(HelloWorld world) {
        return  operations.save(world);
    }

    @Override
    public List<HelloWorld> findAll() {
        return operations.findAll(HelloWorld.class);
    }

    @Override
    public Page<HelloWorld> findAll(Pageable pageable) {
        Query query = new Query();
        query.with(pageable);
        List<HelloWorld> helloWorlds = operations.find(query, HelloWorld.class);
        long count = operations.count(query, HelloWorld.class);
        return new PageImpl(helloWorlds,pageable,count);
    }


}
