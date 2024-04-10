package ru.saynurdinov.demo.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.saynurdinov.demo.forum.DTO.RegisterDTO;
import ru.saynurdinov.demo.forum.entity.User;
import ru.saynurdinov.demo.forum.exception.UserIsAlreadyExistsException;
import ru.saynurdinov.demo.forum.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(RegisterDTO registerDTO) throws UserIsAlreadyExistsException {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new UserIsAlreadyExistsException("User with this login is already exits");
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole("ROLE_" + registerDTO.getRole());
        return userRepository.save(user);
    }
}
