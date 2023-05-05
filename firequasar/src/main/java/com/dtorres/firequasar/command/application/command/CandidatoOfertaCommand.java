package com.dtorres.firequasar.command.application.command;

public class CandidatoOfertaCommand {

    private String nombre;
    private String apellido;
    private Long celular;
    private String email;
    private String profile;
    private String presentacion;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Long getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile() {
        return profile;
    }

    public String getPresentacion() {
        return presentacion;
    }
}
