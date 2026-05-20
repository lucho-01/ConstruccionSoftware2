package app.adapter.out;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import app.adapter.rest.request.AuthRequest;
import app.adapter.rest.request.PatientRegisterRequest;
import app.adapter.rest.response.PatientResponseDto;
import app.adapter.rest.response.TokenResponseDto;
import app.domain.model.Patient;
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
        return new TokenResponseDto(
            token.getToken(),
            token.getUsername(),
            token.getFullName(),
            token.getRole()
        );
    }

    public Patient toDomain(PatientRegisterRequest req) {
        Patient patient = new Patient();
        patient.setFullName(req.getFullName());
        patient.setEmail(req.getEmail());
        patient.setUserName(req.getUsername());
        patient.setPassword(req.getPassword());
        patient.setPhoneNumber(req.getPhoneNumber());
        patient.setAddress(req.getAddress());
        
        if (req.getBirthdate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            patient.setBirthdate(req.getBirthdate().format(formatter));
        }
        
        patient.setGender(req.getGender());
        patient.setDocument(req.getDocument());
        patient.setWeigth(req.getWeight());
        patient.setSize(req.getSize());
        patient.setPolicyNumber(req.getPolicyNumber());
        patient.setInsuranceCompanyName(req.getInsuranceCompanyName());
        patient.setPolicyValidity(req.getPolicyValidity());
        patient.setPolicyEndDate(req.getPolicyEndDate());
        
        return patient;
    }

    public PatientResponseDto toResponse(Patient patient) {
        return new PatientResponseDto(
            patient.getId(),
            patient.getFullName(),
            patient.getEmail(),
            patient.getUserName(),
            patient.getPhoneNumber(),
            patient.getAddress(),
            patient.getBirthdate() != null ? parseBirthdate(patient.getBirthdate()) : null,
            patient.getGender(),
            patient.getDocument(),
            patient.getWeigth(),
            patient.getSize()
        );
    }

    private LocalDate parseBirthdate(String value) {
        String trimmed = value.trim();
        try {
            return LocalDate.parse(trimmed);
        } catch (DateTimeParseException ignored) {
            return LocalDate.parse(trimmed, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }
}
