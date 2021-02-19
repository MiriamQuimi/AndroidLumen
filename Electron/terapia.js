const { ipcRenderer } = require('electron')

window.addEventListener('DOMContentLoaded', () => {
  const replaceText = (selector, text) => {
    const element = document.getElementById(selector)
    if (element) element.innerText = text
  }

var today = new Date();


ipcRenderer.on('terapia', function (event, args){   
//var id = args.id
var json = JSON.parse(args);
console.log(json);	
var text= json.Terapia.Acciones[0].accion.configuraciones.textoConfig.textos[0].texto
var tletra = json.Terapia.Acciones[0].accion.configuraciones.textoConfig.textos[0].tamanioLetra
var silabas= json.Terapia.Acciones[0].accion.configuraciones.basicas.silabas
var fondo= json.Terapia.Acciones[0].accion.configuraciones.extraConfig.colorFondo
var texto = text.replace("{silabas}", silabas)
var pregunta1 = json.Terapia.Acciones[1].accion.configuraciones.basicasConfig.preguntas[0].pregunta
var respuestas1 = json.Terapia.Acciones[1].accion.configuraciones.basicasConfig.preguntas[0].respuestas
var respuestaCorrecta1 = json.Terapia.Acciones[1].accion.configuraciones.basicasConfig.preguntas[0].respuestaCorrecta
var pregunta2 = json.Terapia.Acciones[1].accion.configuraciones.basicasConfig.preguntas[1].pregunta
var respuestas2 = json.Terapia.Acciones[1].accion.configuraciones.basicasConfig.preguntas[1].respuestas
var respuestaCorrecta2 = json.Terapia.Acciones[1].accion.configuraciones.basicasConfig.preguntas[1].respuestaCorrecta
var text1= json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[0].texto
var tletra1 = json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[0].tamanioLetra
var text2= json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[1].texto
var tletra2 = json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[1].tamanioLetra
var text3= json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[2].texto
var tletra3 = json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[2].tamanioLetra
var text4= json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[3].texto
var tletra4 = json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[3].tamanioLetra
var text5= json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[4].texto
var tletra5 = json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[4].tamanioLetra
var text6= json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[5].texto
var tletra6 = json.Terapia.Acciones[1].accion.configuraciones.textoConfig.textos[5].tamanioLetra
var fondo1= json.Terapia.Acciones[1].accion.configuraciones.extraConfig.colorFondo
var oracion1 = json.Terapia.Acciones[2].accion.configuraciones.basicas.oraciones[0].oracion
var oracion2 = json.Terapia.Acciones[2].accion.configuraciones.basicas.oraciones[1].oracion
var pregunta3 = json.Terapia.Acciones[2].accion.configuraciones.basicas.preguntas[0].pregunta
var respuestas3 = json.Terapia.Acciones[2].accion.configuraciones.basicas.preguntas[0].respuestas
var respuestaCorrecta3 = json.Terapia.Acciones[2].accion.configuraciones.basicas.preguntas[0].respuestaCorrecta
var pregunta4 = json.Terapia.Acciones[2].accion.configuraciones.basicas.preguntas[1].pregunta
var respuestas4 = json.Terapia.Acciones[2].accion.configuraciones.basicas.preguntas[1].respuestas
var respuestaCorrecta4 = json.Terapia.Acciones[2].accion.configuraciones.basicas.preguntas[1].respuestaCorrecta
var text7= json.Terapia.Acciones[2].accion.configuraciones.textoConfig.textos[0].texto
var tletra7 = json.Terapia.Acciones[2].accion.configuraciones.textoConfig.textos[0].tamanioLetra
var text8= json.Terapia.Acciones[2].accion.configuraciones.textoConfig.textos[1].texto
var tletra8 = json.Terapia.Acciones[2].accion.configuraciones.textoConfig.textos[1].tamanioLetra
var fondo2= json.Terapia.Acciones[2].accion.configuraciones.extraConfig.colorFondo
var imagen1= json.Terapia.Acciones[0].accion.configuraciones.extraConfig.urlImagenes[0]
var imag1 = imagen1.substring(2)
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

console.log(imag1)







//function limpiar(id){
//   var imag = $(id).siblings(`div[id="imagenes"]`)
// $(imag).attr('style','');
//console.log("HOLAAAAAAAAAAAA"+imag.toString())
//}



function shuffle(array) {
  var currentIndex = array.length, temporaryValue, randomIndex;

  // While there remain elements to shuffle...
  while (0 !== currentIndex) {

    // Pick a remaining element...
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    // And swap it with the current element.
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}









function load(oracion){
    var words = oracion.split(' ');
var desordenadas = shuffle(words)
var fs = require('fs');
fs.appendFile('resultadoOraciones',"DESORDENADA"+","+ desordenadas+",",function(err) {

 if (err) {
      return console.error(err);
   }
})

	


 return `
${desordenadas.map((word,i) => `<div id=${i} = style=" border: solid palegreen; background-color: transparent; float: left; width: 15% ;  transform: translate(10%, 200%)" draggable="true"  ondragstart="drag(event);" ondragover="onDragOver(event);" ondrop="drop(event);" >${word}</div>`).join("")}
`;
} 














document.getElementById("app").innerHTML = `

<div style="background-color:${fondo}; border: 8px solid white">
  <h1 style="font-size: ${tletra}"> ${texto} </h1>

<div  align ="center">
<button id ="limpiar" style="background-color:${fondo}; color:blue; border: solid ${fondo}"> Limpiar</button>
</div>
<br/>
<script src="drag.js"></script>
<div>
<div style=" background-color:${fondo}; float: left; width: 5%; height:150"> </div>
<div style="  float: left; background-color: white;  width: 20%; height:150;  text-align: center; font-size: 100">${silabas[0]}
 </div>
<div style=" background-color:${fondo}; float: left; width: 5%; height:150"  ondragover="onDragOver(event);" ondrop="onDrop(event);">   
</div>
<div id = "silaba1" style=" background-color:${fondo}; float: left; width: 70%; height:150;" ondragover="onDragOver(event);" ondrop="onDrop(event);" >  
</div>
</div>

<br/>

<div>
<div style=" background-color:${fondo}; float: left; width: 5%; height:150">
 </div>
<div align="left" style=" float: left; background-color: white; width:20%; height:150;  text-align: center; font-size: 100" >${silabas[1]}
 </div>
<div style=" background-color: ${fondo}; float: left; width: 5%; height:150"  ondragover="onDragOver(event);" ondrop="onDrop(event);"> 
</div>
<div id ="silaba2" style=" background-color: ${fondo}; float: left; width: 70%; height:150;" ondragover="onDragOver(event);" ondrop="onDrop(event);" > 
</div>
</div>

<br/><br/>
 <div id ="imagenes" align="center" >
  <img style =" border-style: dotted; border-color:red"id="fig1" draggable="true"  ondragstart="onDragStart(event);" src ="imagenes/${imag1}" width="200" height="150">
 <img style =" border-style: dotted; border-color:red"  id="fig2" draggable="true"  ondragstart="onDragStart(event);" src ="imagenes/${imag2}" width="200" height="150">
 <img style =" border-style: dotted; border-color:red"  id="fig3" draggable="true"  ondragstart="onDragStart(event);" src ="imagenes/${imag3}" width="200" height="150">
<img  style =" border-style: dotted; border-color:red" id="fig4" draggable="true"  ondragstart= "onDragStart(event);" src ="imagenes/${imag4}" width="200" height="150"> 
</div>
 </div>

 </div>


 <div>



 </div>


<div style="background-color:${fondo1}; border: 8px solid white">
  <h1 style="font-size: ${tletra1}"> ${text1} </h1>
 <h1 style="font-size: ${tletra2}"> ${text2} </h1>
 <h1 style="font-size: ${tletra5}"> ${text5} </h1>
 <h1 style="font-size: ${tletra6}"> ${text6} </h1>
 <h1 style="font-size: ${tletra3}"> ${text3} </h1>

<script src="preguntas.js"></script>
<p>${pregunta1}</p>
<div>
  <input type="checkbox" id="Respuesta11" value="${respuestas1[0]}"
        onclick="selectOnlyThis(1,this.id)" >
  <label for="${respuestas1[0]}">${respuestas1[0]}</label>
</div>

<div>
  <input type="checkbox" id="Respuesta12"  value="${respuestas1[1]}" onclick="selectOnlyThis(1,this.id)">
  <label for="${respuestas1[1]}">${respuestas1[1]}</label>
</div>

<div>
  <input type="checkbox" id="Respuesta13"  value="${respuestas1[2]}" onclick="selectOnlyThis(2,this.id)">
  <label for="${respuestas1[2]}">${respuestas1[2]}</label>
</div>


<p>${pregunta2}</p>
<div>
  <input type="checkbox" id="Respuesta21" value="${respuestas2[0]}"  onclick="selectOnlyThis(2,this.id)" 
         >
  <label for="${respuestas2[0]}">${respuestas2[0]}</label>
</div>

<div>
  <input type="checkbox" id="Respuesta22"  value="${respuestas2[1]}"  onclick="selectOnlyThis(2,this.id)" >
  <label for="${respuestas2[1]}">${respuestas2[1]}</label>
</div>

<div>
  <input type="checkbox" id="Respuesta23"  value="${respuestas2[2]}"  onclick="selectOnlyThis(2,this.id)" >
  <label for="${respuestas2[2]}">${respuestas2[2]}</label>
</div>

 <h1 style="font-size: ${tletra4}"> ${text4} </h1>


</div>












<div style="background-color:${fondo2}; border: 8px solid white">

<div>
<h1 style="font-size: ${tletra7}"> ${text7} </h1>

<div>
<button style="color:blue; background-color:${fondo2}; border: solid ${fondo2}"> Calificar</button>
</div>

<div >
<img id="fig2" style="float: left; border: 1px solid black" src ="imagenes/${imag7}" width="20%" height="150" >
<div id ="maindiv" style=" background-color: ${fondo2}; float: left; width: 79%; height:150; border: 1px solid black"  > 
${load(oracion1)}

</div>
</div>

<div style=" background-color: ${fondo2}"></div>


<div >
<img  id="fig3" style="float: left; border: 1px solid black" src ="imagenes/${imag8}" width="20%" height="150">

<div style=" background-color: ${fondo2}; float: left; width: 79%; height:150; border: 1px solid black" > 
${load(oracion2)}
</div>

</div>

</div>

<script src="preguntas.js"></script>
<h1 style="font-size: ${tletra8}"> ${text8} </h1>
<div style=" background-color:${fondo2}; float: left; width: 10%; height:150">
 </div>
<div style=" background-color:${fondo2}; float: left; width: 45%; height:150"> 

<div>
<img id="fig2" style="float: left; border: 1px solid black" src ="imagenes/${imag9}" width="25%" height="150" >

<div style=" background-color: ${fondo2}; float: left; width=25%; height=150 " >

<div>
  <input type="checkbox" id="Respuesta31" value="${respuestas3[0]}"  onclick="selectOnlyThis(3,this.id)" 
         >
  <label for="${respuestas3[0]}">${respuestas3[0]}</label>
</div>

<div>
  <input type="checkbox" id="Respuesta32"  value="${respuestas3[1]}"  onclick="selectOnlyThis(3,this.id)" >
  <label for="${respuestas3[1]}">${respuestas3[1]}</label>
</div>

<div>
  <input type="checkbox" id="Respuesta33"  value="${respuestas3[2]}"  onclick="selectOnlyThis(3,this.id)" >
  <label for="${respuestas3[2]}">${respuestas3[2]}</label>
</div>
</div>
</div>
</div>

<div style=" background-color:${fondo2}; float: left; width: 45%; height:150"> 
<div>
<img id="fig2" style="float: left; border: 1px solid black" src ="imagenes/${imag10}" width="25%" height="150" >

<div style=" background-color: ${fondo2}; float: left; width=25%; height=150 " >

<div>
  <input type="checkbox" id="Respuesta41" value="${respuestas4[0]}"  onclick="selectOnlyThis(4,this.id)" 
         >
  <label for="${respuestas4[0]}">${respuestas4[0]}</label>
</div>

<div>
  <input type="checkbox" id="Respuesta42"  value="${respuestas4[1]}"  onclick="selectOnlyThis(4,this.id)" >
  <label for="${respuestas4[1]}">${respuestas4[1]}</label>
</div>

<div>
  <input type="checkbox" id="Respuesta43"  value="${respuestas4[2]}"  onclick="selectOnlyThis(4,this.id)" >
  <label for="${respuestas4[2]}">${respuestas4[2]}</label>
</div>
</div>
</div>



</div>
</div>



</div>

`;




document.getElementById("terminar").addEventListener('click', (evt) => {
var datos = {
today: today,
respuestas:  [respuestaCorrecta1,respuestaCorrecta2,respuestaCorrecta3,respuestaCorrecta4,oracion1,oracion2]
}

ipcRenderer.send('terminar', datos);
console.log("cerrar")
})


})


ipcRenderer.on('nofile', function (event, args){   
const dialog = electron.remote.dialog; 
dialog.showMessageBox({ 
        // option Object 
        type: 'error', 
        buttons: [], 
        defaultId: 0, 
        icon: 'images/logotipo.png', 
        title: 'Terapia No Completada', 
        message: 'La Terapia no ha sido completada',  
        cancelId: 0, 
        noLink: false, 
        normalizeAccessKeys: false, 
    }).then(box => { 
        console.log('Button Clicked Index - ', box.response); 
        console.log('Checkbox Checked - ', box.checkboxChecked); 
    }).catch(err => { 
        console.log(err) 
    }); 


})


})
