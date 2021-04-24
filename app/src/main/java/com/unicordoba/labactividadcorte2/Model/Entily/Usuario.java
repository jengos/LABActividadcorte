package com.unicordoba.labactividadcorte2.Model.Entily;

public class Usuario {
    private int id;

    private String nombreUsuario, correo, password, telefono;

    public Usuario(String nombreUsuario, String correo, String password) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
    }

    public Usuario(int id, String nombreUsuario, String correo, String password, String telefono) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario() {
    }
    public boolean isNull (){
        if(nombreUsuario.equals("")&&correo.equals("")&&password.equals("")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
