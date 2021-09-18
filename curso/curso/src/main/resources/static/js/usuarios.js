// Call the dataTables jQuery plugin
//ES UNA LIBRERÍA DE JAVASCRIPT QUE SELECCIONA LA TABLA usuarios Y CON EL MÉTODO DataTable(), LE DA LA
//...FUNCIONALIDAD DE QUE TENGA PAGINACIÓN  Y OTRAS FUNCIONALIZADES.
$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
});


//AQUÍ VA A IR TODA LA LÓGICA
async function cargarUsuarios(){

    //LLAMADA A UN SERVIDOR CON LA FUNCIÓN FETCH. LLAMAMOS AL SERVIDOR, DONDE ESTÁ EL CONTROLADOR CON LA URL "/usuario/{id}"
    //await : HACE QUE SE PONGA LA APP A LA ESPERA DEL LLAMADO A '/usuario/675'. UNA VEZ QUE SE OBTIENE EL RESULTADO, ESTE...
    //...SE ALMACENA EN resquest.
    //CUANDO USAMOS "AWAIT" HAY QUE INDICARLE AL MÉTODO QUE SERÁ ASINCRÓNICO CON "ASYNC"
    //  const request = await fetch('/usuario/675', {


        const request = await fetch('/usuarios', {
        method: 'GET',
        //INDICA que SE USARÁ JSON
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      });
      //EL RESULTADO LO CONVERTIMOS EN JSON
      const usuarios = await request.json();

      //PARA CONCATENAR EL LISTADO DENTRO DE UN STRING QUE VA A SER EL LISTADO FINAL.
      let listadoHtml = '';
      //HACEMOS FOR EACH PARA RECORRER LA LISTA DE USUARIOS
      for(let usuario of usuarios){
        //CORTAMOS EL USUARIO DEL TBODY DEL HTML Y LO PEGAMOS ACÁ, DONDE LO ALMACENAMOS EN LA VARIABLE "USUARIO"
        let usuarioHtml = '<tr><td>'+usuario.id+'</td> <td>' + usuario.nombre + ' ' + usuario.apellido + '</td>'
        +'<td>' + usuario.email + '</td> <td>' + usuario.telefono + '</td>'
        +'<td> <a href="#" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a></td> </tr>';
        //ALMACENAMOS
        listadoHtml += usuarioHtml;
      }

      //CON ESTO  VEMOS QUE ESTÁ HACIENDO EL PROGRAMA  USANDO LOG
      //console.log(content);

      //ESTE CÓDIGO LO SACAMOS DE LA CONSOLA DEL BROWSER. LO QUE HACE ES MODIFICAR LA INFORMACIÓN DEL TBODY DE LA TABLA Y LE PASAMOS usuario.
      document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}