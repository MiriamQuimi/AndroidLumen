package com.example.temonet;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public  class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         Button button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EditText user;
                EditText pass;
                String usuario;

                String contraseña;

                user = findViewById(R.id.usuario);
                pass = findViewById(R.id.contraseña);
                usuario = user.getText().toString();
                contraseña = pass.getText().toString();


                try {
                    loginRequest(usuario, contraseña);



                } catch (Exception e) {
                    e.printStackTrace();

                }
            }


            public  void  loginRequest(final String usuario, String contraseña) {
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    String URL = "http://192.168.100.188:8000/api/login";
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("email", usuario);
                    jsonBody.put("password", contraseña);
                    RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());



                    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                          try {
                                JSONObject data = response.getJSONObject("data");


                              BufferedReader reader = null;
                              try {
                                  reader = new BufferedReader(
                                          new InputStreamReader(getAssets().open("usuario"), "UTF-8"));

                                  // do reading, usually loop until end of file reading

                                  int c;
                                  String temp = "";
                                  while ((c = reader.read()) != -1) {
                                      temp = temp + Character.toString((char) c);
                                  }

                                  reader.close();

                                      int i = temp.indexOf("{");
                                      temp = temp.substring(i);

                                      //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                     // SharedPreferences.Editor editor = prefs.edit();
                                     // Log.d("USUARIOFILE",temp);
                                    // editor.putString("usuario", temp); //InputString: from the EditText
                                   //  editor.commit();
                                  } catch (UnsupportedEncodingException e) {
                                  e.printStackTrace();
                                  Log.d("Error", e.toString());
                              } catch (IOException e) {
                                  e.printStackTrace();
                                  Log.d("Error", e.toString());
                              }


                              SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                             SharedPreferences.Editor editor = prefs.edit();
                             editor.putString("usuario", response.toString()); //InputString: from the EditText
                              editor.commit();

                                Log.i("VOLLEY", response.toString());
                              Intent intent = new Intent(getApplicationContext(), Escritorio.class);
                              startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                           }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }



                        @Override
                        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                            String responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                                // can get more details such as response.headers
                            }
                            return super.parseNetworkResponse(response);
                        }
                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }







    });


}


}

