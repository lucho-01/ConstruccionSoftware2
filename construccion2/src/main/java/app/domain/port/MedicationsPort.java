package app.domain.port;
import app.domain.model.medications;
public interface MedicationsPort {
	
	public medications findByOrderNumber(medications medications) throws Exception;
	public void save(medications mediactions) throws Exception;

}
