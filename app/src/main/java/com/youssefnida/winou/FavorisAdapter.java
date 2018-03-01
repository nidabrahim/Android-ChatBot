package com.youssefnida.winou;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by youssefNIDA on 03/04/2017.
 */

public class FavorisAdapter extends ArrayAdapter {

    private LayoutInflater inflater;
    private List<Citation> citation;


    public FavorisAdapter(Context context, int resource, List<Citation> citation) {
        //mettre le layout qui contient la liste
        super(context, resource, citation);

        this.inflater = LayoutInflater.from(context);
        this.citation = citation;
    }

    @Override
    //getView retournera la vue de l’item pour l’affichage.
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        //Si la vue n'est pas recyclée
        if(convertView == null)
        {
            holder = new ViewHolder();
            //On va charger notre layout xml dans un objet View via la méthide inflater
            convertView = inflater.inflate(R.layout.item, null);
            //On place les composants de notre layout dans le holder
            holder.quotes_view = (TextView)convertView.findViewById(R.id.label);
            setFont(holder.quotes_view,"A_DAY_WITHOUT_SUN.otf");
            //sauvgarde la ref du holder en memoire pour la réutilisation par la suite
            convertView.setTag(holder);
        } else {
            //réutilisation du holder déja existant
            holder = (ViewHolder) convertView.getTag();
        }

        //remplir notre vue
        holder.quotes_view.setText(citation.get(position).getCitation_text());
        return convertView;
    }

    public void setFont(TextView textView, String fontName) {
        if(fontName != null){
            try {
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets() , "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }

    static class ViewHolder
    {
        public TextView quotes_view;
    }
}
