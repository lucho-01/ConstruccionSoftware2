package app.domain.model.enums;

public enum Role {
    ADMINISTRATOR,
    HUMANRESOURCES,
    INFORMATIONSUPPORT,
    NURSES,
    DOCTORS,
    PATIENT;

    public static Role fromCode(int code) {
        switch (code) {
            case 1:
                return ADMINISTRATOR;
            case 2:
                return HUMANRESOURCES;
            case 3:
                return INFORMATIONSUPPORT;
            case 4:
                return NURSES;
            case 5:
                return DOCTORS;
            case 6:
                return PATIENT;
            default:
                throw new IllegalArgumentException("Tipo de empleado inválido. Use 1=ADMINISTRATOR, 2=HUMANRESOURCES, 3=INFORMATIONSUPPORT, 4=NURSES, 5=DOCTORS, 6=PATIENT");
        }
    }

    public static Role fromCodeOrName(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de empleado inválido. Proporcione un valor numérico o el nombre del rol.");
        }

        String trimmed = input.trim();
        try {
            return fromCode(Integer.parseInt(trimmed));
        } catch (NumberFormatException ex) {
            try {
                return Role.valueOf(trimmed.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de empleado inválido. Use 1=ADMINISTRATOR, 2=HUMANRESOURCES, 3=INFORMATIONSUPPORT, 4=NURSES, 5=DOCTORS o el nombre del rol.");
            }
        }
    }
}
