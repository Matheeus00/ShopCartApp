package br.com.fritz.shopcart;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.fritz.shopcart.util.CMN;
import shopcart.R;

public class MainActivity
    extends
        AppCompatActivity
{

    TextView tvCompliment;

    @RequiresApi( api = Build.VERSION_CODES.O )
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        setCompliment();

        Button btnNew = findViewById( R.id.btnNew );
        btnNew.setOnClickListener( view -> startActivity( new Intent( MainActivity.this, ListActivity.class ) ) );

        Button btnView = findViewById( R.id.btnView );
        btnView.setOnClickListener( view -> startActivity( new Intent( MainActivity.this, ListActivity.class ) ) );
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setCompliment()
    {
        tvCompliment = findViewById( R.id.tvCompliment );
        tvCompliment.getText();

        if ( CMN.timeNow().getHour() > 6 && CMN.timeNow().getHour() < 13 )
        {
            tvCompliment.setText( "Bom dia!" );
        }

        else if ( CMN.timeNow().getHour() > 13 && CMN.timeNow().getHour() < 18 )
        {
            tvCompliment.setText( "Boa tarde!" );
        }

        else if ( CMN.timeNow().getHour() > 18 && CMN.timeNow().getHour() < 23 )
        {
            tvCompliment.setText( "Boa noite!" );
        }

        else if ( CMN.timeNow().getHour() > 23 && CMN.timeNow().getHour() < 6 )
        {
            tvCompliment.setText( "Boa madrugada!" );
        }

        else
        {
            tvCompliment.setText( "Olá!" );
        }
    }
}