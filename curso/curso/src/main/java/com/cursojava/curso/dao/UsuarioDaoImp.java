package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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

    @Override
    public boolean verificarEmailPassword(Usuario usuario) {
        //CON ESTA QUERY EVITAMOS ATAQUES DE INYECCIÓN SQL
        String query = "FROM Usuario WHERE email = :email";

        //ESTABLECEMOS LOS PARÁMETROS PARA EMAIL // PARA QUE APAREZCA LA "S:" PRIMERO PONE LAS COMILLAS Y LUEGO ESPACIO, Y APARECE SOLO.
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();


        //IF, PARA EVITAR EL NULL POINT EXCEPTION POR SI ES QUE NO HAY COINCIDENCIA CON LOS REGISTROS DE LA BBDD.
        if(lista.isEmpty()){
            return false;
        }

        //OBTENEMOS EL HASH DE LA BBDD  // USAMOS EL GET() DE LIST CON INDICE "0", YA QUE DEBE HABER SOLO UN REGISTRO EN LA LISTA. NO DEBE HABER MÁS DE UN REGISTRO CON LAS MISMAS CREDENCIALES.
        String passwordHashed = lista.get(0).getPassword();

        //VERIFICAMOS LA CONTRASEÑA , verify(COMPARAMOS UN HASH CON UNA CONTRASEÑA INGRESADA POR EL USUARIO) DEVUELVE TRUE/FALSE.
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        boolean laPasswordEsLaMisma = argon2.verify(passwordHashed, usuario.getPassword());
        return laPasswordEsLaMisma;


    }

    @PersistenceContext // HACE REFERENCIA A ALGÚN CONTEXTO EN LA BASE DE DATOS QUE DEBA UTILIZAR.
    private EntityManager entityManager;
}
