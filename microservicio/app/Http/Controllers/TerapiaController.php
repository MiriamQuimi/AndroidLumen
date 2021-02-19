<?php

namespace App\Http\Controllers;

use Laravel\Lumen\Routing\Controller as BaseController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Storage;

use App\Usuario;
use App\Paciente;
use App\TerapiaPaciente;
use App\TerapiaLogopeda;
use App\Terapia;
use App\Logopeda;
use App\TipoDislexia;
use App\Archivo;


class TerapiaController extends Controller
{
   public function usuario(Request $request) {

       
        $id = $request->input('id');   

$tp = TerapiaPaciente::where('idpaciente',$id)->where('estadoexistencia','A')->get();
$terapia = new \Illuminate\Database\Eloquent\Collection;
//$td = new \Illuminate\Database\Eloquent\Collection;
foreach($tp as $tp){
$terapia = $terapia->concat(Terapia::where('idterapia',$tp->idterapia)->get());
}

$count = $terapia->count();
if ($count>1){

$td = TipoDislexia::where('idtipodislexia',$terapia[0]->idtipodislexia)->get();
$td = $td->concat(TipoDislexia::where('idtipodislexia',$terapia[1]->idtipodislexia)->get());
}
elseif(!$terapia->isEmpty()){
$td = TipoDislexia::where('idtipodislexia',$terapia[0]->idtipodislexia)->get();

$respuesta = response()->json(['data' => [ 'terapias' => $terapia ,'tipodislexia' => $td[0]->nombre ]], 200);
Storage::put('terapias', $respuesta);

return response()->json(['data' => [ 'terapias' => $terapia ,'tipodislexia' => $td[0]->nombre ]], 200);

}
elseif($terapia->isEmpty()){
$respuesta = response()->json(['data'=>"Sin Terapias"]);
Storage::put('terapias', $respuesta);
return response()->json(['data'=>"Sin Terapias"]);
     
}
$respuesta = response()->json(['data' => [ 'terapias' => $terapia ,'tipodislexia' => $td[0]->nombre,'tipodislexia1' => $td[1]->nombre ]], 200);
Storage::put('terapias', $respuesta);
         return response()->json(['data' => [ 'terapias' => $terapia ,'tipodislexia' => $td[0]->nombre,'tipodislexia1' => $td[1]->nombre ]], 200);
        
 }



public function descargarTerapia(Request $request) {


        $idt = $request->input('id');
	


$terapia = Terapia::where('idterapia',$idt)->get();
$json =  $terapia[0]->json;
$manage = json_decode($json, true);
$imagenes = $manage['Terapia']['Acciones'][0]['accion']['configuraciones']['extraConfig']['urlImagenes'];
foreach ($imagenes as &$valor) {
    $valor = substr($valor, 2);
}
unset($valor); 
$imagenes1 = $manage['Terapia']['Acciones'][1]['accion']['configuraciones']['extraConfig']['urlImagenes'];
foreach ($imagenes1 as &$valor) {
    $valor = substr($valor, 2);
}
unset($valor);

$imagenes2 = $manage['Terapia']['Acciones'][2]['accion']['configuraciones']['basicas']['oraciones'][0]['imagen'];
$imagenes2 = substr($imagenes2, 2);
$imagenes3 = $manage['Terapia']['Acciones'][2]['accion']['configuraciones']['basicas']['oraciones'][1]['imagen'];
$imagenes3 = substr($imagenes3, 2);
$imagenes4 = $manage['Terapia']['Acciones'][2]['accion']['configuraciones']['basicas']['preguntas'][0]['urlImagen'];
$imagenes4 = substr($imagenes4, 2);
$imagenes5 = $manage['Terapia']['Acciones'][2]['accion']['configuraciones']['basicas']['preguntas'][1]['urlImagen'];
$imagenes5 = substr($imagenes5, 2);
$imagenes = array_merge($imagenes, $imagenes1);


array_push($imagenes,$imagenes2,$imagenes3,$imagenes4,$imagenes5);


foreach ($imagenes as &$valor) {
    $valor = Archivo::where('etiqueta',$valor)->get();
	
}
unset($valor);


$rutaimagen = [];

//$imagenes[0][0]->urlarchivo


foreach ($imagenes as &$valor) {
    $valor = $valor[0]->urlarchivo;
}
unset($valor);


return response()->json(['json' => $terapia[0]->json, 'rutaimagenes'=>$imagenes]);

}







  public function actualizarTerapia(Request $request) {


        $idt = $request->input('id');
	 $idp = $request->input('paciente');
 $tiempo = $request->input('tiempo');
 $aciertos = $request->input('aciertos');
 $fallos = $request->input('fallos');

$porcentaje = ($aciertos)*100/($aciertos+$fallos);
$porcentaje = (int)$porcentaje;


$affected = TerapiaPaciente::where('idpaciente',$idp)->where('idterapia',$idt)->update(['estadoexistencia' => 'F', 'tiempo' =>$tiempo, 'aciertos' =>$aciertos,'fallos' =>$fallos,'porcentaje' =>$porcentaje]);

	 return response()->json(['update' => $affected]);
}
}