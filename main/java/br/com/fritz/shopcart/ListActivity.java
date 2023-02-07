package br.com.fritz.shopcart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import br.com.fritz.shopcart.db.Transaction;
import br.com.fritz.shopcart.model.Item;
import br.com.fritz.shopcart.util.CMN;
import br.com.fritz.shopcart.controller.ItemController;
import shopcart.R;

public class ListActivity
    extends
        AppCompatActivity
{
    private static final int NORMAL = 1281;
    private static final int STRIKE = 16;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list);

        this.populateListItems( false );

        ImageButton btnAdd = findViewById( R.id.fbAdd );
        btnAdd.setOnClickListener( view ->
        {
            EditText ed = findViewById( R.id.tfdItem );
            ItemController.insertItem( this, ed );

            this.populateListItems( true );
        } );

        ImageButton backHome = findViewById( R.id.btnbackHome );
        backHome.setOnClickListener( view -> startActivity( new Intent( ListActivity.this, MainActivity.class ) ) );
    }

    /**
     * populateListItems
     *
     * @param refresh boolean
     */
    private void populateListItems( boolean refresh )
    {
        List<Item> items = ItemController.getListItems( this );

        ListView listViewItems = findViewById( R.id.itemsList );

        ArrayAdapter<Item> ad = new ArrayAdapter<Item>( this, android.R.layout.simple_list_item_1, items )
        {
            public View getView( int position, View convertView, ViewGroup parent )
            {
                TextView tv = new TextView( getContext() );

                if ( items.get( position ).isLined() )
                {
                    tv.setText( items.get( position ).getDesc() );
                    tv.setPaintFlags( STRIKE );
                }

                else
                {
                    tv.setText( items.get( position ).getDesc() );
                    tv.setPaintFlags( NORMAL );
                }

                tv.setPadding(30, 20, 0, 20);
                tv.setTextColor( Color.BLACK );
                tv.setTextSize( 20 );

                return tv;
            }
        };

        listViewItems.setAdapter( ad );

        listViewItems.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener()
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder( ListActivity.this );

            @Override
            public boolean onItemLongClick( AdapterView<?> adapterView, View view, int i, long l )
            {
                builder.create();
                builder.setMessage( "Deseja excluir o item: " + items.get( i ) + "?" );
                builder.setPositiveButton( "Sim", ( dialog, which ) ->
                {
                    try
                    {
                        Transaction db = new Transaction( ListActivity.this, CMN.DATABASE );
                        long result = db.deleteItemById( items.get( i ).getId() );

                        if ( result != -1 )
                        {
                            CMN.showAlert( ListActivity.this, "Item excluído com sucesso!" );
                            populateListItems( true );
                        }

                        else
                        {
                            CMN.showAlert( ListActivity.this, "Não foi possível excluir o item" );
                        }
                    }

                    catch ( Exception e )
                    {
                        CMN.showAlert( ListActivity.this, e.getMessage() );
                    }
                } ).setNegativeButton("Não", ( dialog, which ) -> {} ).show();

                return true;
            }
        } );

        listViewItems.setOnItemClickListener( ( adapterView, view, i, l ) ->
        {
            Transaction db = new Transaction( this, CMN.DATABASE );
            TextView text = (TextView) view;
            ContentValues value = new ContentValues();

            if ( items.get( i ).getLined() == 0 )
            {
                try
                {
                    value.put( CMN.LINED, 1 );
                    db.alterLined( value, items.get( i ).getId() );
                    text.setPaintFlags( STRIKE );

                    items.get( i ).setLined( 1 );
                }

                catch ( Exception e )
                {
                    CMN.showAlert( ListActivity.this, e.getMessage() );
                }
            }

            else
            {
                try
                {
                    value.put( CMN.LINED, 0 );
                    db.alterLined( value, items.get(i).getId() );
                    text.setPaintFlags( NORMAL );

                    items.get( i ).setLined( 0 );
                }

                catch ( Exception e )
                {
                    CMN.showAlert( ListActivity.this, e.getMessage() );
                }
            }
        } );

        if ( refresh )
        {
            listViewItems.setAdapter( null );
            this.populateListItems( false );
        }
    }
}