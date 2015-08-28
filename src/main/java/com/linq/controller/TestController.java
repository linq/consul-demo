package com.linq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author LinQ
 * @version 2015-08-27
 */
@RestController
public class TestController {
    @Autowired
    private Environment env;

    @RequestMapping(name = "/hello")
    public String hello() {
        return env.getProperty("usercenter.tag");
    }
}
