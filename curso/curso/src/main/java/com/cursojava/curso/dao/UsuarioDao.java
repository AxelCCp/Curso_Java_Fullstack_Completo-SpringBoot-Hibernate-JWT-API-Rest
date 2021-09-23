package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    public List<Usuario> getUsuarios();

    public void eliminar(Long id);

    public void registrar(Usuario usuario);

    public boolean verificarEmailPassword(Usuario usuario);
}
