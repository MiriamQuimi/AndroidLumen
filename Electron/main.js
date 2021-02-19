// Modules to control application life and create native browser window
const {app, BrowserWindow, ipcMain} = require('electron')
const path = require('path')
const axios = require('axios')
const remote = BrowserWindow.remote


function createWindow () {
  // Create the browser window.
  const mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
    preload: path.join(__dirname, 'preload.js'),
nodeIntegration: true
  
    }
  })

  // and load the index.html of the app.
  mainWindow.loadFile('index.html')

  // Open the DevTools.
  // mainWindow.webContents.openDevTools()
mainWindow.webContents.send('asynchronous-message', 'ping')

const rpc = axios.create({
  
  proxy: false
})

function serialize(obj) {
  let str = [];
  for(let p in obj)
    if (obj.hasOwnProperty(p)) {
      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    }
  return str.join("&");
} 




ipcMain.on('submitForm',(event, data) => {
data = serialize(data);

var win = new BrowserWindow({
  width: 800,
  height: 600,
webPreferences: {
nodeIntegration: true

  } })
console.log(data);



  async function login() { 
    let res = await rpc.post('http://localhost:8000/api/login',data);

//var fs = require('fs');
//var login = fs.readFileSync("usuario")
//login = login.toString()
//var i = login.indexOf("{")
//login = login.substring(i)



 win.webContents.on('dom-ready', () => {
   //  (win.webContents.send('login', login))
   (win.webContents.send('login', res.data))
 
   })

win.loadFile('logeado.html');
mainWindow.close();

}

login();



ipcMain.on('cerrar',(event, dat) => {



 var login = new BrowserWindow({
   width: 800,
    height: 600,
  webPreferences: {
  nodeIntegration: true
  
    } })

login.loadFile('index.html');
win.close()
})















ipcMain.on('listar',(event, datos) => {
async function listar() {

var id = {id: datos}
console.log(id)
let res1 = await rpc.post('http://localhost:8000/api/terapia',id);
var data = {
  terapias: res1.data,
  idpaciente: id 
  
}


//var fs = require('fs');
//var listar = fs.readFileSync("terapias")
//listar = listar.toString()
//var i = listar.indexOf("{")
//listar = listar.substring(i)


//var datos = {
 // terapias: listar,
 // idpaciente: id 
  
//}

console.log("LOGINNNN"+datos.toString())










//win.webContents.send('lista', datos)
  win.webContents.send('lista', data)

}

listar()
})

}) 

ipcMain.on('descargar',(event, datos) => {
var id = datos.idt
console.log(id)
console.log(datos.idp)


 var wind = new BrowserWindow({
    width: 800,
    height: 600,
  webPreferences: {
  nodeIntegration: true
  
    } })
    async function descargar() {
var id =   parseInt(datos.idt)
var idt = {id: id}

let res = await rpc.post('http://localhost:8000/api/descargar',idt);
    // var Client = require('ssh2').Client;
//var connSettings = {
  // host: '201.159.223.162',
  // port: 22, // Normal is 22 port
  // username: 'ms_edge',
  // password: 'Password$2020.'
   // You can use a key file too, read the ssh2 documentation
//};

//var conn = new Client();
//conn.on('ready', function() {
  //conn.sftp(function(err, sftp) {
    //  if (err) throw err;
     
      //var moveFrom = "/home/ms_edge/json/"+id;
      //var moveTo = ""+id;
//sftp.fastGet(moveFrom, moveTo , {}, function(downloadError){
  //        if(downloadError) throw downloadError;

    //      console.log("Succesfully uploaded");

//})
  //})
    //}).connect(connSettings)




//async function file(){
//var fs = require('fs');
//var data = fs.readFileSync(id)

        wind.webContents.send('terapia', res.data.json ) 
  
    
//}
 
 
var json = JSON.parse(res.data.json);

var imagen1= json.Terapia.Acciones[0].accion.configuraciones.extraConfig.urlImagenes[0]
var imag1 = imagen1.substring(2)
//var imag1 = JSON.stringify(imagen1.substring(2))
//var image1 = imag1.substring(1,imag1.length-1)
//console.log(image1)
var imagen2= json.Terapia.Acciones[0].accion.configuraciones.extraConfig.urlImagenes[1]
var imag2 = imagen2.substring(2)
var imagen3= json.Terapia.Acciones[0].accion.configuraciones.extraConfig.urlImagenes[2]
var imag3 = imagen3.substring(2)
var imagen4= json.Terapia.Acciones[0].accion.configuraciones.extraConfig.urlImagenes[3]
var imag4 = imagen4.substring(2)
var imagen5= json.Terapia.Acciones[1].accion.configuraciones.extraConfig.urlImagenes[0]
var imag5 = imagen5.substring(2)
var imagen6= json.Terapia.Acciones[1].accion.configuraciones.extraConfig.urlImagenes[1]
var imag6 = imagen6.substring(2)
var imagen7 = json.Terapia.Acciones[2].accion.configuraciones.basicas.oraciones[0].imagen
var imag7 = imagen7.substring(2)
var imagen8 = json.Terapia.Acciones[2].accion.configuraciones.basicas.oraciones[1].imagen
var imag8 = imagen8.substring(2)
var imagen9 = json.Terapia.Acciones[2].accion.configuraciones.basicas.preguntas[0].urlImagen
var imag9 = imagen9.substring(2)
var imagen10 = json.Terapia.Acciones[2].accion.configuraciones.basicas.preguntas[1].urlImagen
var imag10 = imagen10.substring(2)
var imagenes = [imag1,imag2,imag3,imag4,imag5,imag6,imag7,imag8,imag9,imag10]


var Client = require('ssh2').Client;
var connSettings = {
   host: '201.159.223.162',
   port: 22, // Normal is 22 port
   username: 'ms_edge',
   password: 'Password$2020.'

};

var conn = new Client();
conn.on('ready', function() {
 conn.sftp(function(err, sftp) {
      if (err) throw err;
   
  // for (var i = 0; i < 10; i++) {

      var moveFrom = "/home/ms_edge/imagenes/"+imag1;
 var moveTo = "imagenes/"+imag1;

     var moveTo2 = "imagenes/"+imag2;
  var moveFrom2 = "/home/ms_edge/imagenes/"+imag2;

      var moveTo3 = "imagenes/"+imag3;
  var moveFrom3 = "/home/ms_edge/imagenes/"+imag3;

      var moveTo4 = "imagenes/"+imag4;
  var moveFrom4 = "/home/ms_edge/imagenes/"+imag4;

      var moveTo5 = "imagenes/"+imag5;
  var moveFrom5 = "/home/ms_edge/imagenes/"+imag5;

      var moveTo6 = "imagenes/"+imag6;
  var moveFrom6 = "/home/ms_edge/imagenes/"+imag6;

      var moveTo7 = "imagenes/"+imag7;
  var moveFrom7 = "/home/ms_edge/imagenes/"+imag7;

      var moveTo8 = "imagenes/"+imag8;
  var moveFrom8 = "/home/ms_edge/imagenes/"+imag8;

      var moveTo9 = "imagenes/"+imag9;
  var moveFrom9 = "/home/ms_edge/imagenes/"+imag9;

      var moveTo10 = "imagenes/"+imag10;
  var moveFrom10 = "/home/ms_edge/imagenes/"+imag10;

     



sftp.fastGet(moveFrom, moveTo , {}, function(downloadError){
          if(downloadError) throw downloadError;

          console.log("Succesfully downloaded");

})



sftp.fastGet(moveFrom2, moveTo2 , {}, function(downloadError){
         if(downloadError) throw downloadError;

          console.log("Succesfully downloaded");

})

sftp.fastGet(moveFrom3, moveTo3 , {}, function(downloadError){
          if(downloadError) throw downloadError;

          console.log("Succesfully downloaded");

})

//sftp.fastGet(moveFrom4, moveTo4 , {}, function(downloadError){
 //         if(downloadError) throw downloadError;

//          console.log("Succesfully downloaded");

//})

sftp.fastGet(moveFrom5, moveTo5 , {}, function(downloadError){
         if(downloadError) throw downloadError;

         console.log("Succesfully downloaded");

})

sftp.fastGet(moveFrom6, moveTo6 , {}, function(downloadError){
         if(downloadError) throw downloadError;

          console.log("Succesfully downloaded");

})

sftp.fastGet(moveFrom7, moveTo7 , {}, function(downloadError){
         if(downloadError) throw downloadError;

         console.log("Succesfully downloaded");

})

sftp.fastGet(moveFrom8, moveTo8 , {}, function(downloadError){
         if(downloadError) throw downloadError;

         console.log("Succesfully downloaded");

})

sftp.fastGet(moveFrom9, moveTo9 , {}, function(downloadError){
          if(downloadError) throw downloadError;

          console.log("Succesfully downloaded");

})

sftp.fastGet(moveFrom10, moveTo10 , {}, function(downloadError){
         if(downloadError) throw downloadError;

         console.log("Succesfully downloaded");

})

//}



  })
  }).connect(connSettings)









//if( fs.existsSync(id)){
//var data = fs.readFileSync(id)
//console.log("Synchronous read: " + data.toString());

//wind.webContents.on('dom-ready', () => {
 //       wind.webContents.send('terapia', data.toString() )
    
     
 //   })
}

//wind.loadFile('terapias.html');
    
   if (descargar()){
  wind.loadFile('terapias.html');}

//descargar()



ipcMain.on('terminar',(event, args) => {
var dat = args.today
var respuestas = args.respuestas
console.log(respuestas)
var respuesta1 = respuestas[0]
var respuesta2 = respuestas[1]
var respuesta3 = respuestas[2]
var respuesta4 = respuestas[3]
var oracion1 = respuestas[4]
var oracion2 = respuestas[5]


//RESULTADOS
var today = new Date();
  var res = Math.abs(today - dat) / 1000;
 var hours = Math.floor(res / 3600) % 24;    
var minutes = Math.floor(res / 60) % 60;
  var seconds = Math.floor(res % 60);
var tiempo =hours+":"+minutes+":"+seconds 


var fs = require('fs');

try{
var resultados = fs.readFileSync("resultado")
var resultadoOraciones = fs.readFileSync("resultadoOraciones")
}
catch(err){
var hola= "hi"
 wind.webContents.send('nofile', hola )
}
console.log(resultados)
var result = resultados.toString().split(',');
result = result.reverse()



var aciertos = 0
var fallos = 0


for (var i = 0; i < result.length; i+=1) {

 var caso = result[i];
var caso1 = result[i+1];
var caso2 = result[i+2];



switch (caso) {

  case 'PREGUNTAS':
    switch(caso1){
	case '1':
		if(respuesta1[respuesta1.length -1] === caso2[caso2.length -1]){
aciertos++;


console.log("RESPUESTA"+ respuesta1[respuesta1.length -1] +"OTRA"+caso2[caso2.length -1] )
}
		else{
fallos++;
}
break;

	case '2':
		if(respuesta2[respuesta2.length -1] === caso2[caso2.length -1]){
aciertos++;
}
		else{
fallos++;
}
break;

	case '3':
		if(respuesta3[respuesta3.length -1] === caso2[caso2.length -1]){
aciertos++;
}
		else{
fallos++;
}
break;

	case '4':
		if(respuesta4[respuesta4.length -1] === caso2[caso2.length -1]){
aciertos++;
}
		else{
fallos++;
}
break;

default:
	console.log("NO es respuesta")
} 
    break;



  case 'SILABAS':
   switch(caso1){

	case 'silabas1':
	if(caso2 === "fig1" ||caso2==="fig2"){
	aciertos++;}
	else{
	fallos++;
	}
	
	break;
	
	case 'silaba2':
    	if(caso2 === "fig3" || caso2 ==="fig4"	){
	aciertos++;}
	else{
	fallos++;
	}
  break;
  

  default:
    console.log("NO es respuesta")
}
break;

 default:
	console.log("NO es respuesta")
}

}


var indice = resultadoOraciones.indexOf("DESORDENADA", resultadoOraciones.indexOf("DESORDENADA") + 1);
var indice1 = resultadoOraciones.indexOf("0");
var indice2 = resultadoOraciones.indexOf("0", resultadoOraciones.indexOf("0") + 1);
var desordenada1 = resultadoOraciones.toString().substring(12,indice)
var desordenada2 =  resultadoOraciones.toString().substring(indice+12,indice1)
var cambios1 = resultadoOraciones.toString().substring(indice1,indice2)
var cambios2 = resultadoOraciones.toString().substring(indice2)

desordenada1 = desordenada1.split(',');
desordenada2 = desordenada2.split(',');
cambios1 = cambios1.split(',');
cambios2 = cambios2.split(',');




console.log("ORACIONESSSSS "+cambios1)
console.log("ORACIONESSSSS "+cambios2)

for (var i = 0; i < cambios1.length; i+=1) {
	switch(cambios1[i]){

	case '0':
	var tmp = desordenada1[0]
var index = desordenada1.indexOf(cambios1[i+1])
	desordenada1[0] = cambios1[i+1]
	
	desordenada1[index]= tmp
	break;
	
	case '1':
var tmp = desordenada1[1]
var index = desordenada1.indexOf(cambios1[i+1])
	desordenada1[1] = cambios1[i+1]
	
	desordenada1[index]= tmp
break;


	case '2':
var tmp = desordenada1[2]
var index = desordenada1.indexOf(cambios1[i+1])
	desordenada1[2] = cambios1[i+1]

	desordenada1[index]= tmp
	break;

case '3':
var tmp = desordenada1[3]
var index = desordenada1.indexOf(cambios1[i+1])
	desordenada1[3] = cambios1[i+1]

	desordenada1[index]= tmp
	break;

case '4':
var tmp = desordenada1[4]
var index = desordenada1.indexOf(cambios1[i+1])
	desordenada1[4] = cambios1[i+1]

	desordenada1[index]= tmp
	break;

	default:
    	console.log("NO es respuesta")
}
}


for (var i = 0; i < cambios2.length; i+=1) {
	switch(cambios2[i]){

		case '0':
var tmp = desordenada2[0]
var index = desordenada2.indexOf(cambios2[i+1])
	desordenada2[0] = cambios2[i+1]
	desordenada2[index]= tmp
	break;
	
	case '1':
var tmp = desordenada2[1]
var index = desordenada2.indexOf(cambios2[i+1])
	desordenada2[1] = cambios2[i+1]
	
	desordenada2[index]= tmp

break;


	case '2':
var tmp = desordenada2[2]
var index = desordenada2.indexOf(cambios2[i+1])
	desordenada2[2] = cambios2[i+1]

	desordenada2[index]= tmp
	break;

case '3':
var tmp = desordenada2[3]
var index = desordenada2.indexOf(cambios2[i+1])
	desordenada2[3] = cambios2[i+1]
	
	desordenada2[index]= tmp
break;

case '4':
var tmp = desordenada2[4]
var index = desordenada2.indexOf(cambios2[i+1])
	desordenada2[4] = cambios2[i+1]
	
	desordenada2[index]= tmp
break;
	
	default:
    	console.log("NO es respuesta")
}
}




desordenada1 = desordenada1.toString().replace(/,/g," ")
desordenada2 = desordenada2.toString().replace(/,/g," ")


if(desordenada1 === oracion1)
{
aciertos++
}
else
{
fallos++
}

if(desordenada2 === oracion2)
{
aciertos++
}
else
{
fallos++
}


console.log("ORACIONESSSSS "+desordenada1.toString())
console.log("ORACIONESSSSS "+desordenada2.toString())

console.log("ACIERTOS "+aciertos+"FALLOS "+fallos)










var termino = {
  id: id,
  paciente: datos.idp,
  tiempo: tiempo,
  aciertos:aciertos,
  fallos:fallos 
}
async function makePostRequest2() {
console.log(termino)
await rpc.post('http://localhost:8000/api/actualizar',termino);
}


fs.unlinkSync('resultado');
fs.unlinkSync('resultadoOraciones');

makePostRequest2()
//wind.close()
})






})


}
// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.whenReady().then(() => {
  createWindow()
  


  app.on('activate', function () {
    // On macOS it's common to re-create a window in the app when the
    // dock icon is clicked and there are no other windows open.
    if (BrowserWindow.getAllWindows().length === 0) createWindow()



  })
})

// Quit when all windows are closed, except on macOS. There, it's common
// for applications and their menu bar to stay active until the user quits
// explicitly with Cmd + Q.
app.on('window-all-closed', function () {
  if (process.platform !== 'darwin') app.quit()
})