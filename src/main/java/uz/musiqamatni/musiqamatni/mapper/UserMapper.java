package uz.musiqamatni.musiqamatni.mapper;

import org.springframework.stereotype.Service;
import uz.musiqamatni.musiqamatni.dto.UserDTO;
import uz.musiqamatni.musiqamatni.model.User;

@Service
public class UserMapper {
    public UserDTO toDTO(User user) {
        if (user == null) return null;
        return new UserDTO(

        );
    }
}
