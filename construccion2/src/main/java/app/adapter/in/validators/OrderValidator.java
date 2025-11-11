package app.adapter.in.validators;

import org.springframework.stereotype.Component;

@Component
public class OrderValidator extends SimpleValidator{

	public String DiagnosticAidValidator (String value) throws Exception {
            return stringValidator("Ayuda diagnostica de la orden", value);
	}
        
        public String ProcedureValidator(String value) throws Exception {
            return stringValidator("El procedimiento de la orden", value);
                }
        public String MedicationsValidator(String value) throws Exception {
            return stringValidator("Medicina de la orden", value);
                }
        public long patientIdValidator(String value) throws Exception{
        	return longValidator("la identificacion del paciente", value);
        }
	
}
