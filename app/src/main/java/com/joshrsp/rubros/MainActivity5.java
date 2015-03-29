package com.joshrsp.rubros;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity5 extends ActionBarActivity {
    TextView texto,todo;
    String ciclo="",va="",rubro="",tipo="";
    float dinero=0,esperado=0,entrada=0,salida=0,entrada2=0;
    private  bdManager bd;
    Cursor cursor,cursor2;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        texto = (TextView) findViewById(R.id.texto6);
        todo = (TextView) findViewById(R.id.textView6);
        if ( bundle != null ) {

            texto.setText(bundle.getString("ciclo"));
            ciclo=bundle.getString("ciclo");
        }
        bd = new bdManager(this);
        cursor = bd.buscarValorTodo(ciclo);
        if (cursor.moveToFirst()) {

            do {
                   //dinero--rubro--ciclo
                    va=va+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+"\r\n";
                    dinero=Float.parseFloat(cursor.getString(1));
                    rubro =cursor.getString(2);
                    cursor2 = bd.buscarRubroTodo(ciclo,rubro);
                if (cursor2.moveToFirst()) {

                    do {
                        esperado=Float.parseFloat(cursor2.getString(3));
                        tipo=cursor2.getString(2);
                    } while (cursor2.moveToNext());
                    if(tipo.equals("entrada"))
                    {
                        entrada=entrada+dinero;
                        entrada2=entrada2+esperado;
                    }
                    else
                    {
                        if(tipo.equals("salida"))
                        {
                            salida=salida+dinero;
                        }
                    }
                    if(dinero>esperado)
                    {float porcentaje=(((dinero-esperado)/esperado)*100);
                        va=va+"Rubro : "+rubro+" se paso "+(dinero-esperado)+" "+porcentaje+"%\r\n";
                    }
                    if(dinero==0)
                    {
                        va=va+"Rubro : "+rubro+" no alcanso su meta en "+esperado+"\r\n";
                    }
                }
            } while(cursor.moveToNext());
        }
        va=va+"Balance general : "+(entrada-salida)+"\r\n";
        va=va+"Balance salida : "+(entrada-salida)+"\r\n";
        va=va+"Balance extra : "+(entrada2-entrada)+"\r\n";
        todo.setText(va);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void buttonOnClickvolver(View view)
    {



        if (cursor.moveToFirst()) {

            Intent intent = new Intent(MainActivity5.this,MainActivity2.class);
            Bundle bundle = new Bundle();
            bundle.putString("ciclo",ciclo);
            intent.putExtras(bundle);
            startActivity(intent);

        }

    }

}
