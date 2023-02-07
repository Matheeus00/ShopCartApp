package br.com.fritz.shopcart.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;
import br.com.fritz.shopcart.db.Transaction;
import br.com.fritz.shopcart.model.Item;
import br.com.fritz.shopcart.util.CMN;

public class ItemController
{

    /**
     * getListItems
     *
     * @return listItems List<Item>
     */
    public static List<Item> getListItems( Context context )
    {
        Transaction db = new Transaction( context, CMN.DATABASE );

        List<Item> listItems = new ArrayList<>();

        Cursor query = db.getItems( CMN.SELECT_SQL );

        if ( query.moveToFirst() )
        {
            do
            {
                Item item = new Item();
                item.setId( query.getInt( 0 ) );
                item.setDesc( query.getString( 1 ) );
                item.setLined( query.getInt( 2 ) );

                listItems.add( item );
            }
            while ( query.moveToNext() );
        }
        return listItems;
    }


    /**
     * insertItem
     *
     */
    public static void insertItem( Context context, EditText textField )
    {
        Transaction db = new Transaction( context, CMN.DATABASE );
        String item = textField.getText().toString().trim();

        ContentValues value = new ContentValues();
        value.put( CMN.DESC, item );
        value.put( CMN.LINED, 0 );

        if ( !item.isEmpty() )
        {
            try
            {
                if ( db.insertItem( value ) != -1 )
                {
                    CMN.showAlert( context, "Item salvo com sucesso!" );
                    textField.setText("");
                    textField.requestFocus();
                }

                else
                {
                    CMN.showAlert( context, "Não foi possível salvar o item" );
                }
            }

            catch ( Exception e )
            {
                CMN.showAlert( context, e.getMessage() );
            }
        }
    }
}
