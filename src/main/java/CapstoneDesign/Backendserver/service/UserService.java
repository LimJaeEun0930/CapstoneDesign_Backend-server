package CapstoneDesign.Backendserver.service;

import CapstoneDesign.Backendserver.domain.User;
import CapstoneDesign.Backendserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public boolean validateDuplicateMember(String id) {
        log.info("중복체크함수실행 id={}",id);
        Optional<User> existinguser = Optional.ofNullable(userRepository.findOne(id));
        return existinguser.isPresent();
        }


    public void join(User user) {
        userRepository.save(user);
    }

 }
