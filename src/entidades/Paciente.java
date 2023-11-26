package entidades;

import java.io.Serializable;

public class Paciente extends Persona implements Serializable {

    private String alergias;
    private String contactoEmergencias;

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getContactoEmergencias() {
        return contactoEmergencias;
    }

    public void setContactoEmergencias(String contactoEmergencias) {
        this.contactoEmergencias = contactoEmergencias;
    }
}
