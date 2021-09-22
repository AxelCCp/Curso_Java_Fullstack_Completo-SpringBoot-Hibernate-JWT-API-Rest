// Call the dataTables jQuery plugin
$(document).ready(function() {
   //on ready

});

//-------------------------------------------------------------------------------------------------------------------
//AQUÍ VA A IR TODA LA LÓGICA
async function registrarUsuario(){

      //TRAEMOS LOS DATOS DEL FORMULARIO DE REGISTRO "registrar.html"
      let datos = {};
      datos.nombre = document.getElementById('txtNombre').value;
      datos.apellido = document.getElementById('txtApellido').value;
      datos.email = document.getElementById('txtEmail').value;
      datos.password = document.getElementById('txtPassword').value;

      let repetirPassword = document.getElementById('txtRepetirPassword').value;

      if(repetirPassword !=  datos.password){
        alert("La contraseña que escribiste es diferente");
        return;
      }

      //INDICA que SE USARÁ JSON
      const request =
      await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //JSON.stringify : ESTA FUNCIÓN CONVIERTE UN OBJ JAVASCRIPT A JSON.
        body: JSON.stringify(datos)
      });

      //EL RESULTADO LO CONVERTIMOS EN JSON
      const usuarios = await request.json();
}

//-------------------------------------------------------------------------------------------------------------------


