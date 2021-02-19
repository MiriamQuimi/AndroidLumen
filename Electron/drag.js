
  
var fs = require('fs');


function onDragStart(event) {

fs.appendFile('resultado', event.target.id+",",function(err) {
    console.log(event.target.id)
if (err) {
      return console.error(err);
   }
})
  event
    .dataTransfer
    .setData('text/plain', event.target.id);

 
}


function onDragOver(event) {
  event.preventDefault();
}

function onDrop(event) {
  const id = event
    .dataTransfer
    .getData('text');


const draggableElement = document.getElementById(id);
  const dropzone = event.target;


fs.appendFile('resultado', event.target.id+","+"SILABAS"+",",function(err) {
  console.log(event.target.id)
 if (err) {
      return console.error(err);
   }
})


  dropzone.appendChild(draggableElement);

 event
    .dataTransfer
    .clearData();
}


function drag(event) {

 source = event.target;

    event
    .dataTransfer
    .setData('text/html', event.target.innerHTML);
event.dataTransfer.effectAllowed = "move";


  }



function drop(event){
      
source.innerHTML  = event.target.innerHTML ;
event.target.innerHTML= event.dataTransfer.getData('text/html');
fs.appendFile('resultadoOraciones', event.target.id+","+event.target.innerHTML+",",function(err) {
  console.log(event.target.innerHTML)
console.log(event.target.id)

 if (err) {
      return console.error(err);
   }
})
}


