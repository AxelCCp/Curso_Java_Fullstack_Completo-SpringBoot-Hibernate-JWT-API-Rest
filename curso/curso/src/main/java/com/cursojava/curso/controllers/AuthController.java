package com.cursojava.curso.controllers;

//CONTROLADOR DE AUTENTICACIÓN DE USUARIO PARA EL LOGIN

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        if (usuarioLogueado!=null) {
            //AL VERIFICAR QUE EL EMAIL Y PASSWORD ESTÁN OK EN EL MÉTODO obtenerUsuarioPorCredenciales,...
            //GENERAMOS EL JWT. create() : RECIBE EL ID DEL USUARIO LOGUEADO. create() RECIBE String, POR LO TANTO SE HACE CASTING DEL getId QUE A SU VEZ RECIBE UN TIPO LONG.
            //ALMACENAMOS EN EL TOKEN.
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()),usuarioLogueado.getEmail());
            return tokenJwt;
         }

       else return "FAIL";
    }

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JwtUtil jwtUtil;
}
