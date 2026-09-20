package com.examtester.entidad;

public class Usuario {
    private int idUsuarios;
    private String nombre;
    private String password;
    private String correoElectronico;

    public int getIdUsuarios() { return idUsuarios; }
    public void setIdUsuarios(int idUsuarios) { this.idUsuarios = idUsuarios; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
}
