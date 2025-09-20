package app.adapter.in.validators;

public class OrderValidator extends SimpleValidator{

	public String medicationValidator(String value) throws Exception{
		return stringValidator("medicina de la orden", value);
	}
	
	public String proceduresValidator(String value) throws Exception{
		return stringValidator("procedimiento de la orden", value);
	}
	
	public String diagnosticAidsValidator(String value) throws Exception{
		return stringValidator("ayuda diagnostica de la orden", value);
	}
}
