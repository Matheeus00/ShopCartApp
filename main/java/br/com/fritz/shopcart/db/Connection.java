package br.com.fritz.shopcart.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.fritz.shopcart.util.CMN;

public class Connection extends SQLiteOpenHelper
{
    public Connection(Context context, String db )
    {
        super( context, db, null, CMN.VERSION );
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( CMN.EXECUTE_SQL_ITEM );
        db.execSQL( CMN.EXECUTE_SQL_OK );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int passed, int now )
    {
        db.execSQL( CMN.UPGRADE_SQL_ITEM );
        db.execSQL( CMN.UPGRADE_SQL_OK );
        onCreate( db );
    }
}
