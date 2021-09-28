// Call the dataTables jQuery plugin
$(document).ready(function() {
   //on ready
});

//-------------------------------------------------------------------------------------------------------------------
//AQUÍ VA A IR TODA LA LÓGICA
async function iniciarSesion(){

      //TRAEMOS LOS DATOS DEL FORMULARIO DE REGISTRO "registrar.html"
      let datos = {};
      datos.email = document.getElementById('txtEmail').value;
      datos.password = document.getElementById('txtPassword').value;

      //INDICA que SE USARÁ JSON
      const request =
      await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //JSON.stringify : ESTA FUNCIÓN CONVIERTE UN OBJ JAVASCRIPT A JSON.
        body: JSON.stringify(datos)
      });

      //EL RESULTADO QUE VIENE DESDE EL CONTROLLER EN EL SERVIDOR LO ALMACENAMOS COMO TEXTO EN "respuesta".
      const respuesta = await request.text();

      //SI EL MÉTODO DEL CONTROLLER DE AUTENTICACIÓN  NO DEVOLVIÓ "FAIL", NOS MANDARÁ A usuarios.html'
      if(respuesta != 'FAIL'){
        //GUARDAMOS LA INFORMACIÓN QUE VIENE CON LA RESPUESTA DEL SERVIDOR EN LOCALSTORAGE. ASÍ GUARDAMOS LA INFO EN EL LADO DEL BROWSER.
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'usuarios.html'
      }else{
      alert("Las credenciales son incorrectas");
      }
}

//-------------------------------------------------------------------------------------------------------------------


