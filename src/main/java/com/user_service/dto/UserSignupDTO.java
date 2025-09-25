package com.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDTO {
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(min = 5, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    @Pattern(regexp = "^[A-Za-zÀ-ú\\s'-]+$", message = "O nome deve conter apenas letras, espaços e hifens/apóstrofos.")
    private String name;

    @NotBlank(message = "O nome de usuário não pode estar em branco.")
    @Size(min = 5, max = 15, message = "O nome de usuário deve ter entre 3 e 50 caracteres.")
    private String userName;

    @NotBlank(message = "A senha não pode estar em branco.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    private String password;

    @Email(message = "O formato do e-mail é inválido.")//pode ser vazio, pois o email será por enquanto opcional
    private String email;

}