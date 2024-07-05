package uz.musiqamatni.musiqamatni.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.musiqamatni.musiqamatni.dto.request.AuthenticationRequest;
import uz.musiqamatni.musiqamatni.dto.request.RegisterRequest;
import uz.musiqamatni.musiqamatni.dto.response.ApiResponse;
import uz.musiqamatni.musiqamatni.dto.response.AuthenticationResponse;
import uz.musiqamatni.musiqamatni.dto.response.ResetPasswordResponse;
import uz.musiqamatni.musiqamatni.groups.OnCreate;
import uz.musiqamatni.musiqamatni.service.AuthenticationService;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    @Validated(OnCreate.class)
    public ResponseEntity<ApiResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest,
            HttpServletRequest request
    ){
        return service.register(registerRequest,request);
    }


    @GetMapping("/register/confirm-email")
    public ResponseEntity<?> confirmEmail(@RequestParam String code,@RequestParam Long user) {
        return service.confirmEmail(code,user);
    }

    @PostMapping("/login")
    @Validated
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest registerRequest
    ) {
        return service.authenticate(registerRequest);
    }

    @PostMapping("/forget-password/{email}")
    public ResponseEntity<ApiResponse> forgotPassword(@PathVariable String email){
        return service.forgotPassword(email);
    }

    @GetMapping("/check-key/{key}/{email}")
    public ResponseEntity<ApiResponse> checkKey(@PathVariable String key,@PathVariable String email){
        return service.checkKey(key,email);
    }
    @PostMapping("/reset-password")
    @Validated
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ResetPasswordResponse passwordResponse){
        return service.resetPassword(passwordResponse);
    }
}
