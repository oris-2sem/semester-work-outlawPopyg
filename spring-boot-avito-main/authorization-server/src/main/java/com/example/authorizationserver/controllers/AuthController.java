package com.example.authorizationserver.controllers;

import com.example.authorizationserver.details.User;
import com.example.authorizationserver.details.UserDetailsImpl;
import com.example.authorizationserver.repositories.UserRepository;

import com.example.authorizationserver.services.RedisUserService;
import com.example.authorizationserver.services.TokenService;
import com.example.customstarter.services.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RedisUserService redisUserService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsServiceImpl;
    private final UserRepository userRepository;
    private final BlackListService blackListService;
    record LoginRequest(String username, String password) {}
    record TokensResponse(String access_token) {}
    record UserResponse(String email, String name) {}
    record UserSummary(UUID id, String email, String name) {};

    @PostMapping("/login")
    public TokensResponse login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(request.username, request.password);

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl user = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(request.username);
        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        redisUserService.addTokenToUser(user, accessToken);

        return new TokensResponse(accessToken);
    }

    @GetMapping("/users/me")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        try {
            String headerAuth = request.getHeader("Authorization");
            String accessToken = headerAuth.substring(7);

            String email = tokenService.parseToken(accessToken);

            UserDetailsImpl user = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(email);
            User currentUser = user.getUser();

            return ResponseEntity.ok(new UserSummary(currentUser.getId(), currentUser.getEmail(), currentUser.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/users/summaries")
    public ResponseEntity<?> findAllUserSummaries(HttpServletRequest request) {
        try {
            String headerAuth = request.getHeader("Authorization");
            String accessToken = headerAuth.substring(7);

            String email = tokenService.parseToken(accessToken);

            return ResponseEntity.ok(
                    userRepository
                            .findAll()
                            .stream()
                            .filter(user -> !user.getEmail().equals(email))
                            .map(user -> new UserSummary(user.getId(), user.getEmail(), user.getName()))
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/token/refresh")
    public ResponseEntity<TokensResponse> refresh(HttpServletRequest request) {
        try {
            String headerAuth = request.getHeader("Authorization");
            String refreshToken = headerAuth.substring(7, headerAuth.length());

            String email = tokenService.parseToken(refreshToken);
            UserDetailsImpl user = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(email);

            String access = tokenService.generateAccessToken(user);
            String refresh = tokenService.generateRefreshToken(user);

            return ResponseEntity.ok(new TokensResponse(access));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        blackListService.add(request.getHeader("Authorization"));
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok().build();
    }
}
