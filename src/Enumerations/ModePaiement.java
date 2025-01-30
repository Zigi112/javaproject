package Enumerations;

public enum ModePaiement {
	EN_LIGNE("En ligne"),
    SUR_PLACE("Sur place");

    private final String value;

    ModePaiement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static ModePaiement fromString(String value) {
        for (ModePaiement mode : ModePaiement.values()) {
            if (mode.value.equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("ModePaiement inconnu : " + value);
    }
}
