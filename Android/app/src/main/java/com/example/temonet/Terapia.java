package com.example.temonet;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Terapia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        final String id = prefs.getString("id", "no id");
        final String idp = prefs.getString("idp", "no id");
       final Date inicio = new Date();
        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    Log.d("id",id);
                    descargaRequest(id,idp,inicio);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);

        setContentView(R.layout.terapia);

       // Button actualizar = findViewById(R.id.terminar);
        //actualizar.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View view) {
        //        actualizarRequest(id, idp, hoy);
          //  }
      // });
    }


    public void descargaRequest(final String id, final String idp, final Date hoy) {

        try {
            RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id", id);
            String URL1 = "http://192.168.100.188:8000/api/descargar";

            JsonObjectRequest stringRequest1 = new JsonObjectRequest(Request.Method.POST, URL1,jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public   void onResponse(JSONObject response) throws JSONException {
                    JSONArray rutas = new JSONArray(response.getJSONArray("rutaimagenes").toString());
                   final String ruta1 = rutas.getString(0);
                      String ruta2 = rutas.getString(1);
                      String ruta3 = rutas.getString(2);
                       String ruta4 = rutas.getString(3);
                   String ruta5 = rutas.getString(4);
                   String ruta6 = rutas.getString(5);
                    String ruta7 = rutas.getString(6);
                    String ruta8 = rutas.getString(7);
                    String ruta9 = rutas.getString(8);


                    String user = "ms_edge";
                    String password = "Password$2020.";
                     String host = "201.159.223.162";


                     java.util.Properties config = new java.util.Properties();
                     config.put("StrictHostKeyChecking", "no");
                      try {


                      JSch jsch = new JSch();
                    Session session = jsch.getSession(user, host, 22);
                    session.setPassword(password);
                   session.setConfig(config);
                     session.setTimeout(150000);
                   //  session.connect();
                    if (session.isConnected()) {
                      Log.i("ssh", "Connected Successfully");
                     } else {
                         Log.i("ssh", "Connection Failed");
                      }


                      ChannelSftp channel = null;


                    //  channel =(ChannelSftp) session.openChannel("sftp");
                   //  channel.connect();



               //     InputStream in = channel.get(ruta1);
                   //     final Bitmap bitmap1 = BitmapFactory.decodeStream(in);

                      //   InputStream in2 = channel.get(ruta1);
                      //    final Bitmap bitmap2 = BitmapFactory.decodeStream(in2);
                       //  InputStream in3 = channel.get(ruta1);
                        // final Bitmap bitmap3 = BitmapFactory.decodeStream(in3);
                      //   InputStream in4 = channel.get(ruta1);
                      //   final Bitmap bitmap4 = BitmapFactory.decodeStream(in4);
                       //  InputStream in5 = channel.get(ruta1);
                        //  final Bitmap bitmap5 = BitmapFactory.decodeStream(in5);
                       //   InputStream in6 = channel.get(ruta1);
                       //   final Bitmap bitmap6 = BitmapFactory.decodeStream(in6);
                       //   InputStream in7 = channel.get(ruta1);
                       //   final Bitmap bitmap7 = BitmapFactory.decodeStream(in7);
                       //   InputStream in8 = channel.get(ruta1);
                      //    final Bitmap bitmap8 = BitmapFactory.decodeStream(in8);






                          //tarea1
                    JSONObject data = response;
                    //data = new JSONObject(temp);
                   String json = data.getString("json");
                   JSONObject jsono = new JSONObject(json);
                    JSONObject obj = jsono.getJSONObject("Terapia");
                    JSONArray arra = new JSONArray(obj.getJSONArray("Acciones").toString());


                    JSONObject otro = arra.getJSONObject(0);
                    JSONObject acci = otro.getJSONObject("accion");
                    JSONObject conf = acci.getJSONObject("configuraciones");
                    JSONObject textoC = conf.getJSONObject("textoConfig");

                    JSONArray arr1 = new JSONArray(textoC.getJSONArray("textos").toString());
                    JSONObject otro1 = arr1.getJSONObject(0);


                    JSONObject basicas = conf.getJSONObject("basicas");
                    JSONObject extra = conf.getJSONObject("extraConfig");


                    String text = otro1.getString("texto");
                    Log.d("texto", text);
                    final String tletra = otro1.getString("tamanioLetra");
                    final String tamano = tletra.substring(0, tletra.length() - 2);
                    Log.d("texto", tamano);
                    final String silabas = basicas.getString("silabas");
                    Log.d("texto", silabas);
                    final String fondo = extra.getString("colorFondo");
                    Log.d("texto", fondo);
                    int o = fondo.indexOf("(");
                    String sub = fondo.substring(o + 1, fondo.length() - 1);
                    Log.d("texto", sub);
                    final String[] resultado = sub.split(",");
                    final String g = resultado[1].substring(1);
                    final String b = resultado[2].substring(1);

                    final String texto = text.replace("{silabas}", silabas);




                    //tarea2

                    JSONObject otro2 = arra.getJSONObject(1);
                    JSONObject acci1 = otro2.getJSONObject("accion");
                    JSONObject conf1 = acci1.getJSONObject("configuraciones");
                    JSONObject basicC1 = conf1.getJSONObject("basicasConfig");
                    JSONObject textoC1 = conf1.getJSONObject("textoConfig");
                    JSONObject extra1 = conf1.getJSONObject("extraConfig");
                    JSONArray textos = new JSONArray(textoC1.getJSONArray("textos").toString());
                    JSONObject textos1 = textos.getJSONObject(0);
                    JSONObject textos2 = textos.getJSONObject(1);
                    JSONObject textos3 = textos.getJSONObject(2);
                    JSONObject textos4 = textos.getJSONObject(3);
                    JSONObject textos5 = textos.getJSONObject(4);
                    JSONObject textos6 = textos.getJSONObject(5);

                    final String fondo2 = extra1.getString("colorFondo");
                    final String texto1 = textos1.getString("texto");
                    final String tletra1 = textos1.getString("tamanioLetra");
                    final String texto2 = textos2.getString("texto");
                    final String tletra2 = textos2.getString("tamanioLetra");
                    final String texto3 = textos3.getString("texto");
                    final String tletra3 = textos3.getString("tamanioLetra");
                    final String texto4 = textos4.getString("texto");
                    final String tletra4 = textos4.getString("tamanioLetra");
                    final String texto5= textos5.getString("texto");
                    final String tletra5 = textos5.getString("tamanioLetra");
                    final String texto6= textos6.getString("texto");
                    final String tletra6 = textos6.getString("tamanioLetra");


                    JSONArray arra1 = new JSONArray(basicC1.getJSONArray("preguntas").toString());
                    JSONObject preguntas = arra1.getJSONObject(0);
                    final String pregunta1 = preguntas.getString("pregunta");
                    final JSONArray respuestas1 = preguntas.getJSONArray("respuestas");
                    final String respuesta11 = respuestas1.getString(0);
                    final String respuesta12 = respuestas1.getString(1);
                    final String respuesta13 = respuestas1.getString(2);
                    final String respuestaCorrecta1 = preguntas.getString("respuestaCorrecta");
                    JSONObject preguntas2 = arra1.getJSONObject(1);
                    final String pregunta2 = preguntas2.getString("pregunta");
                    JSONArray respuestas2 = preguntas2.getJSONArray("respuestas");
                    final String respuesta21 = respuestas2.getString(0);
                    final String respuesta22 = respuestas2.getString(1);
                    final String respuesta23 = respuestas2.getString(2);

                    final String respuestaCorrecta2 = preguntas2.getString("respuestaCorrecta");





                    //tarea3

                    JSONObject otro3 = arra.getJSONObject(2);
                    JSONObject acci2 = otro3.getJSONObject("accion");
                    JSONObject conf2 = acci2.getJSONObject("configuraciones");
                    JSONObject textConfig2 = conf2.getJSONObject("textoConfig");
                    JSONArray textos7 = new JSONArray(textConfig2.getJSONArray("textos").toString());
                    JSONObject textos8 = textos7.getJSONObject(0);
                    JSONObject textos9 = textos7.getJSONObject(1);
                    final String texto7 = textos8.getString("texto");
                    final String tletra7 = textos8.getString("tamanioLetra");
                    final String texto8 = textos9.getString("texto");
                    final String tletra8 = textos9.getString("tamanioLetra");


                    JSONObject extraConfig = conf2.getJSONObject("extraConfig");
                    final String fondo3 = extraConfig.getString("colorFondo");
                    JSONObject basicas2 = conf2.getJSONObject("basicas");
                    JSONArray arra2 = new JSONArray(basicas2.getJSONArray("oraciones").toString());
                    JSONObject oraciones1 = arra2.getJSONObject(0);
                    final String oracion1 = oraciones1.getString("oracion");
                    JSONObject oraciones2 = arra2.getJSONObject(1);
                    final String oracion2 = oraciones2.getString("oracion");
                    JSONArray preguntas1 = new JSONArray(basicas2.getJSONArray("preguntas").toString());
                    JSONObject preguntas11 = preguntas1.getJSONObject(0);
                    String pregunta3 = preguntas11.getString("pregunta");
                    JSONArray respuestas3 = preguntas11.getJSONArray("respuestas");
                    final String respuesta31 = respuestas3.getString(0);
                    final String respuesta32 = respuestas3.getString(1);
                    final String respuesta33 = respuestas3.getString(2);



                    String respuestaCorrecta3 = preguntas11.getString("respuestaCorrecta");
                    JSONObject preguntas12 = preguntas1.getJSONObject(1);
                    String pregunta4 = preguntas12.getString("pregunta");
                    JSONArray respuestas4 = preguntas12.getJSONArray("respuestas");



                    String respuestaCorrecta4 = preguntas12.getString("respuestaCorrecta");
                    final String respuesta41 = respuestas4.getString(0);
                    final String respuesta42 = respuestas4.getString(1);
                    final String respuesta43 = respuestas4.getString(2);





                    Log.d("texto", texto);


                          final int[] aciertos = new int[1];
                          final int[] fallos = new int[1];

                          runOnUiThread(new Runnable() {

                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void run() {

                            // Stuff that updates the UI

                            RelativeLayout terapia = findViewById(R.id.terapia);
                            ScrollView terapiascroll = new ScrollView(getApplicationContext());
                            terapiascroll.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT));
                            LinearLayout l1 = new LinearLayout(getApplicationContext());
                            l1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            l1.setOrientation(LinearLayout.VERTICAL);
                            l1.setPadding(10, 10, 10, 10);
                            l1.setBackgroundColor(Color.rgb(Integer.parseInt(resultado[0]), Integer.parseInt(g), Integer.parseInt(b)));
                            l1.invalidate();

                            TextView textView = new TextView(getApplicationContext());
                            TextView textView1 = new TextView(getApplicationContext());
                            TextView textView2 = new TextView(getApplicationContext());
                            textView.setTextSize(Float.parseFloat(tamano));
                            textView.setText(texto);

                            Button limpiar = new Button(getApplicationContext());
                            limpiar.setBackgroundColor(Color.rgb(Integer.parseInt(resultado[0]), Integer.parseInt(g), Integer.parseInt(b)));
                            limpiar.setText("Limpiar");


                            String sil = silabas.substring(2);
                            String[] result = sil.split(",");
                           int Id = getResources().getIdentifier("border", "drawable", getPackageName());
                            textView1.setTextSize(40);
                            textView2.setTextSize(40);
                            textView1.setBackground(getResources().getDrawable(Id));
                            textView2.setBackground(getResources().getDrawable(Id));
                            textView1.setText(result[0]);
                            textView2.setText(result[1]);



                            LinearLayout lh1 = new LinearLayout(getApplicationContext());
                            lh1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            lh1.setOrientation(LinearLayout.HORIZONTAL);

                            LinearLayout lh2 = new LinearLayout(getApplicationContext());
                            lh2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            lh2.setOrientation(LinearLayout.HORIZONTAL);

                            l1.addView(textView);

                            l1.addView(limpiar);
                            lh1.addView(textView1);
                            lh2.addView(textView2);
                            l1.addView(lh1);
                            l1.addView(lh2);


                            final ImageView imageView1 = new ImageView(getApplicationContext());

                            imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                           // imageView1.setImageBitmap(bitmap1);
                            imageView1.setImageResource(R.drawable.pinza);
                            int x =1;
                            imageView1.setId(x);
                            android.view.ViewGroup.LayoutParams layoutParams = imageView1.getLayoutParams();
                            layoutParams.width = 80;
                            layoutParams.height = 80;
                            imageView1.setLayoutParams(layoutParams);
                            l1.addView(imageView1);


                           lh1.setOnDragListener(new View.OnDragListener(){

                                @Override
                                public boolean onDrag(View view, DragEvent event) {
                                    int action = event.getAction();
                                    switch (event.getAction()) {
                                        case DragEvent.ACTION_DRAG_STARTED:
                                            // do nothing
                                            break;

                                        case DragEvent.ACTION_DROP:
                                            // Dropped, reassign View to ViewGroup
                                            View v = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) v.getParent();
                                            owner.removeView(v);
                                            LinearLayout container = (LinearLayout) view;
                                            container.addView(v);
                                           int idf= v.getId();
                                           if(idf == 1 || idf == 2 ){
                                                aciertos[0]++;
                                           }
                                           else{
                                               fallos[0]++;
                                           }
                                            v.setVisibility(View.VISIBLE);
                                            break;

                                        default:
                                            break;
                                    }
                                    return true;
                                }

                            });
                            textView2.setText(result[1]);


                            final ImageView imageView2 = new ImageView(getApplicationContext());
                            imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                           // imageView2.setImageBitmap(bitmap2);
                            imageView2.setImageResource(R.drawable.cinco);
                           imageView2.setLayoutParams(layoutParams);
                           int y = 2;
                           imageView2.setId(y);
                            l1.addView(imageView2);

                            lh2.setOnDragListener(new View.OnDragListener(){

                                @Override
                                public boolean onDrag(View view, DragEvent event) {
                                    int action = event.getAction();
                                    switch (event.getAction()) {
                                        case DragEvent.ACTION_DRAG_STARTED:
                                            // do nothing
                                            break;

                                        case DragEvent.ACTION_DROP:
                                            // Dropped, reassign View to ViewGroup
                                            View v = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) v.getParent();
                                            owner.removeView(v);
                                            LinearLayout container = (LinearLayout) view;
                                            container.addView(v);
                                            v.setVisibility(View.VISIBLE);
                                            int idf= v.getId();
                                            if(idf == 3 || idf == 4 ){
                                                aciertos[0]++;
                                            }
                                            else{
                                                fallos[0]++;
                                            }
                                            break;

                                        default:
                                            break;
                                    }
                                    return true;
                                }

                            });

                            final ImageView imageView3 = new ImageView(getApplicationContext());
                             imageView3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                          //  imageView3.setImageBitmap(bitmap3);
                            imageView3.setImageResource(R.drawable.auto);
                           imageView3.setLayoutParams(layoutParams);
                           int z =3;
                            imageView3.setId(z);
                           l1.addView(imageView3);

                            final ImageView imageView4 = new ImageView(getApplicationContext());
                            imageView4.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                          //  imageView4.setImageBitmap(bitmap4);
                            imageView4.setImageResource(R.drawable.auto);
                            imageView4.setLayoutParams(layoutParams);
                           int a =4;
                            imageView4.setId(a);
                            l1.addView(imageView4);


                            final ImageView imageView5 = new ImageView(getApplicationContext());
                            imageView5.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                           // imageView5.setImageBitmap(bitmap5);
                            imageView5.setImageResource(R.drawable.auto);
                            imageView5.setLayoutParams(layoutParams);



                           final ImageView imageView6 = new ImageView(getApplicationContext());
                           imageView6.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                           //imageView6.setImageBitmap(bitmap6);
                            imageView6.setImageResource(R.drawable.auto);
                            imageView6.setLayoutParams(layoutParams);



                         //   final ImageView imageView7 = new ImageView(getApplicationContext());
                           // imageView7.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                           // imageView7.setImageBitmap(bitmap7);
                         //   imageView7.setLayoutParams(layoutParams);



                          //  final ImageView imageView8 = new ImageView(getApplicationContext());
                           // imageView8.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                          //  imageView8.setImageBitmap(bitmap8);
                          //  imageView8.setLayoutParams(layoutParams);








                            imageView1.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent motionEvent) {
                                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                            ClipData data = ClipData.newPlainText("", "");
                                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                                    v);
                                            v.startDrag(data, shadowBuilder, v, 0);
                                            v.setVisibility(View.INVISIBLE);
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
                            });


                            imageView2.setOnTouchListener(new View.OnTouchListener() {

                              @Override
                                                            public boolean onTouch(View v, MotionEvent motionEvent) {
                                                                   if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                                                      ClipData data = ClipData.newPlainText("", "");
                                                                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                                                                v);
                                                                        v.startDrag(data, shadowBuilder, v, 0);
                                                                        v.setVisibility(View.INVISIBLE);
                                                                        return true;
                                                                    } else {
                                                                        return false;
                                                                    }
                                                                }
                           });

                            imageView3.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent motionEvent) {
                                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                        ClipData data = ClipData.newPlainText("", "");
                                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                                v);
                                        v.startDrag(data, shadowBuilder, v, 0);
                                        v.setVisibility(View.INVISIBLE);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            });


                            imageView4.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent motionEvent) {
                                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                        ClipData data = ClipData.newPlainText("", "");
                                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                                v);
                                        v.startDrag(data, shadowBuilder, v, 0);
                                        v.setVisibility(View.INVISIBLE);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            });


                            LinearLayout l2 = new LinearLayout(getApplicationContext());
                            l2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            l2.setOrientation(LinearLayout.VERTICAL);
                            l2.setPadding(2, 2, 2, 2);

                            int o = fondo2.indexOf("(");
                            String sub = fondo2.substring(o + 1, fondo2.length() - 1);
                            Log.d("texto", sub);
                            final String[] resultado = sub.split(",");
                            final String g = resultado[1].substring(1);
                            final String b = resultado[2].substring(1);
                            final String tamano1 = tletra1.substring(0, tletra1.length() - 2);
                            final String tamano2 = tletra2.substring(0, tletra2.length() - 2);
                            final String tamano3 = tletra3.substring(0, tletra3.length() - 2);
                            final String tamano4 = tletra4.substring(0, tletra4.length() - 2);
                            final String tamano5 = tletra5.substring(0, tletra5.length() - 2);
                            final String tamano6 = tletra6.substring(0, tletra6.length() - 2);

                            l2.setBackgroundColor(Color.rgb(Integer.parseInt(resultado[0]), Integer.parseInt(g), Integer.parseInt(b)));
                            l2.invalidate();


                            TextView textView3 = new TextView(getApplicationContext());
                            TextView textView4 = new TextView(getApplicationContext());
                            TextView textView5 = new TextView(getApplicationContext());
                            TextView textView6 = new TextView(getApplicationContext());
                            TextView textView7 = new TextView(getApplicationContext());
                            TextView textView8 = new TextView(getApplicationContext());
                            TextView textView9 = new TextView(getApplicationContext());
                            TextView textView10 = new TextView(getApplicationContext());

                            textView3.setTextSize(Float.parseFloat(tamano1));
                            textView3.setText(texto1);
                            textView4.setTextSize(Float.parseFloat(tamano2));
                            textView4.setText(texto2);
                            textView5.setTextSize(Float.parseFloat(tamano5));
                            textView5.setText(texto5);
                            textView6.setTextSize(Float.parseFloat(tamano6));
                            textView6.setText(texto6);
                            textView7.setTextSize(Float.parseFloat(tamano3));
                            textView7.setText(texto3);

                            textView8.setText(pregunta1);
                            final CheckBox checkBox1 = new CheckBox(getApplicationContext());
                            checkBox1.setText(respuesta11);

                            final CheckBox checkBox2 = new CheckBox(getApplicationContext());
                            checkBox2.setText(respuesta12);
                            final CheckBox checkBox3 = new CheckBox(getApplicationContext());
                            checkBox3.setText(respuesta13);


                            textView9.setText(pregunta2);
                            final CheckBox checkBox4 = new CheckBox(getApplicationContext());
                            checkBox4.setText(respuesta21);
                            final CheckBox checkBox5 = new CheckBox(getApplicationContext());
                            checkBox5.setText(respuesta22);
                            final CheckBox checkBox6 = new CheckBox(getApplicationContext());
                            checkBox6.setText(respuesta23);

                            textView10.setTextSize(Float.parseFloat(tamano4));
                            textView10.setText(texto4);

                            l2.addView(textView3);
                            l2.addView(textView4);
                            l2.addView(textView5);
                            l2.addView(textView6);
                            l2.addView(textView7);
                            l2.addView(textView8);
                            l2.addView(checkBox1);
                            l2.addView(checkBox2);
                            l2.addView(checkBox3);


                            l2.addView(textView9);

                            l2.addView(checkBox4);
                            l2.addView(checkBox5);
                            l2.addView(checkBox6);
                            l2.addView(textView10);


                            int o3 = fondo3.indexOf("(");
                            String sub3 = fondo3.substring(o3 + 1, fondo3.length() - 1);
                            Log.d("texto", sub);
                            final String[] resultado3 = sub3.split(",");
                            final String g3 = resultado3[1].substring(1);
                            final String b3 = resultado3[2].substring(1);
                            final String tamano7 = tletra7.substring(0, tletra7.length() - 2);

                            LinearLayout l3 = new LinearLayout(getApplicationContext());
                            l3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            l3.setOrientation(LinearLayout.VERTICAL);
                            l3.setPadding(2, 2, 2, 2);
                            l3.setBackgroundColor(Color.rgb(Integer.parseInt(resultado3[0]), Integer.parseInt(g3), Integer.parseInt(b3)));
                            l3.invalidate();


                            TextView textView11 = new TextView(getApplicationContext());

                            textView11.setTextSize(Float.parseFloat(tamano7));
                            textView11.setText(texto7);
                            l3.addView(textView11);
                            Button calificar = new Button(getApplicationContext());
                            calificar.setBackgroundColor(Color.rgb(Integer.parseInt(resultado3[0]), Integer.parseInt(g3), Integer.parseInt(b3)));
                            calificar.setText("Calificar");
                            l3.addView(calificar);

                            LinearLayout lh3 = new LinearLayout(getApplicationContext());
                            lh3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            lh3.setOrientation(LinearLayout.HORIZONTAL);

                            LinearLayout lh4 = new LinearLayout(getApplicationContext());
                            lh4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            lh4.setOrientation(LinearLayout.HORIZONTAL);

                           lh3.addView(imageView5);


                            lh4.addView(imageView6);


                            String[] oraciond1 = oracion1.split("\\s");
                            Arrays.sort(oraciond1);

                            //final TextView[] myTextViews = new TextView[oraciond1.length];
                            for (int i = 0; i < oraciond1.length; i++) {
                                TextView tv = new TextView(getApplicationContext()); // Prepare textview object programmatically
                                tv.setText(oraciond1[i]);
                                tv.setPadding(2,2,2,2);
                                tv.setOnTouchListener(new View.OnTouchListener() {

                                    @Override
                                    public boolean onTouch(View v, MotionEvent motionEvent) {
                                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                            ClipData data = ClipData.newPlainText("", "");
                                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                                    v);
                                            v.startDrag(data, shadowBuilder, v, 0);
                                            v.setVisibility(View.INVISIBLE);
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
                                });

                              tv.setOnDragListener(new View.OnDragListener() {
                                  @Override
                                  public boolean onDrag(View view, DragEvent dragEvent) {
                                      int action = dragEvent.getAction();
                                      switch (dragEvent.getAction()) {
                                          case DragEvent.ACTION_DRAG_STARTED:
                                              // do nothing
                                              break;

                                          case DragEvent.ACTION_DROP:
                                              // Dropped, reassign View to ViewGroup
                                              View v = (View) dragEvent.getLocalState();
                                              TextView textdrag = (TextView) v;
                                              String textddd= textdrag.getText().toString();
                                              TextView textdropped= (TextView) view;

                                              textdrag.setText(textdropped.getText());

                                              textdropped.setText(textddd);
                                              v.setVisibility(View.VISIBLE);
                                              break;

                                          default:
                                              break;
                                      }
                                      return true;
                                  }



                              });  // tv.setId(i + 5);
                               lh3.addView(tv); // Add to your ViewGroup using this method


                            }
                            l3.addView(lh3);

                            String[] oraciond2 = oracion2.split("\\s");

                              Arrays.sort(oraciond2);

                            for (int i = 0; i < oraciond2.length; i++) {
                                TextView tv = new TextView(getApplicationContext()); // Prepare textview object programmatically
                                tv.setText(oraciond2[i]);
                                tv.setPadding(2,2,2,2);
                                tv.setOnTouchListener(new View.OnTouchListener() {

                                    @Override
                                    public boolean onTouch(View v, MotionEvent motionEvent) {
                                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                            ClipData data = ClipData.newPlainText("", "");
                                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                                    v);
                                            v.startDrag(data, shadowBuilder, v, 0);
                                            v.setVisibility(View.INVISIBLE);
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
                                });
                                tv.setOnDragListener(new View.OnDragListener() {
                                    @Override
                                    public boolean onDrag(View view, DragEvent dragEvent) {
                                        int action = dragEvent.getAction();
                                        switch (dragEvent.getAction()) {
                                            case DragEvent.ACTION_DRAG_STARTED:
                                                // do nothing
                                                break;

                                            case DragEvent.ACTION_DROP:
                                                // Dropped, reassign View to ViewGroup

                                                View v = (View) dragEvent.getLocalState();
                                                TextView textdrag = (TextView) v;
                                                String textddd= textdrag.getText().toString();
                                                TextView textdropped= (TextView) view;

                                                textdrag.setText(textdropped.getText());

                                                textdropped.setText(textddd);
                                                v.setVisibility(View.VISIBLE);
                                                break;
                                        }
                                        return true;
                                    }



                                });  // tv.setId(i + 5);
                                lh4.addView(tv); // Add to your ViewGroup using this method


                            }

                            l3.addView(lh4);


                            final String tamano8 = tletra8.substring(0, tletra8.length() - 2);
                            TextView textView14 = new TextView(getApplicationContext());
                            textView14.setTextSize(Float.parseFloat(tamano8));
                            textView14.setText(texto8);

                            l3.addView(textView14);



                         //   l3.addView(imageView7);

                            CheckBox checkBox7 = new CheckBox(getApplicationContext());
                            checkBox7.setText(respuesta31);
                            CheckBox checkBox8 = new CheckBox(getApplicationContext());
                            checkBox8.setText(respuesta32);
                            CheckBox checkBox9 = new CheckBox(getApplicationContext());
                            checkBox9.setText(respuesta33);

                            l3.addView(checkBox7);
                            l3.addView(checkBox8);
                            l3.addView(checkBox9);


                        //    l3.addView(imageView8);

                            CheckBox checkBox10 = new CheckBox(getApplicationContext());
                            checkBox10.setText(respuesta41);
                            CheckBox checkBox11 = new CheckBox(getApplicationContext());
                            checkBox11.setText(respuesta42);
                            CheckBox checkBox12 = new CheckBox(getApplicationContext());
                            checkBox12.setText(respuesta43);
                            l3.addView(checkBox10);
                            l3.addView(checkBox11);
                            l3.addView(checkBox12);


                            LinearLayout l4 = new LinearLayout(getApplicationContext());
                            l4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            l4.setOrientation(LinearLayout.VERTICAL);

                            l4.addView(l1);
                            l4.addView(l2);
                            l4.addView(l3);



                            final String[] respuestapregunta1 = new String[1];
                            //RESPUESTAS
                            checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                                     @Override
                                                                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                                         if (b) {
                                                                             checkBox2.setChecked(false);
                                                                             checkBox3.setChecked(false);
                                                                             respuestapregunta1[0] =  checkBox1.getText().toString();
                                                                             if (respuestapregunta1[0].equals(respuestaCorrecta1)){
                                                                                 aciertos[0]++;
                                                                             }
                                                                             else{
                                                                                 fallos[0]++;
                                                                             }

                                                                         }
                                                                     }
                                                                 }

                            );

                            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                                     @Override
                                                                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                                         if (b) {
                                                                             checkBox3.setChecked(false);
                                                                             checkBox1.setChecked(false);
                                                                             respuestapregunta1[0] =  checkBox2.getText().toString();
                                                                             if (respuestapregunta1[0].equals(respuestaCorrecta1)){
                                                                                 aciertos[0]++;
                                                                             }
                                                                             else{
                                                                                 fallos[0]++;
                                                                             }
                                                                         }
                                                                     }
                                                                 }

                            );
                            checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                                     @Override
                                                                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                                         if (b) {
                                                                             checkBox1.setChecked(false);
                                                                             checkBox2.setChecked(false);
                                                                             respuestapregunta1[0] =  checkBox3.getText().toString();
                                                                             if (respuestapregunta1[0].equals(respuestaCorrecta1)){
                                                                                 aciertos[0]++;
                                                                             }
                                                                             else{
                                                                                 fallos[0]++;
                                                                             }
                                                                         }
                                                                     }
                                                                 }

                            );

                            checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                                     @Override
                                                                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                                         if (b) {
                                                                             checkBox5.setChecked(false);
                                                                             checkBox6.setChecked(false);
                                                                             respuestapregunta1[0] =  checkBox4.getText().toString();
                                                                             if (respuestapregunta1[0].equals(respuestaCorrecta2)){
                                                                                 aciertos[0]++;
                                                                             }
                                                                             else{
                                                                                 fallos[0]++;
                                                                             }
                                                                         }
                                                                     }
                                                                 }

                            );

                            checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                                     @Override
                                                                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                                         if (b) {
                                                                             checkBox6.setChecked(false);
                                                                             checkBox4.setChecked(false);
                                                                             respuestapregunta1[0] =  checkBox5.getText().toString();
                                                                             if (respuestapregunta1[0].equals(respuestaCorrecta2)){
                                                                                 aciertos[0]++;
                                                                             }
                                                                             else{
                                                                                 fallos[0]++;
                                                                             }
                                                                         }
                                                                     }
                                                                 }

                            );

                            checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                                     @Override
                                                                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                                         if (b) {
                                                                             checkBox5.setChecked(false);
                                                                             checkBox4.setChecked(false);
                                                                             respuestapregunta1[0] =  checkBox6.getText().toString();
                                                                             if (respuestapregunta1[0].equals(respuestaCorrecta2)){
                                                                                 aciertos[0]++;
                                                                             }
                                                                             else{
                                                                                 fallos[0]++;
                                                                             }
                                                                         }
                                                                     }
                                                                 }

                            );

                            Log.d("aciertos", aciertos.toString());
                            Log.d("fallos", fallos.toString());

                            Button terminar = new Button(getApplicationContext());
                            terminar.setText("TERMINADO");
                            terminar.setBackgroundColor(Color.GREEN);
                            terminar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    actualizarRequest(id,idp,hoy);
                                }
                            });

                            l4.addView(terminar);



                            terapiascroll.addView(l4);


                            terapia.addView(terapiascroll);






                        }
                    });

                       //   if (channel.isClosed()) {
                          //    channel.disconnect();

                           //   session.disconnect();
                        //  }
                    //     try {
                        //      Thread.sleep(1000);
                       //   } catch (Exception ee) {
                      //   }
                      } catch (JSchException e) {
                          System.out.println(e.getMessage());
                          e.printStackTrace();
                     // } catch (SftpException e) {
                     //     e.printStackTrace();
                      }


                    Log.i("VOLLEY", response.toString());


                }}, new Response.ErrorListener() {
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


        public void actualizarRequest(final String id, String idp, Date hoy) {
            Date today = new Date();
              int res = Math.abs(today.compareTo(hoy)) / 1000;
            int hours = (int) (Math.floor(res / 3600) % 24);
            int minutes = (int) (Math.floor(res / 60) % 60);
            int seconds = (int) Math.floor(res % 60);
          String tiempo =hours+":"+minutes+":"+seconds;
Log.d("TEMPOOOO",tiempo);

            try {
            RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id", id);
            jsonBody.put("paciente", idp);
                jsonBody.put("tiempo", tiempo);

            String URL1 = "http://192.168.100.139:8000/api/actualizar";

            JsonObjectRequest stringRequest1 = new JsonObjectRequest(Request.Method.POST, URL1, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.i("VOLLEY", response.toString());
                    finish();
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
            stringRequest1.setRetryPolicy(new DefaultRetryPolicy(50000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue1.add(stringRequest1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }






}
