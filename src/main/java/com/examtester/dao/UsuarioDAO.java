package com.examtester.dao;

import com.examtester.entidad.Usuario;

public interface UsuarioDAO {
    Usuario findByCorreo(String correoElectronico);
    int insertar(Usuario usuario);
}
