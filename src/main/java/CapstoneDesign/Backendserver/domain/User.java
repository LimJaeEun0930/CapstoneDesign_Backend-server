package CapstoneDesign.Backendserver.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class User {

    @NotBlank //implementation 'org.springframework.boot:spring-boot-starter-validation'임포트해야됨
    private String id;
    @NotBlank
    private String password;

    private String email;

    private String name;

    private LocalDate birth;
    private String sex;
    private String mbti;

}
