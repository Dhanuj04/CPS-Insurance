package com.insurance.controller;




import com.insurance.dto.AuthRequest;
import com.insurance.dto.ForgotPasswordRequest;
import com.insurance.dto.UserIdResponse;
import com.insurance.entity.UserCredential;
import com.insurance.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            System.out.println("****************** Called by the caller");
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity validateToken(@RequestBody ForgotPasswordRequest fpr) {
        boolean isPassChanged = service.changePassword(fpr);
        if(!isPassChanged)return ResponseEntity.badRequest().build();
        return ResponseEntity.ok("password has been changed");
    }

    @GetMapping("/getuserid")
    public ResponseEntity getUserIdFromToken(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        Long userid = service.getUserIdFromToken(token);

        return  ResponseEntity.ok(new UserIdResponse(userid));
    }
}

