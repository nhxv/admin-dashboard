package com.xv.admindashboard.controller;

import com.xv.admindashboard.config.JwtTokenUtil;
import com.xv.admindashboard.model.AdminLogin;
import com.xv.admindashboard.model.ApiResponse;
import com.xv.admindashboard.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ApiResponse<AuthToken> getToken(@RequestBody AdminLogin adminLogin) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        adminLogin.getUsername(),
                        adminLogin.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return new ApiResponse<>(200, "success", new AuthToken(token));
    }
}
