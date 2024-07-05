package uz.musiqamatni.musiqamatni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private String firstname;
    private String lastname;
    private String oldPassword;
    private String newPassword;

}
