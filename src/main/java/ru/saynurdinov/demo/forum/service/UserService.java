package ru.saynurdinov.demo.forum.service;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.saynurdinov.demo.forum.DTO.RegisterDTO;
import ru.saynurdinov.demo.forum.entity.User;
import ru.saynurdinov.demo.forum.exception.UserIsAlreadyExistsException;
import ru.saynurdinov.demo.forum.security.UserDetailsImpl;

public interface UserService {

    User signUp(RegisterDTO registerDTO) throws UserIsAlreadyExistsException;

    static UserDetailsImpl getAuthenticated() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    static boolean isAdmin() {
        return getAuthenticated().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }
}
