package com.pederapido.pederapido.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
	
	@GetMapping(path = "/api/hello")
    public String getResp(){
        return  "Hey authenticated request!";
    }

}
