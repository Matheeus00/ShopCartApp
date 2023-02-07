package br.com.fritz.shopcart.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CMN
{
    public static final String DATABASE = "itemdb";

    // Item Table
    public static final String TABLE_ITEM = "item";
    public static final String ID = "id";
    public static final String DESC = "desc";
    public static final String LINED = "lined";

    // OK Table
    public static final String TABLE_OK = "ok";
    public static final String YES = "yes";

    // Create Tables
    public static final String EXECUTE_SQL_ITEM  = "CREATE TABLE item ( id INTEGER PRIMARY KEY, desc TEXT NOT NULL, lined INTEGER NOT NULL );";
    public static final String EXECUTE_SQL_OK = "CREATE TABLE ok ( yes INTEGER );";

    // Drop Tables
    public static final String UPGRADE_SQL_ITEM = "DROP TABLE IF EXISTS item";
    public static final String UPGRADE_SQL_OK = "DROP TABLE IF EXISTS ok";

    // Selects
    public static final String SELECT_SQL = "SELECT * FROM " + TABLE_ITEM;
    public static final String CONDITIONAL_DESC_SQL = DESC;
    public static final String CONDITIONAL_ID_SQL = ID;
    public static final String SELECT_SQL_WHERE = SELECT_SQL + " WHERE " + DESC + " = ";

    // Toast Length Message
    public static final int VERSION = 1;
    public static final int QUICK = 1;

    @SuppressLint("WrongConstant")
    public static void showAlert(Context context, String msg)
    {
        Toast.makeText( context, msg, CMN.QUICK ).show();
    }

    @RequiresApi( api = Build.VERSION_CODES.O )
    public static LocalDateTime timeNow()
    {
        return LocalDateTime.ofInstant( Instant.now(), ZoneId.of( "America/Sao_Paulo" ) );
    }
}
