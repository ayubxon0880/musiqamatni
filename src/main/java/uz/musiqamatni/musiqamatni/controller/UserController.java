package uz.musiqamatni.musiqamatni.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.musiqamatni.musiqamatni.dto.UserDTO;
import uz.musiqamatni.musiqamatni.dto.UserUpdateDTO;
import uz.musiqamatni.musiqamatni.mapper.UserMapper;
import uz.musiqamatni.musiqamatni.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/getMe")
    public ResponseEntity<UserDTO> getMe() {
        return userService.getMe();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id , @RequestBody UserUpdateDTO userDTO) {
        return userService.updateUser(id,userDTO);
    }
}
