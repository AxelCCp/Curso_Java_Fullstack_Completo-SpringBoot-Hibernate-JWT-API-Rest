package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    /*
    @RequestMapping("/prueba0")
    public String  prueba0(){
        return "prueba0";
    }
     */

    /*
    @RequestMapping("/prueba")
    public List<String> prueba(){
        return  List.of("Manzana","Kiwi", "Plátano");
    }
    */

    //DEVUELVE UN USUARIO
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Majin");
        usuario.setApellido("Boo");
        usuario.setEmail("Boo@boo.jp");
        usuario.setTelefono("278343283");
        return usuario;
    }

    /*
    //DEVUELVE UNA LISTA DE USUARIOS
    @RequestMapping("/usuarios")
    public List<Usuario> getUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Majin");
        usuario.setApellido("Boo");
        usuario.setEmail("Boo@boo.jp");
        usuario.setTelefono("278343283");
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("Mr.");
        usuario2.setApellido("Satán");
        usuario2.setEmail("mr@satan.jp");
        usuario2.setTelefono("275553283");
        Usuario usuario3 = new Usuario();
        usuario3.setId(3L);
        usuario3.setNombre("UP");
        usuario3.setApellido("zzz");
        usuario3.setEmail("up@zzz.jp");
        usuario3.setTelefono("987343283");
        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        return usuarios;
    }
    */

    //MÉTODO QUE DEVUELVE LISTA DE LA USUARIOS
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public  List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }

    //MÉTODO PARA AGREGAR USUARIOS  //@RequestBody : RECIBIMOS UN OBJ DE TIPO USUARIO, YA QUE EL MÉTODO ES POST // DIFIERE DE @REQUESTPARAM YA QUE ESTE ES PARA LOS GET.
    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public  void registrarUsuario(@RequestBody Usuario usuario){

        //MODIFICACIÓN DE CONTRASEÑA CON UN HASH PARA ALMACENAR CONTRASEÑA CIFRADA EN BBDD
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //CONVERTIMOS LA CONTRASEÑA
        //hash(n° de cantidad de iteraciones, uso de memoria que se usará, Si es q va a crear varios hilos para que procese más rápido, texto que queremos hashear)
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        //ESTABLECEMOS LA CONTRASEÑA HASHEADA
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }


    //MODIFICA AL USUARIO
    @RequestMapping("/api/usuario2")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Majin");
        usuario.setApellido("Boo");
        usuario.setEmail("Boo@boo.jp");
        usuario.setTelefono("278343283");
        return usuario;
    }

    //ELIMINA USUARIO
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
      usuarioDao.eliminar(id);
    }

    //BUSCAR USUARIO
    @RequestMapping("/usuario4")
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Majin");
        usuario.setApellido("Boo");
        usuario.setEmail("Boo@boo.jp");
        usuario.setTelefono("278343283");
        return usuario;
    }


    @Autowired
    private UsuarioDao usuarioDao;
}
