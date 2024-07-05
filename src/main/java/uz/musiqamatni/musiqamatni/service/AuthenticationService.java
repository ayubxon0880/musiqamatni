package uz.musiqamatni.musiqamatni.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import uz.musiqamatni.musiqamatni.dto.request.AuthenticationRequest;
import uz.musiqamatni.musiqamatni.dto.request.RegisterRequest;
import uz.musiqamatni.musiqamatni.dto.response.ApiResponse;
import uz.musiqamatni.musiqamatni.dto.response.AuthenticationResponse;
import uz.musiqamatni.musiqamatni.dto.response.ResetPasswordResponse;

public interface AuthenticationService {
    ResponseEntity<ApiResponse> register(RegisterRequest registerRequest, HttpServletRequest httpServletRequest);
    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest registerRequest);
    ResponseEntity<?> confirmEmail(String code,Long user);
    ResponseEntity<ApiResponse> forgotPassword(String email);
    ResponseEntity<ApiResponse> resetPassword(ResetPasswordResponse passwordResponse);
    ResponseEntity<ApiResponse> checkKey(String key,String email);
}
