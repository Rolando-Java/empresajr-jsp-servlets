
var date = new Date();

var day = date.getDate();
var month = date.getMonth() + 1;
var year = date.getFullYear();

if (month < 10) month = "0" + month;
if (day < 10) day = "0" + day;

var today = year + "-" + month + "-" + day;       
var allinputs  = document.querySelectorAll("input");
var myLength = allinputs.length;
var input;

for (var i = 0; i < myLength; ++i) {
    input = allinputs[i];
    input.setAttribute("value", today);
  }


function comparar(id) {
    var fecha_input = document.getElementById(id).value;
    var fecha_actual = new Date();
    var fechaActualString  = (fecha_actual.getMonth()+1).toString() + '-' 
    + fecha_actual.getDate().toString() + '-' + fecha_actual.getFullYear().toString();
    
    fecha_input = new Date(fecha_input);
    fecha_actual = new Date(fechaActualString);
    if(fecha_input < fecha_actual){
        Swal.fire({
            icon: 'error',
            text: 'Ingrese por favor una fecha mayor o igual a la actual',
        });

        document.getElementById(id).value = today;
    }
}