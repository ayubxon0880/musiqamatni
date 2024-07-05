package uz.musiqamatni.musiqamatni.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.musiqamatni.musiqamatni.dto.UserDTO;
import uz.musiqamatni.musiqamatni.dto.UserUpdateDTO;
import uz.musiqamatni.musiqamatni.exceptions.NotFoundException;
import uz.musiqamatni.musiqamatni.mapper.UserMapper;
import uz.musiqamatni.musiqamatni.model.User;
import uz.musiqamatni.musiqamatni.repository.UserRepository;
import uz.musiqamatni.musiqamatni.service.UserService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public ResponseEntity<UserDTO> getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @Override
    public ResponseEntity<UserDTO> getMe() {
        UserDTO userDTO = userMapper.toDTO((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        if (Objects.equals(principal.getId(), id)) {
            if (userUpdateDTO.getFirstname() != null && userUpdateDTO.getFirstname().length() > 1){
                user.setFirstname(userUpdateDTO.getFirstname());
            }
            if (userUpdateDTO.getLastname() != null && userUpdateDTO.getLastname().length() > 1) {
                user.setLastname(userUpdateDTO.getLastname());
            }
            if (passwordEncoder.matches(userUpdateDTO.getOldPassword(), user.getPassword())){
                user.setPassword(passwordEncoder.encode(userUpdateDTO.getNewPassword()));
            }
            user = userRepository.save(user);
        }
        return ResponseEntity.ok(userMapper.toDTO(user));
    }
}
