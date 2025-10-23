package app.infrastructure.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.model.Billing;
import app.infrastructure.entities.BillingEntity;

@Component
public class BillingMapper {


    private final EmployeeMapper employeeMapper;
    private final PatientMapper patientMapper;

    public BillingMapper(EmployeeMapper employeeMapper, PatientMapper patientMapper) {
        this.employeeMapper = employeeMapper;
        this.patientMapper = patientMapper;
    }

    // === Domain → Entity ===
    public BillingEntity toEntity(Billing billing) {
        if (billing == null) return null;

        BillingEntity entity = new BillingEntity();
        entity.setPatientDocument(billing.getPatientDocument());
        entity.setPolicyNumber(billing.getPolicyNumber());
        entity.setPatientAge(billing.getPatientAge());
        entity.setInsuranceCompanyName(billing.getInsuranceCompanyName());

        // java.sql.Date → LocalDate
        if (billing.getPolicyValidity() != null)
            entity.setPolicyValidity(billing.getPolicyValidity().toLocalDate());

        if (billing.getPolicyEndDate() != null)
            entity.setPolicyEndDate(billing.getPolicyEndDate().toLocalDate());

        entity.setDoctorName(employeeMapper.toEntity(billing.getDoctorName()));
        entity.setPatientName(patientMapper.toEntity(billing.getPatientName()));

        return entity;
    }

    // === Entity → Domain ===
    public Billing toDomain(BillingEntity entity) {
        if (entity == null) return null;

        Billing billing = new Billing();
        billing.setPatientDocument(entity.getPatientDocument());
        billing.setPolicyNumber(entity.getPolicyNumber());
        billing.setPatientAge(entity.getPatientAge());
        billing.setInsuranceCompanyName(entity.getInsuranceCompanyName());

        // LocalDate → java.sql.Date
        if (entity.getPolicyValidity() != null)
            billing.setPolicyValidity(java.sql.Date.valueOf(entity.getPolicyValidity()));

        if (entity.getPolicyEndDate() != null)
            billing.setPolicyEndDate(java.sql.Date.valueOf(entity.getPolicyEndDate()));

        billing.setDoctorName(employeeMapper.toDomain(entity.getDoctorName()));
        billing.setPatientName(patientMapper.toDomain(entity.getPatientName()));

        return billing;
    }
}