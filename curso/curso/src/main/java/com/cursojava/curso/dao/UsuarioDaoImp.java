package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
    @Override
    public List<Usuario> getUsuarios(){
        //CON HQL: "Desde la clase Usuario"
        String query = "FROM Usuario";
        //CREAMOS LA CONSULTA Y DEVUELVE UNA LISTA
        List<Usuario> resultado;
        resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @PersistenceContext // HACE REFERENCIA A ALGÚN CONTEXTO EN LA BASE DE DATOS QUE DEBA UTILIZAR.
    private EntityManager entityManager;
}
