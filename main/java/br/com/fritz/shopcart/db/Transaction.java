package br.com.fritz.shopcart.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.fritz.shopcart.util.CMN;

public class Transaction
{
    private final SQLiteDatabase banco;
    private Cursor resultQuery;
    private long resultado;

    public Transaction( Context context, String db )
    {
        Connection gerenciador = new Connection( context, CMN.DATABASE );
        banco = gerenciador.getWritableDatabase();
    }

    public long insertItem( ContentValues values )
    {
        resultado = banco.insert( CMN.TABLE_ITEM, null, values );
        return resultado;
    }

    public long deleteItemById( int id )
    {
        resultado = banco.delete( CMN.TABLE_ITEM, CMN.CONDITIONAL_ID_SQL + " = ? ", new String[] { String.valueOf( id ) } );
        return resultado;
    }

    public void alterLined(ContentValues values, int id )
    {
        resultado = banco.update( CMN.TABLE_ITEM, values, CMN.CONDITIONAL_ID_SQL + " = ?", new String[] { String.valueOf( id ) } );
    }

    public Cursor getItems( String sql )
    {
        resultQuery = banco.rawQuery( sql, null );
        return resultQuery;
    }

}
