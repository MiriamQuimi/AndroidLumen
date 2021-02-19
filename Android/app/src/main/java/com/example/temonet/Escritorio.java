package com.example.temonet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Escritorio extends AppCompatActivity {
    String fechaactual = Calendar.getInstance().getTime().toLocaleString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String usuario = prefs.getString("usuario", "no id"); //no id: default value
        setContentView(R.layout.escritorio);
        try {
            JSONObject objt = new JSONObject(usuario);
            String jsonstring = objt.getString("data");
            JSONObject jsonObject = new JSONObject(jsonstring);
            String user = jsonObject.getString("user");
            String logopeda = jsonObject.getString("logopeda");
            final String id = jsonObject.getString("id");
            TextView fecha = findViewById(R.id.FECHA);
            TextView us = findViewById(R.id.usuario);
            TextView logopeda1 = findViewById(R.id.logopeda);
            logopeda1.setText("Logopeda Asignado: " + logopeda);
            us.setText("Bienvenido " + user);
            fecha.setText(fechaactual);


            Button listar = findViewById(R.id.listar);
            listar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    terapiaRequest(id);
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("idp", id);
                    editor.commit();


                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }


        final Button descargar = findViewById(R.id.descargar);
        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Terapia.class);
                startActivity(intent);
            }
        });


    }

    public void init(String tipodislexia, JSONArray json) throws JSONException {
        Log.d("tamano", String.valueOf(json.length()));
        TableLayout stk = findViewById(R.id.terapias);
        stk.removeAllViews();
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);

            TableRow tbrow0 = new TableRow(this);
            tbrow0.setPadding(0, 0, 0, 2);
            TextView tv0 = new TextView(this);
            tv0.setText(" ID Terapia ");
            tv0.setPadding(0, 0, 4, 3);
            tv0.setBackgroundColor(Color.parseColor("#B247B403"));
            tv0.setTextColor(Color.BLACK);
            tbrow0.addView(tv0);
            TextView t0v = new TextView(this);
            t0v.setText(obj.getString("idterapia"));
            t0v.setTextColor(Color.BLACK);
            tbrow0.addView(t0v);

            TableRow tbrow1 = new TableRow(this);
            tbrow1.setPadding(0, 0, 0, 2);
            TextView tv1 = new TextView(this);
            tv1.setText(" Nombre ");
            tv1.setPadding(0, 0, 4, 3);
            tv1.setBackgroundColor(Color.parseColor("#B247B403"));
            tv1.setTextColor(Color.BLACK);
            tbrow1.addView(tv1);
            TextView t1v = new TextView(this);
            t1v.setText(obj.getString("nombreterapia"));
            t1v.setTextColor(Color.BLACK);

            tbrow1.addView(t1v);

            TableRow tbrow2 = new TableRow(this);
            tbrow2.setPadding(0, 0, 0, 2);
            TextView tv2 = new TextView(this);
            tv2.setText(" Descripción ");
            tv2.setBackgroundColor(Color.parseColor("#B247B403"));
            tv2.setPadding(0, 0, 4, 3);
            tv2.setTextColor(Color.BLACK);
            tbrow2.addView(tv2);
            TextView t2v = new TextView(this);
            t2v.setText(obj.getString("descripcion"));
            t2v.setTextColor(Color.BLACK);

            tbrow2.addView(t2v);

            TableRow tbrow3 = new TableRow(this);
            tbrow3.setPadding(0, 0, 0, 2);
            TextView tv3 = new TextView(this);
            tv3.setText(" Tipo Dislexia ");
            tv3.setBackgroundColor(Color.parseColor("#B247B403"));
            tv3.setTextColor(Color.BLACK);
            tv3.setPadding(0, 0, 4, 3);
            tbrow3.addView(tv3);
            TextView t3v = new TextView(this);
            t3v.setText(tipodislexia);
            t3v.setTextColor(Color.BLACK);

            tbrow3.addView(t3v);

            TableRow tbrow4 = new TableRow(this);
            tbrow4.setPadding(0, 0, 0, 2);
            TextView tv4 = new TextView(this);
            tv4.setText(" Estado ");
            tv4.setBackgroundColor(Color.parseColor("#B247B403"));
            tv4.setTextColor(Color.BLACK);
            tbrow4.addView(tv4);
            TextView t4v = new TextView(this);
            tv4.setPadding(0, 0, 4, 3);
            t4v.setText(obj.getString("estadoexistencia"));
            t4v.setTextColor(Color.BLACK);


            TableRow tbrow5 = new TableRow(this);
            TextView tv5 = new TextView(this);
            tbrow5.addView(tv5);

            tbrow4.addView(t4v);
            stk.addView(tbrow0);
            stk.addView(tbrow1);
            stk.addView(tbrow2);
            stk.addView(tbrow3);
            stk.addView(tbrow4);
            stk.addView(tbrow5);

            tbrow0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Drawable background = view.getBackground();
                    Log.d("Back",background.toString());
                    view.setBackgroundColor(Color.LTGRAY);
                    TableRow t = (TableRow) view;
                    TextView firstTextView = (TextView) t.getChildAt(1);
                    String firstText = firstTextView.getText().toString();
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("id", firstText);
                    editor.commit();
                }
            });
            tbrow0.setBackgroundColor(Color.TRANSPARENT);
        }


    }



    public void init(String tipodislexia)  {
        Log.d("tamano", tipodislexia);


            TableLayout stk = findViewById(R.id.terapias);
            stk.removeAllViews();
            TableRow tbrow0 = new TableRow(this);
            tbrow0.setPadding(0, 0, 0, 2);
            TextView tv0 = new TextView(this);
            tv0.setText(" ID Terapia ");
            tv0.setPadding(0, 0, 4, 3);
            tv0.setBackgroundColor(Color.parseColor("#B247B403"));
            tv0.setTextColor(Color.BLACK);
            tbrow0.addView(tv0);
            TextView t0v = new TextView(this);
            t0v.setText(tipodislexia);
            t0v.setTextColor(Color.BLACK);
            tbrow0.addView(t0v);

            TableRow tbrow1 = new TableRow(this);
            tbrow1.setPadding(0, 0, 0, 2);
            TextView tv1 = new TextView(this);
            tv1.setText(" Nombre ");
            tv1.setPadding(0, 0, 4, 3);
            tv1.setBackgroundColor(Color.parseColor("#B247B403"));
            tv1.setTextColor(Color.BLACK);
            tbrow1.addView(tv1);

            TableRow tbrow2 = new TableRow(this);
            tbrow2.setPadding(0, 0, 0, 2);
            TextView tv2 = new TextView(this);
            tv2.setText(" Descripción ");
            tv2.setBackgroundColor(Color.parseColor("#B247B403"));
            tv2.setPadding(0, 0, 4, 3);
            tv2.setTextColor(Color.BLACK);
            tbrow2.addView(tv2);



            TableRow tbrow3 = new TableRow(this);
            tbrow3.setPadding(0, 0, 0, 2);
            TextView tv3 = new TextView(this);
            tv3.setText(" Tipo Dislexia ");
            tv3.setBackgroundColor(Color.parseColor("#B247B403"));
            tv3.setTextColor(Color.BLACK);
            tv3.setPadding(0, 0, 4, 3);
            tbrow3.addView(tv3);


                       TableRow tbrow4 = new TableRow(this);
            tbrow4.setPadding(0, 0, 0, 2);
            TextView tv4 = new TextView(this);
            tv4.setText(" Estado ");
            tv4.setBackgroundColor(Color.parseColor("#B247B403"));
            tv4.setTextColor(Color.BLACK);
            tbrow4.addView(tv4);

            stk.addView(tbrow0);
            stk.addView(tbrow1);
            stk.addView(tbrow2);
            stk.addView(tbrow3);
            stk.addView(tbrow4);

    }

    public  void  terapiaRequest(final String id)
    {
        try {
            RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id", id);
            String URL1 = "http://192.168.100.188:8000/api/terapia";

            JsonObjectRequest stringRequest1 = new JsonObjectRequest(Request.Method.POST, URL1,jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public   void onResponse(JSONObject response)  {

                    JSONObject data = null;
                    try {

                            BufferedReader reader = null;
                            try {
                                reader = new BufferedReader(
                                        new InputStreamReader(getAssets().open("terapias"), "UTF-8"));


                                int c;
                                String temp = "";
                                while ((c = reader.read()) != -1) {
                                    temp = temp + Character.toString((char) c);
                                }

                                reader.close();
                                int i = temp.indexOf("{");
                                temp = temp.substring(i);

                                data = response.getJSONObject("data");
                                //data = new JSONObject(temp);

                                String dislexia = data.getString("tipodislexia");
                                String terapias = data.getString("terapias");
                                JSONArray terapia = new JSONArray(terapias);
                                init(dislexia, terapia);

                            }catch (JSONException e) {
                                    e.printStackTrace();

                                        String datas = response.getString("data");
                                        init(datas);


                            } catch (IOException e) {
                                Log.d("ERRROR",e.toString());
                            } finally {
                                if (reader != null) {
                                    try {
                                        reader.close();
                                    } catch (IOException e) {
                                        Log.d("ERRROR",e.toString());
                                    }
                                }


                            }


                        Log.i("VOLLEY", response.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                        try {
                            String datas = response.getString("data");
                            init(datas);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                       }

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());

                }
            })

            {
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
            stringRequest1.setRetryPolicy(new DefaultRetryPolicy( 50000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue1.add(stringRequest1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




    }
