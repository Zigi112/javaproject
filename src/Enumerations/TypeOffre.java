package Enumerations;

public enum TypeOffre {
	 SEJOUR_HOTEL("SejourHotel"),
     VOL("vol"),
     VOYAGE_ORGANISE("VoyageOrganise");
	
     private final String value;
     
     TypeOffre(String value) {
         this.value = value;
     }

     public String getValue() {
         return value;
     }
     public static TypeOffre fromString(String value) {
         for (TypeOffre mode : TypeOffre.values()) {
             if (mode.value.equalsIgnoreCase(value)) {
                 return mode;
             }
         }
         throw new IllegalArgumentException("Typeoffre inconnu : " + value);
     }
}
