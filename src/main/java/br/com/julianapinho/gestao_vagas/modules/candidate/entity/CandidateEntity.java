package br.com.julianapinho.gestao_vagas.modules.candidate.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name= "candidate")
@Data
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "O campo [username] não deve conter espaços")
    private String username;

    @Email(message = "O campo [e-mail] deve conter um e-mail válido")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\[\\]{}|\\\\;:'\",.<>?/~`-])(?=.*\\d).*$", message = "A senha deve conter pelo menos 1 caractere maiusculo, 1 número e 1 caractere especial.")
    @Length(min = 10, max = 20, message = "A senha deve conter entre [10] e [20] caracteres")
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}