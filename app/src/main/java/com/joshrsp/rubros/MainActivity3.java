package com.joshrsp.rubros;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity3 extends ActionBarActivity {
    TextView texto;
    String ciclo;
    private  bdManager bd;
    EditText nombre,valor;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        texto = (TextView) findViewById(R.id.texto3);
        if ( bundle != null ) {
            ciclo=bundle.getString("ciclo");
            texto.setText(bundle.getString("ciclo"));
        }
        bd = new bdManager(this);
        nombre = (EditText) findViewById(R.id.editText2);
        valor = (EditText) findViewById(R.id.editText3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGrupo);
        radioGroup.check(R.id.radioButton);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
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
        if(!nombre.getText().equals("") && !valor.getText().equals("")){
           if(radioGroup.getCheckedRadioButtonId()==R.id.radioButton)
           {
               tipo="entrada";
           }
            else{
               if(radioGroup.getCheckedRadioButtonId()==R.id.radioButton2)
               {
                   tipo="salida";
               }
            }
            bd.insertarRublo(nombre.getText().toString(),Integer.valueOf(valor.getText().toString()),tipo,ciclo);
            Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
            Bundle bundle = new Bundle();
            bundle.putString("ciclo",ciclo);
            intent.putExtras(bundle);
            startActivity(intent);
          }


    }
}
