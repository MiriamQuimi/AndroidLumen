<?php

namespace App\Http\Controllers;

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


class LoginController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }

    public function login(Request $request) {

        $this->validate($request, [
            'email' => 'required',
            'password' => 'required'
        ]);
        
        
        $email = $request->input('email');
        $password = $request->input('password');



$user = Usuario::where('usuario', $email)->where('rol','PACIENTE')->first();


        if($user === null) {
            return response()->json(['error' => true, 'message' =>  "user not found!"], 401);
        }

        //get user's token
        $token = $user->api_token;

$id = Paciente::where('usuario', $email)->first();
$tp = TerapiaPaciente::where('idpaciente',$id->idpaciente)->where('estadoexistencia','A')->get();
$terapia = new \Illuminate\Database\Eloquent\Collection;
$tl = new \Illuminate\Database\Eloquent\Collection;

foreach($tp as $tp){
$terapia = $terapia->concat(Terapia::where('idterapia',$tp->idterapia)->get());
$tl = $tl->concat(TerapiaLogopeda::where('idterapia',$tp->idterapia)->get());
$logopeda = Logopeda::where('idlogopeda',$tl[0]->idlogopeda)->get();
}

if(!isset($logopeda)){



	 $respuesta = response()->json(['data' => [ 'success' => true, 'user' => $user->usuario , 'id' => $id->idpaciente, 'logopeda'=>  "No asignado" . $token]], 200);
Storage::put('usuario', $respuesta);
return $respuesta;

}

        if ($password === $user->passwd) {

             $respuesta = response()->json(['data' => [ 'success' => true, 'user' => $user->usuario , 'id' => $id->idpaciente, 'logopeda'=>  $logopeda[0]->usuario. $token]], 200);
Storage::put('usuario', $respuesta);
return  $respuesta;
        }

        return response()->json(['error' => true, 'message' => "Invalid Credential"], 401);
        
    }
}



