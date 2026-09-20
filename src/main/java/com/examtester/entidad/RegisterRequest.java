package com.examtester.entidad;

public class RegisterRequest {
    private String nombre;
    private String correoElectronico;
    private String password;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
