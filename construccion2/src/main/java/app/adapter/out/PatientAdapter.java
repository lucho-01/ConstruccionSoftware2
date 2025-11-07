package app.adapter.out;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.port.PatientPort;
import app.infrastructure.entities.EmployeeEntity;
import app.infrastructure.entities.PatientEntity;
import app.infrastructure.mapper.PatientMapper;
import app.infrastructure.repository.EmployeeRepository;
import app.infrastructure.repository.PatientRepository;

@Service
public class PatientAdapter implements PatientPort {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
    private EmployeeRepository employeeRepository;

	@Override
	public Patient findByDocument(Patient patient) throws Exception {
		PatientEntity patientEntity = patientRepository.findByDocument(patient.getDocument());
	    return PatientMapper.toDomain(patientEntity);
	}
	

	@Override
	public Patient findById(Patient patient) throws Exception {
		Optional<PatientEntity> Patiententity = patientRepository.findById(patient.getId());
        return PatientMapper.toDomain(Patiententity.get());
	}

	@Override
	public List<Patient> findByPatient(Patient patient) throws Exception {
	    if (patient.getDocument() == 0) {
	        throw new Exception("Debe ingresar el documento del paciente");
	    }

	    PatientEntity entity = patientRepository.findByDocument(patient.getDocument());

	    if (entity == null) {
	        throw new Exception("No se encontró ningún paciente con documento: " + patient.getDocument());
	    }

	    Patient p = new Patient();
	    p.setId(entity.getId());
	    p.setDocument(entity.getDocument());
	    p.setFullName(entity.getFullName());
	    p.setEmail(entity.getEmail());
	    p.setPhoneNumber(entity.getPhoneNumber());
	    p.setAddress(entity.getAddress());
	    p.setGender(entity.getGender());
	    p.setWeigth(entity.getWeight());
	    p.setSize(entity.getSize());

	    return List.of(p);
	}




	@Override
	public Patient updatePatient(Patient patient) throws Exception {
		Optional<PatientEntity> existingEntityOpt = patientRepository.findById(patient.getId());

	    if (existingEntityOpt.isPresent()) {
	        PatientEntity existingEntity = existingEntityOpt.get();

	        existingEntity.setFullName(patient.getFullName());
	        existingEntity.setEmail(patient.getEmail());
	        existingEntity.setDocument(patient.getDocument());
	        existingEntity.setPhoneNumber(patient.getPhoneNumber());
	        existingEntity.setAddress(patient.getAddress());
	        existingEntity.setGender(patient.getGender());
	        existingEntity.setWeight(patient.getWeigth());
	        existingEntity.setSize(patient.getSize());

	        if (patient.getBirthdate() != null && !patient.getBirthdate().isEmpty()) {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	            existingEntity.setBirthdate(LocalDate.parse(patient.getBirthdate(), formatter));
	        }

	        if (patient.getDoctor() != null) {
	            EmployeeEntity doctorEntity = employeeRepository.findByDocument(
	                String.valueOf(patient.getDoctor().getDocument())
	            );

	            if (doctorEntity == null) {
	                throw new Exception("No existe un doctor con el documento: " + patient.getDoctor().getDocument());
	            }

	            existingEntity.setDoctor(doctorEntity);
	        }

	        PatientEntity updatedEntity = patientRepository.save(existingEntity);

	        return PatientMapper.toDomain(updatedEntity);
	    } else {
	        throw new Exception("No se pudo actualizar. Paciente no encontrado con id: " + patient.getId());
	    }
	}

	@Override
	public void save(Patient patient) throws Exception {
		PatientEntity patientEntity = PatientMapper.toEntity(patient);
		
		patientEntity.setId(null);
		
		patientRepository.save(patientEntity);
		System.out.println("Paciente guardado correctamente: " + patientEntity.getFullName());
		
	}
}
