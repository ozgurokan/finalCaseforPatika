package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.RefreshToken;
import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    Long expireTime;



    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createRefreshToken(User user){
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(user.getId());
        if(refreshToken == null){
            refreshToken = new RefreshToken();
            refreshToken.setUser(user);
        }
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Date.from(Instant.now().plusSeconds(expireTime)));
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();

    }

    public boolean isRefreshTokenExpired(RefreshToken refreshToken){
        return refreshToken.getExpiryDate().before(new Date());
    }

    public RefreshToken getByUser(Long userId){
        return refreshTokenRepository.findByUserId(userId);
    }
}
