function selectOnlyThis(p,id) {
  for (var i = 1;i <= 3; i++)
    {
        document.getElementById("Respuesta" + p + i).checked = false;
    }
    document.getElementById(id).checked = true;
fs.appendFile('resultado', id+","+p+","+"PREGUNTAS"+",",function(err) {
  //  console.log( p+","+id)
if (err) {
      return console.error(err);
   }
})
}


