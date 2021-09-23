package com.cursojava.curso.controllers;

//CONTROLADOR DE AUTENTICACIÓN DE USUARIO PARA EL LOGIN

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
       if (usuarioDao.verificarEmailPassword(usuario) == true) {
           return "OK";
       }
       else return "FAIL";
    }

    @Autowired
    UsuarioDao usuarioDao;
}
