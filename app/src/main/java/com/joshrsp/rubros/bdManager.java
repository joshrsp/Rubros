package com.joshrsp.rubros;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * Created by Usuario on 27/03/2015.
 */
public class bdManager {
    //////////////////////////tabla principal ciclos//////////////////////////
    public static final String TABLE_NAME = "ciclos";
    public static final String CN_ID="_id";
    public static final String CN_NAME ="nombre";
    ///////////////////////////tabla de rublos///////////////////////////////
    public static final String TABLE_NAME2 = "rubros";
    public static final String CN_ID2="_id";
    public static final String CN_NAME2 ="rubro";
    public static final String CN_MONEY ="esperado";
    public static final String CN_TYPE ="tipo";
    public static final String CN_CYCLE="ciclo";
    ////////////////////////////tabla de valores//////////////////////////////
    public static final String TABLE_NAME3 = "valores";
    public static final String CN_ID3="_id";
    public static final String CN_MONEY2 ="dinero";
    public static final String CN_RUBRO="idrubro";
    public static final String CN_CYCLE2="ciclo";
    ////////////////////////////Creacion tablas//////////////////////////////
    ///////////////////////////Creacion tabla ciclo//////////////////////////
    public static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("
            +CN_ID+" integer primary key autoincrement,"
            +CN_NAME+ " text not null);";
    ///////////////////////////Creacion tabla rubro//////////////////////////
    public static final String CREATE_TABLE2 = "create table "+TABLE_NAME2+" ("
            +CN_ID2+" integer primary key autoincrement,"
            +CN_NAME2+ " text not null,"
            +CN_TYPE+ " text not null,"
            +CN_MONEY+ " integer not null,"
            +CN_CYCLE+ " text not null);";
    ///////////////////////////Creacion tabla valores//////////////////////////
    public static final String CREATE_TABLE3 = "create table "+TABLE_NAME3+" ("
            +CN_ID3+" integer primary key autoincrement,"
            +CN_MONEY2+ " integer not null,"
            +CN_RUBRO+ " text not null,"
            +CN_CYCLE2+ " text not null);";

    private DBrubros dbrubro;
    private SQLiteDatabase db;
/////////////////////////////////////////constructor////////////////////////////////////////////////
    public bdManager(Context context) {

        dbrubro = new DBrubros(context);
        db = dbrubro.getWritableDatabase();
    }
///////////////////////////////////////insertar y modificar ciclo//////////////////////////////////////
    public ContentValues generalValorCiclo(String nombre){
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,nombre);
        return valores;
    }
    public void insertarCiclo(String nombre)
    {
       db.insert(TABLE_NAME,null,generalValorCiclo(nombre));
    }
    public void eliminarCiclo(String nombre){

        db.delete(TABLE_NAME,CN_NAME+"=?",new String[]{nombre});
    }
    ///////////////////////////////////////insertar rublo//////////////////////////////////////
    public ContentValues generalValorRublo(String nombre, int money,String tipo,String ciclo){
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME2,nombre);
        valores.put(CN_TYPE,tipo);
        valores.put(CN_MONEY,money);
        valores.put(CN_CYCLE,ciclo);
        return valores;
    }
    public void insertarRublo(String nombre, int money,String tipo,String ciclo)
    {
        db.insert(TABLE_NAME2,null,generalValorRublo(nombre, money, tipo, ciclo));
    }
    public Cursor buscarRubroTodo(String ciclo,String nombre)
    {
        String[] columnas = new String[]{CN_ID2,CN_NAME2,CN_TYPE,CN_MONEY,CN_CYCLE};
        return db.query(TABLE_NAME2,columnas,CN_NAME2 + "=? and "+CN_CYCLE+"=?",new String[]{nombre,ciclo},null,null,null);
    }
    ////////////////////////////////insertar, modificar  y buscar valor rublo//////////////////////
    public ContentValues generalModRublo(String id,int valor,String ciclo){
        ContentValues valores = new ContentValues();
        valores.put(CN_MONEY2,valor);
        valores.put(CN_RUBRO,id);
        valores.put(CN_CYCLE2,ciclo);
        return valores;
    }

    public Cursor buscarValor(String nombre,String ciclo)
    {
        String[] columnas = new String[]{CN_ID3,CN_RUBRO};
        return db.query(TABLE_NAME3,columnas,CN_RUBRO + "=? and "+CN_CYCLE2+"=?",new String[]{nombre,ciclo},null,null,null);
    }
    public Cursor buscarValorDinero(String nombre,String ciclo)
    {
        String[] columnas = new String[]{CN_ID3,CN_MONEY2};
        return db.query(TABLE_NAME3,columnas,CN_RUBRO + "=? and "+CN_CYCLE2+"=?",new String[]{nombre,ciclo},null,null,null);
    }
    public Cursor buscarValorTodo(String ciclo)
    {
        String[] columnas = new String[]{CN_ID3,CN_MONEY2,CN_RUBRO,CN_CYCLE2};
        return db.query(TABLE_NAME3,columnas,CN_CYCLE2+"=?",new String[]{ciclo},null,null,null);
    }
    public void insertarValor(String rubro,int valor,String ciclo)
    {
        Cursor cursor = buscarValor(rubro,ciclo);
        if(!cursor.moveToFirst()) {
            db.insert(TABLE_NAME3, null, generalModRublo(rubro, valor,ciclo));
        }
        else
        {
            modificarValor(rubro,valor,ciclo);
        }
    }

    public void modificarValor(String rubro,int valor,String ciclo){

         int mon=0;
        Cursor cursor = buscarValorDinero(rubro,ciclo);

        if (cursor.moveToFirst()) {

           mon=Integer.valueOf(cursor.getString(1));

        }
        db.update(TABLE_NAME3,generalModRublo(rubro,(valor+mon),ciclo),CN_RUBRO+"=?",new String[]{""+rubro});
    }
    /////////////////////////////////////////cargar bd///////////////////////////////////////////
    ////////////////////////////////////////cargar ciclo/////////////////////////////////////////
    public Cursor cargarCiclo()
    {
        String[] columnas = new String[]{CN_ID,CN_NAME};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }
    ///////////////////////////////////////cargar rubro/////////////////////////////////////////
    public Cursor cargaRubro()
    {
        String[] columnas = new String[]{CN_ID2,CN_NAME2,CN_TYPE,CN_MONEY,CN_CYCLE};
        return db.query(TABLE_NAME2,columnas,null,null,null,null,null);
    }
    public Cursor cargaRubro2(String ciclo)
    {
        String[] columnas = new String[]{CN_ID2,CN_NAME2,CN_TYPE,CN_MONEY,CN_CYCLE};
        return db.query(TABLE_NAME2,columnas,CN_CYCLE+"=?",new String[]{ciclo},null,null,null);
    }
    ///////////////////////////////////////cargar valor/////////////////////////////////////////
    public Cursor cargaValor()
    {
        String[] columnas = new String[]{CN_ID3,CN_MONEY2,CN_RUBRO};
        return db.query(TABLE_NAME3,columnas,null,null,null,null,null);
    }
    //////////////////////////////////////
}



