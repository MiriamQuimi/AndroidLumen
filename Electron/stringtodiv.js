$('div').keyup(function(){
    var value = $(this).val();
    var words = value.split(/[\s,]+/);
	var desordenadas = words.sort()
    $('#maindiv').html("");
    desordenadas.forEach((x, index) => $('#maindiv').append('<div>'+x + '-' + (index+1) +'</div>'));
})
 