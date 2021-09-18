package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping("/prueba")
    public List<String> prueba(){
        return  List.of("Manzana","Kiwi", "Plátano");
    }

    //DEVUELVE UN USUARIO
    @RequestMapping("/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Majin");
        usuario.setApellido("Boo");
        usuario.setEmail("Boo@boo.jp");
        usuario.setTelefono("278343283");
        return usuario;
    }

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

    //MODIFICA AL USUARIO
    @RequestMapping("/usuario2")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Majin");
        usuario.setApellido("Boo");
        usuario.setEmail("Boo@boo.jp");
        usuario.setTelefono("278343283");
        return usuario;
    }
    //ELIMINA USUARIO
    @RequestMapping("/usuario3")
    public Usuario eliminar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Majin");
        usuario.setApellido("Boo");
        usuario.setEmail("Boo@boo.jp");
        usuario.setTelefono("278343283");
        return usuario;
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

}
