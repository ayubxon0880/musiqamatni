package uz.musiqamatni.musiqamatni.service;

import org.springframework.http.ResponseEntity;
import uz.musiqamatni.musiqamatni.dto.UserDTO;
import uz.musiqamatni.musiqamatni.dto.UserUpdateDTO;

public interface UserService {
    ResponseEntity<UserDTO> getById(Long id);

    ResponseEntity<UserDTO> getMe();

    ResponseEntity<UserDTO> updateUser(Long id, UserUpdateDTO userUpdateDTO);
}
