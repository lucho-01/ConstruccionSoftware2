package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Medications;
import app.domain.port.MedicationsPort;
import app.infrastructure.entities.MedicationsEntity;
import app.infrastructure.mapper.MedicationsMapper;
import app.infrastructure.repository.MedicationsRepository;

@Service
public class MedicationsAdapter implements MedicationsPort {

    @Autowired
    private MedicationsRepository medicationsRepository;

    @Autowired
    private MedicationsMapper medicationsMapper;

    @Override
    public Medications findByOrderNumber(Medications medications) throws Exception {
        MedicationsEntity entity = medicationsRepository.findByOrderNumber(medications.getOrderNumber());
        return medicationsMapper.toDomain(entity);
    }

    @Override
    public void save(Medications medications) throws Exception {
        MedicationsEntity entity = medicationsMapper.toEntity(medications);
        entity.setIdMedications(null); 
        medicationsRepository.save(entity);
        System.out.println("Medicamento guardado correctamente con orden #" + medications.getOrderNumber());
    }
}
