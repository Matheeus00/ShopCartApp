package br.com.fritz.shopcart.model;

import androidx.annotation.NonNull;

public class Item
{
    private int id;
    private int lined;
    private String desc;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public int getLined()
    {
        return lined;
    }

    public void setLined( int lined )
    {
        this.lined = lined;
    }

    public String getDesc()
    {
        return desc != null ? desc : "";
    }

    public void setDesc( String desc )
    {
        this.desc = desc;
    }

    public boolean isLined()
    {
        return getLined() == 1;
    }

    @NonNull
    @Override
    public String toString()
    {
        return getDesc();
    }
}
