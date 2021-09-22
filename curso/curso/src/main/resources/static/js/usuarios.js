// Call the dataTables jQuery plugin
//ES UNA LIBRERÍA DE JAVASCRIPT QUE SELECCIONA LA TABLA usuarios Y CON EL MÉTODO DataTable(), LE DA LA
//...FUNCIONALIDAD DE QUE TENGA PAGINACIÓN  Y OTRAS FUNCIONALIZADES.
//-------------------------------------------------------------------------------------------------------------------

$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
});

//-------------------------------------------------------------------------------------------------------------------
//AQUÍ VA A IR TODA LA LÓGICA
async function cargarUsuarios(){

    //-------------------------------------------------------------------------------------------------------------------
    //LLAMADA A UN SERVIDOR CON LA FUNCIÓN FETCH. LLAMAMOS AL SERVIDOR, DONDE ESTÁ EL CONTROLADOR CON LA URL "/usuario/{id}"
    //await : HACE QUE SE PONGA LA APP A LA ESPERA DEL LLAMADO A '/usuario/675'. UNA VEZ QUE SE OBTIENE EL RESULTADO, ESTE...
    //...SE ALMACENA EN resquest.
    //CUANDO USAMOS "AWAIT" HAY QUE INDICARLE AL MÉTODO QUE SERÁ ASINCRÓNICO CON "ASYNC"
    //  const request = await fetch('/usuario/675', {
    //-------------------------------------------------------------------------------------------------------------------

      //INDICA que SE USARÁ JSON
      const request =
      await fetch('api/usuarios', {method: 'GET',headers: { 'Accept': 'application/json','Content-Type': 'application/json' }});

      //EL RESULTADO LO CONVERTIMOS EN JSON
      const usuarios = await request.json();

      //EXTRAEMOS EL BOTÓN ELIMINAR PARA DARLE FUNCIONALIDAD LLAMANDO A ELIMINAR.
      let botonEliminar = '<a href="#" onclick="eliminarUsuario(1234)" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a>';

      //PARA CONCATENAR EL LISTADO DENTRO DE UN STRING QUE VA A SER EL LISTADO FINAL.
      let listadoHtml = '';

      //HACEMOS FOR EACH PARA RECORRER LA LISTA DE USUARIOS
      for(let usuario of usuarios){

        //EXTRAEMOS EL BOTÓN ELIMINAR PARA DARLE FUNCIONALIDAD LLAMANDO A ELIMINAR.
        let botonEliminar = '<a href="#" onclick="eliminarUsuario(' +usuario.id+ ')" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a>';
        //SI EL USUARIO.TELEFONO ES NULL, DEVOLVERÁ "-", SINO DEVOLVERÁ EL TELÉFONO.
        let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;

        //CORTAMOS EL USUARIO DEL TBODY DEL HTML Y LO PEGAMOS ACÁ, DONDE LO ALMACENAMOS EN LA VARIABLE "USUARIO"
        let usuarioHtml = '<tr><td>'+usuario.id+'</td> <td>' + usuario.nombre + ' ' + usuario.apellido + '</td>'
        +'<td>' + usuario.email + '</td> <td>' + telefonoTexto + '</td>'
        +'<td>' + botonEliminar +'</td> </tr>';

        //ALMACENAMOS
        listadoHtml += usuarioHtml;
      }

      //ESTE CÓDIGO LO SACAMOS DE LA CONSOLA DEL BROWSER. LO QUE HACE ES MODIFICAR LA INFORMACIÓN DEL TBODY DE LA TABLA Y LE PASAMOS usuario.
      document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}


//FUNCIÓN PARA ELIMINAR USUARIO POR EL FRONTEND
async function eliminarUsuario(id){

    if(!confirm('¿Desea eliminar este usuario?')){
        return;
    }

    //COPIAMOS DE ARRIBA LA FUNCIÓN FETCH PARA ACCEDER AL SERVIDOR Y LO MODIFICAMOS PARA ELIMINAR.
    const request = await fetch('api/usuarios/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    //PARA ACTUALIZAR LA PÁGINA DESPUÉS DE ELIMINAR AL USUARIO.
    location.reload()

}