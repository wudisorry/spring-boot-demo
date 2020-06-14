package com.arh.springbootdemo.mongo.controller;

import com.arh.springbootdemo.mongo.dao.TestColDao;
import com.arh.springbootdemo.mongo.entity.TestCol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private TestColDao testColDao;

    @RequestMapping("/addTestCol")
    public void addTestCol(@RequestParam(name = "name") String name) {
        TestCol testCol = new TestCol();
        testCol.setName(name);
        testCol.setInfo("hahah hello mongo");
        testCol.setTags(Arrays.asList("aa", "bb", "cc"));
        testCol.setAddUser("diva");
        testCol.setAddDate(new Date());
        testColDao.addTestCol(testCol);
    }

    @RequestMapping("/updateTestCol")
    public void updateTestCol(@RequestParam(name = "name") String name, @RequestParam(name = "info") String info) {
        TestCol testCol = new TestCol();
        testCol.setName(name);
        testCol.setInfo(info);
        testColDao.updateTestCol(testCol);
    }

    @RequestMapping("/deleteTestCol")
    public void deleteTestCol(@RequestParam(name = "name") String name) {
        testColDao.deleteTestColByName(name);
    }

}
