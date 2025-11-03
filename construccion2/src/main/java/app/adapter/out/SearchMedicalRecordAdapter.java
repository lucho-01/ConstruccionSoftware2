package app.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalRecord;
import app.domain.model.Patient;
import app.domain.port.SearchMedicalRecordPort;
import app.infrastructure.entities.MedicalRecordEntity;
import app.infrastructure.mapper.MedicalRecordMapper;
import app.infrastructure.repository.MedicalRecordRepository;

@Service
public class SearchMedicalRecordAdapter implements SearchMedicalRecordPort {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public List<MedicalRecord> findByPatient(Patient patient) throws Exception {
        List<MedicalRecordEntity> entities = medicalRecordRepository.findByPatient_Document(patient.getDocument());

        if (entities == null || entities.isEmpty()) {
            throw new Exception("No se encontraron historias clínicas para el paciente con documento: " + patient.getDocument());
        }

        return entities.stream()
                .map(medicalRecordMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public SearchMedicalRecordPort findById(Patient patient) throws Exception {
        throw new UnsupportedOperationException("Búsqueda por ID no implementada todavía");
    }
}
