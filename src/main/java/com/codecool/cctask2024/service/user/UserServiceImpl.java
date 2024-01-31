package com.codecool.cctask2024.service.user;


import com.codecool.cctask2024.exception.CCException;
import com.codecool.cctask2024.exception.ExceptionMessages;
import com.codecool.cctask2024.model.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new CCException(ExceptionMessages.USER_NOT_FOUND_BY_USERNAME.getMessage()));
    }
}
