package com.chaewookim.accountbookformoms.domain.user.application;

import com.chaewookim.accountbookformoms.domain.user.dao.UserRepository;
import com.chaewookim.accountbookformoms.domain.user.domain.User;
import com.chaewookim.accountbookformoms.domain.user.dto.request.SignUpRequest;
import com.chaewookim.accountbookformoms.domain.user.dto.request.UpdateRequest;
import com.chaewookim.accountbookformoms.global.error.CustomException;
import com.chaewookim.accountbookformoms.global.error.ErrorCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(SignUpRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        String encoded = passwordEncoder.encode(request.password());

        User user = User.builder()
                .email(request.email())
                .username(request.username())
                .password(encoded)
                .birthDate(request.birthDate())
                .address(request.address())
                .build();

        userRepository.save(user);

        return userRepository.findByEmail(request.email()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)).getId();
    }

    public User getUserByEmail(String username) {

        return userRepository.findByEmail(username).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public Long updateUser(String email, @Valid UpdateRequest request) {

        return userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND))
                .updateUser(request)
                .getId();
    }
}
