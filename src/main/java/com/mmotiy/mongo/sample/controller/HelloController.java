package com.mmotiy.mongo.sample.controller;

import com.mmotiy.mongo.sample.entity.HelloWorld;
import com.mmotiy.mongo.sample.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private HelloRepository helloRepository;

    @GetMapping("world/{email}")
    public HelloWorld hello(@PathVariable String email){
        return helloRepository.save(new HelloWorld(email));
    }
    @GetMapping("/email/{email}")
    public HelloWorld email(@PathVariable String email){
        return helloRepository.findTopByEmailEquals(email);
    }
    @GetMapping("all")
    public List<HelloWorld> findAll(){
        return helloRepository.findAll();
    }

    @GetMapping("page")
    public Page<HelloWorld> page(@RequestParam Integer p,@RequestParam Integer s){
        PageRequest of = PageRequest.of(p, s);
        return helloRepository.findAll(of);
    }
}
