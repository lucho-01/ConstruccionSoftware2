package app.adapter.out;


import org.springframework.stereotype.Component;

import app.adapter.rest.request.AuthRequest;
import app.adapter.rest.response.TokenResponseDto;
import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;

@Component
public class AuthRestMappers {
    public AuthCredentials toDomain(AuthRequest req) {
        AuthCredentials c = new AuthCredentials();
        c.setUsername(req.getUsername());
        c.setPassword(req.getPassword());
        return c;
    }

    public TokenResponseDto toResponse(TokenResponse token) {
        return new TokenResponseDto(token.getToken());
    }
}
