package CapstoneDesign.Backendserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
public class User {

    @NotBlank //implementation 'org.springframework.boot:spring-boot-starter-validation'임포트해야됨
    @Id
    @Column(name= "User_id")
    private String id;
    @NotBlank
    private String password;

    private String email;

    private String name;

    private LocalDate birth;
    private String sex;
    private String mbti;

}
