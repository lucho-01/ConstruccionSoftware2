package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.model.MedicalRecord;
import app.domain.port.PatientPort;
import app.domain.port.SearchMedicalRecordPort;
@Service
public class SearchMedicalRecordService {
    @Autowired
    private PatientPort patientPort;
    @Autowired
    private SearchMedicalRecordPort searchMedicalRecordPort;

    public List<MedicalRecord> search(Patient patient) throws Exception {
        Patient existingPatient = patientPort.findByDocument(patient);

        if (existingPatient == null) {
            throw new Exception("Debe consultar historia clínica de un paciente registrado");
        }

        List<MedicalRecord> records = searchMedicalRecordPort.findByPatient(existingPatient);

        if (records == null || records.isEmpty()) {
            throw new Exception("El paciente no tiene historias clínicas registradas");
        }

        return records;
    }
}

