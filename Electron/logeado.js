const { ipcRenderer } = require('electron')
window.addEventListener('DOMContentLoaded', () => {
  const replaceText = (selector, text) => {
    const element = document.getElementById(selector)
    if (element) element.innerText = text
  }



ipcRenderer.on('login', function (event, args){
    	const usuario = document.getElementById('usuario')
	const logopeda = document.getElementById('logopeda')
//args =JSON.parse(args)
	usuario.innerHTML = "Bienvenido " +args.data.user
    	logopeda.innerHTML= "Logopeda asignado: "+args.data.logopeda


document.getElementById('listar').addEventListener('click',  

function llenar(){
ipcRenderer.send('listar', args.data.id)
console.log("IDDD"+args.data.id)


ipcRenderer.on('lista', function (event, lista){


var i=0
try{
//var terapias = JSON.parse(lista.terapias)
//var data = terapias.data.terapias
var data= lista.terapias.data.terapias
var dis= [lista.terapias.data.tipodislexia,lista.terapias.data.tipodislexia1]
//var dis =  [terapias.data.tipodislexia,terapias.data.tipodislexia1]          

var table = document.getElementById('terapias');
$("#terapias").empty();

            Object.values(data).forEach(obj => {
	
               var hilera = document.createElement("tr");
                hilera.innerHTML = '<td style="color:#2E2E2E">' + obj.idterapia + '</td>' +
                '<td style="color:#2E2E2E">' + obj.nombreterapia + '</td>' +
                '<td style="color:#2E2E2E">' + obj.descripcion + '</td>' +
		'<td style="color:#2E2E2E">' + dis[i] + '</td>' +
                '<td style="color:#2E2E2E">' + obj.estadoexistencia + '</td>';
                table.appendChild(hilera);    
i++;

       });


           
          
        

document.getElementById('descargar').addEventListener('click', (evt) => {
   $(this).toggleClass('selected')})
   
   $(document).ready(function(){
        $('#terapias tr').on("click",function() {
           $('tr').removeClass('selected');
           $(this).toggleClass('selected')   
 var mensaje = {
    idt:  $(this).find("td:first").html(),
    idp: lista.idpaciente 
    
}
console.log("Mensaje"+mensaje.idt)

ipcRenderer.send('descargar',mensaje);


}) 
})


}

catch{
 var tabla = document.getElementById('terapias');
 tabla.innerHTML = '<td style="color:#2E2E2E">'+ "SIN TERAPIAS" +'</td>';
  }
});

});


});

});