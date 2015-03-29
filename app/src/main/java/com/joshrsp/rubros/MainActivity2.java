package com.joshrsp.rubros;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity2 extends ActionBarActivity {

    TextView texto;
    private String ciclo="";
    private  bdManager bd;
    Cursor cursor;
    private Spinner spinner;
    SimpleCursorAdapter adapter;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bd = new bdManager(this);
        texto = (TextView) findViewById(R.id.text2);
        if ( bundle != null ) {
             ciclo=bundle.getString("ciclo");
            texto.setText(bundle.getString("ciclo"));
        }
        cursor = bd.cargaRubro2(ciclo);
        if(cursor.getCount()>0) {

            String[] from = new String[]{bd.CN_NAME2};
            int[] to = new int[]{android.R.id.text1};

            adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, from, to, 0);
            //list.setAdapter(adapter);
            ///////////////////////////
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner = (Spinner) findViewById(R.id.spinner2);
            spinner.setAdapter(adapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
    public void buttonOnClickCrearaRubro(View view)
    {


                    Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ciclo",ciclo);
                    intent.putExtras(bundle);
                    startActivity(intent);

    }
    public void buttonOnClickSeleccionarRubro(View view)
    {


        int i=0;
        if (cursor.moveToFirst()) {
            do {
                if(i==spinner.getSelectedItemPosition()){
                    // va = cursor.getString(1);
                    Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("rubro",cursor.getString(1) );
                    bundle.putString("ciclo",ciclo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                i=i+1;
            } while(cursor.moveToNext());
        }

    }

    public void buttonOnClickReporte(View view)
    {


        int i=0;
        if (cursor.moveToFirst()) {

                    Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ciclo",ciclo);
                    intent.putExtras(bundle);
                    startActivity(intent);

        }

    }
}
