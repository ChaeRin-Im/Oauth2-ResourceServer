package com.example.oauth2resourceserver.controller;

import com.example.oauth2resourceserver.dto.UserProfile;
import com.example.oauth2resourceserver.entity.User;
import com.example.oauth2resourceserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

// 참고 URL: https://www.skyer9.pe.kr/wordpress/?p=2500

//profile 정보 Custom
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserRepository userRepository;

    @GetMapping("/userinfo")
    public ResponseEntity<?> userInfo(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String name = auth.getName();
        UserProfile profile = new UserProfile(name);

        OAuth2Authentication oauth2;
        if(auth instanceof OAuth2Authentication){
            oauth2 = (OAuth2Authentication) auth;
            Set<String> scopes = oauth2.getOAuth2Request().getScope();
            System.out.println("scopes: " + scopes);

            Optional<User> user = userRepository.findByUid(name);
            System.out.println("user: " + user);

            if(scopes.contains("email")) profile.setEmail(user.get().getEmail());

            if(user.isPresent()) return ResponseEntity.ok(profile);
        }
        return null;
    }

    /*@GetMapping("/userinfo")
    public ResponseEntity<?> userInfo(Principal principal,
                                      HttpServletRequest request,
                                      @RequestParam HashMap<String, String> paramMap){
        return ResponseEntity.ok(principal);
    }*/

}
