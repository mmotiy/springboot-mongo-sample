package com.mmotiy.mongo.sample.repository;

import com.mmotiy.mongo.sample.entity.HelloWorld;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface HelloRepository extends Repository<HelloWorld,Long> {
    HelloWorld findTopByEmailEquals(String email);
    HelloWorld save(HelloWorld world);
    List<HelloWorld> findAll();
    Page<HelloWorld> findAll(Pageable pageable);

}
