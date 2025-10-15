package app.adapter.out;

import java.sql.Date;

import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;
import app.domain.port.MedicalAppointmentPort;
@Service
public class MedicalAppointmentAdapter implements MedicalAppointmentPort {

	@Override
	public MedicalAppointmentPort findById(MedicalAppointment appointment) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDoctorAvailable(Employee doctor, Date date) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPatientAvailable(Patient patient, Date date) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save(MedicalAppointment appointment) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
