package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import app.domain.model.MedicalRecord;
import app.domain.port.MedicalRecordPort;
import app.infrastructure.entities.MedicalRecordEntity;
import app.infrastructure.mapper.MedicalRecordMapper;
import app.infrastructure.repository.MedicalRecordRepository;

@Service
public class MedicalRecordAdapter implements MedicalRecordPort {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) throws Exception {
        MedicalRecordEntity entity = medicalRecordMapper.toEntity(medicalRecord);
        entity.setId(null);
        return medicalRecordMapper.toDomain(medicalRecordRepository.save(entity));
    }

    @Override
    public MedicalRecord findById(MedicalRecord medicalRecord) throws Exception {
        return medicalRecordRepository.findById(medicalRecord.getId())
                .map(medicalRecordMapper::toDomain)
                .orElse(null);
    }

    @Override
    public java.util.List<MedicalRecord> findAll() throws Exception {
        return medicalRecordRepository.findAll().stream()
                .map(medicalRecordMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(MedicalRecord medicalRecord) throws Exception {
        MedicalRecordEntity existing = medicalRecordRepository.findById(medicalRecord.getId())
                .orElseThrow(() -> new Exception("No se encontró la historia clínica para actualizar."));

        existing.setDiagnosis(medicalRecord.getDiagnosis());
        existing.setReasonConsultation(medicalRecord.getReasonConsultation());
        existing.setSymptomatology(medicalRecord.getSymptomatology());
        existing.setDate(medicalRecord.getDate().toLocalDate());

        medicalRecordRepository.save(existing);
    }

    @Override
    public void deleteById(MedicalRecord medicalRecord) throws Exception {
        MedicalRecordEntity existing = medicalRecordRepository.findById(medicalRecord.getId())
                .orElseThrow(() -> new Exception("No se encontró la historia clínica para eliminar."));
        medicalRecordRepository.delete(existing);
    }
}
