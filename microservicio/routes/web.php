<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});

// cors problem....
$router->options(
    '/{any:.*}', 
    [
        'middleware' => ['cors'], 
        function (){ 
            return response(['status' => 'success']); 
        }
    ]
);

$router->group(['prefix' => 'health'], function () use ($router) {

       $router->get('',  ['uses' => 'HealthController@heartbeat']);

});


$router->group(['prefix' => 'api'], function ($router) {

    $router->post('login', 'LoginController@login');
$router->get('login', 'LoginController@login');

    $router->get('usuarios', 'UsuarioController@index');
    $router->post('terapia', 'TerapiaController@usuario');
  $router->post('descargar', 'TerapiaController@descargarTerapia');

$router->post('actualizar', 'TerapiaController@actualizarTerapia');



$router->group(['middleware' => 'cors'], function($router){
    $router->post('/auth/login', function() {
        return response()->json([
            'message' => 'CORS OPTIONS Accepted.',
        ]);
    });
});
});