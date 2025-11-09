package app.adapter.rest.response;

public class TokenResponseDto {
    private String token;

    public TokenResponseDto() {}
    public TokenResponseDto(String token) { this.token = token; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
