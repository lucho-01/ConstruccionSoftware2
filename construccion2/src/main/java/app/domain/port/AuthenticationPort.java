package app.domain.port;

import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;

public interface AuthenticationPort {
	
    TokenResponse authenticate(AuthCredentials credentials, String role);
    boolean validateToken(String token);
    String extractUsername(String token);
    String extractRole(String token);
}
