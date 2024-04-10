package ru.saynurdinov.demo.forum.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.saynurdinov.demo.forum.DTO.RegisterDTO;
import ru.saynurdinov.demo.forum.DTO.UserDTO;
import ru.saynurdinov.demo.forum.entity.User;
import ru.saynurdinov.demo.forum.exception.UserIsAlreadyExistsException;
import ru.saynurdinov.demo.forum.service.UserService;

import java.net.BindException;

@RestController
@RequestMapping("/sign-up")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterDTO registerDTO) throws UserIsAlreadyExistsException, BindException {
        User registeredUser = userService.signUp(registerDTO);
        return new ResponseEntity<>(new UserDTO(registeredUser.getId(), registeredUser.getUsername(), registeredUser.getRole()), HttpStatus.OK);
    }
}
