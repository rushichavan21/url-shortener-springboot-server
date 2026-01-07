package com.rushi.urlShortener.controllers;

import com.rushi.urlShortener.dto.request.LoginRequest;
import com.rushi.urlShortener.dto.request.SignupRequest;
import com.rushi.urlShortener.dto.response.AuthResponse;
import com.rushi.urlShortener.entity.User;
import com.rushi.urlShortener.services.UserDetailServiceImpl;
import com.rushi.urlShortener.services.UserService;
import com.rushi.urlShortener.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/health")
    public String health(){
        return "Backend Server is live";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        try {
            User user= new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            userService.saveAuthUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already in use");
        }
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){

            User user= new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail() , user.getPassword()));
            UserDetails userDetails=userDetailService.loadUserByUsername(user.getEmail());
            String jwt =  jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
