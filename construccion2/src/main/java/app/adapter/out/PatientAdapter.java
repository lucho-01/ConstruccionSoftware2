package app.adapter.out;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	    p.setPolicyNumber(entity.getPolicyNumber() != null ? entity.getPolicyNumber() : 0);
	    p.setInsuranceCompanyName(entity.getInsuranceCompanyName());
	    p.setPolicyValidity(entity.getPolicyValidity() != null ? entity.getPolicyValidity().toString() : null);
	    p.setPolicyEndDate(entity.getPolicyEndDate() != null ? entity.getPolicyEndDate().toString() : null);

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
	        existingEntity.setPolicyNumber(patient.getPolicyNumber());
	        existingEntity.setInsuranceCompanyName(patient.getInsuranceCompanyName());

	        if (patient.getPolicyValidity() != null && !patient.getPolicyValidity().isEmpty()) {
	            existingEntity.setPolicyValidity(LocalDate.parse(patient.getPolicyValidity()));
	        }

	        if (patient.getPolicyEndDate() != null && !patient.getPolicyEndDate().isEmpty()) {
	            existingEntity.setPolicyEndDate(LocalDate.parse(patient.getPolicyEndDate()));
	        }

	        if (patient.getBirthdate() != null && !patient.getBirthdate().isEmpty()) {
	            existingEntity.setBirthdate(parseBirthdate(patient.getBirthdate()));
	        }

	        if (patient.getDoctorDocument() != null) {
	            EmployeeEntity doctorEntity = employeeRepository.findByDocument(
	                String.valueOf(patient.getDoctorDocument().getDocument())
	            );

	            if (doctorEntity == null) {
	                throw new Exception("No existe un doctor con el documento: " + patient.getDoctorDocument().getDocument());
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
	public Patient save(Patient patient) throws Exception {
		PatientEntity patientEntity = PatientMapper.toEntity(patient);

		patientEntity.setId(null);

		if (patient.getDoctorDocument() != null) {
			EmployeeEntity doctorEntity = null;
			if (patient.getDoctorDocument().getId() > 0) {
				doctorEntity = employeeRepository.findById(patient.getDoctorDocument().getId()).orElse(null);
			}
			if (doctorEntity == null) {
				long doctorDoc = patient.getDoctorDocument().getDocument();
				if (doctorDoc > 0) {
					doctorEntity = employeeRepository.findByDocument(String.valueOf(doctorDoc));
				}
			}
			if (doctorEntity != null) {
				patientEntity.setDoctor(doctorEntity);
			}
		}

		PatientEntity saved = patientRepository.save(patientEntity);
		return PatientMapper.toDomain(saved);
	}

	@Transactional(readOnly = true)
	public List<Patient> findAll() throws Exception {
		return patientRepository.findAll().stream()
			.map(PatientMapper::toDomain)
			.collect(Collectors.toList());
	}

	@Override
	public Patient findByUsername(String username) throws Exception {
		PatientEntity patientEntity = patientRepository.findByUsername(username);
		return patientEntity != null ? PatientMapper.toDomain(patientEntity) : null;
	}

	@Override
	public Patient findByEmail(String email) throws Exception {
		PatientEntity patientEntity = patientRepository.findByEmail(email);
		return patientEntity != null ? PatientMapper.toDomain(patientEntity) : null;
	}

	@Override
	public Patient deleteById(Patient patient) throws Exception {
		Optional<PatientEntity> optionalEntity = patientRepository.findById(patient.getId());
		if (optionalEntity.isPresent()) {
			PatientEntity entityToDelete = optionalEntity.get();
			Patient deletedPatient = PatientMapper.toDomain(entityToDelete);
			patientRepository.delete(entityToDelete);
			return deletedPatient;
		} else {
			System.out.println("No se encontró un paciente con ID: " + patient.getId());
			return null;
		}
	}

	private LocalDate parseBirthdate(String value) {
		String trimmed = value.trim();
		try {
			return LocalDate.parse(trimmed);
		} catch (DateTimeParseException ignored) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return LocalDate.parse(trimmed, formatter);
		}
	}
}
