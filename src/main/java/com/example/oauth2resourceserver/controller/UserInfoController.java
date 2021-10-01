package com.example.oauth2resourceserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class UserInfoController {

    @GetMapping("/userinfo")
    public ResponseEntity<?> userInfo(Principal principal,
                                      HttpServletRequest request,
                                      @RequestParam HashMap<String, String> paramMap){
        return ResponseEntity.ok(principal);
    }

}
