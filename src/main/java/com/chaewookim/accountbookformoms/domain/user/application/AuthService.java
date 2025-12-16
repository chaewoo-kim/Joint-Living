package com.chaewookim.accountbookformoms.domain.user.application;

import com.chaewookim.accountbookformoms.domain.user.dao.RefreshTokenRepository;
import com.chaewookim.accountbookformoms.domain.user.dao.UserRepository;
import com.chaewookim.accountbookformoms.domain.user.domain.RefreshToken;
import com.chaewookim.accountbookformoms.domain.user.domain.User;
import com.chaewookim.accountbookformoms.domain.user.dto.request.LoginRequest;
import com.chaewookim.accountbookformoms.domain.user.dto.response.LoginResponse;
import com.chaewookim.accountbookformoms.global.error.CustomException;
import com.chaewookim.accountbookformoms.global.error.ErrorCode;
import com.chaewookim.accountbookformoms.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    private static final long REFRESH_TOKEN_VALID_TIME = 14 * 24 * 60 * 60 * 1000L;

    @Transactional
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());

        refreshTokenRepository.deleteByUserId(user.getId());

        refreshTokenRepository.flush();

        RefreshToken tokenEntity = new RefreshToken(refreshToken, user.getId(), new Date(new Date().getTime() + REFRESH_TOKEN_VALID_TIME));
        refreshTokenRepository.save(tokenEntity);
        log.info("tokenEntity = {}", tokenEntity.getToken());

        return new LoginResponse(accessToken, user.getUsername());
    }
}
