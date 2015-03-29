package com.joshrsp.rubros;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity4 extends ActionBarActivity {
    TextView texto;
    String rubro="",ciclo="";
    EditText dinero;
    private  bdManager bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        texto = (TextView) findViewById(R.id.texto4);
        if ( bundle != null ) {
            rubro=bundle.getString("rubro");
            texto.setText(bundle.getString("rubro"));
            ciclo=bundle.getString("ciclo");
        }
        dinero = (EditText) findViewById(R.id.editText4);
        bd = new bdManager(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity4, menu);
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

    public void buttonOnClickCrearRubro(View view)
    {
        String tipo="";

            if(!dinero.getText().equals("") ){

                bd.insertarValor(rubro,Integer.valueOf(dinero.getText().toString()),ciclo);
                Intent intent = new Intent(MainActivity4.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("ciclo",ciclo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
    }
}
