package com.joshrsp.rubros;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {
    private  bdManager bd;
    Cursor cursor;
    private ListView list;
    private Spinner spinner;
    SimpleCursorAdapter adapter;
    EditText nombre;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd = new bdManager(this);
        //bd.insertarCiclo("mes 1");
        nombre = (EditText) findViewById(R.id.editText);
        cursor = bd.cargarCiclo();
        if(cursor.getCount()>0) {

            String[] from = new String[]{bd.CN_NAME};
            int[] to = new int[]{android.R.id.text1};

            adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, from, to, 0);
            //list.setAdapter(adapter);
            ///////////////////////////
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setAdapter(adapter);
        }
      /*  String[] queryCols=new String[]{"_id", "sampletext"};
        String[] adapterCols=new String[]{"sampletext"};
        int[] adapterRowViews=new int[]{android.R.id.text1};
        mycursor=sdb.query(true,"mytable", queryCols,null,null,null,null,null,null);
        sca=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, mycursor, adapterCols, adapterRowViews,0);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(sca);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void buttonOnClickGuardar(View view)
    {
        if(!nombre.getText().toString().equals("")) {
            bd.insertarCiclo(nombre.getText().toString());
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
    public void buttonOnClickSeleccionar(View view)
    {


        int i=0;
        if (cursor.moveToFirst()) {
            do {
               if(i==spinner.getSelectedItemPosition()){
               // va = cursor.getString(1);
                  Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                   Bundle bundle = new Bundle();
                   bundle.putString("ciclo",cursor.getString(1) );
                   intent.putExtras(bundle);
                   startActivity(intent);
               }
                i=i+1;
            } while(cursor.moveToNext());
        }

    }
}
