package app.adapter.in.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.out.AuthRestMappers;
import app.adapter.rest.request.AuthRequest;
import app.adapter.rest.request.PatientRegisterRequest;
import app.adapter.rest.response.PatientResponseDto;
import app.adapter.rest.response.TokenResponseDto;
import app.application.usercases.LoginUseCase;
import app.application.usercases.RegisterPatientUseCase;
import app.domain.model.Patient;
import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginUseCase loginUseCase;

    @Autowired
    private RegisterPatientUseCase registerPatientUseCase;

    @Autowired
    private AuthRestMappers authRestMapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody AuthRequest request) throws Exception {
        AuthCredentials credentials = authRestMapper.toDomain(request);
        TokenResponse token = loginUseCase.login(credentials);
        return ResponseEntity.ok(authRestMapper.toResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<PatientResponseDto> register(@RequestBody PatientRegisterRequest request) throws Exception {
        Patient patient = authRestMapper.toDomain(request);
        Patient registeredPatient = registerPatientUseCase.registerPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(authRestMapper.toResponse(registeredPatient));
    }
}
