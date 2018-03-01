package com.youssefnida.winou;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youssefnida.winou.DB.CITATION_DBMANAGER;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

import java.util.Calendar;


public class QuotesActivity extends AppCompatActivity {

    private TextView quotes_view;
    private RelativeLayout relativeLayout;
    private String quotes_text;
    private CITATION_DBMANAGER citationDb;

    private static final String CITATION_MATIN = "citation matin";
    private static final String CITATION_SOIR = "citation soir";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        citationDb = new CITATION_DBMANAGER(this);

        //CITATION
        relativeLayout = (RelativeLayout) findViewById(R.id.content_quotes);
        quotes_view = (TextView) findViewById(R.id.quotes_view);
        setFont(quotes_view,"A_DAY_WITHOUT_SUN.otf");

        //MENU
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CHARGEMENT
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                String cmd = "";
                String path = Environment.getExternalStorageDirectory().toString();
                Bot bot = new Bot("quotes", path);
                Chat chat = new Chat(bot);

                /** HEURE COURANT **/
                Calendar calender = Calendar.getInstance();
                calender.setTimeInMillis(System.currentTimeMillis());
                int curHr = calender.get(Calendar.HOUR_OF_DAY);
                if( curHr <= 12 && curHr >= 1 ) cmd = CITATION_MATIN;
                else if( curHr <= 23 && curHr >= 13 ) cmd = CITATION_SOIR;

                /** GET QUOTE **/
                String quotes_jour = chat.multisentenceRespond(cmd);
                return quotes_jour;
            }

            @Override
            protected void onPostExecute(String quotes_jour) {

                String quotes_du_jour = quotes_jour;
                if(quotes_jour.equals("I have no answer for that."))
                    quotes_du_jour = "Pas de citation aujourd'hui";

                quotes_view.setText(quotes_du_jour);
            }

        }.execute();

        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(System.currentTimeMillis());
        int curHr = calender.get(Calendar.HOUR_OF_DAY);
        if( curHr <= 17 && curHr >= 5 ) relativeLayout.setBackgroundResource(R.drawable.c);
        else if( curHr <= 23 && curHr >= 18 ) relativeLayout.setBackgroundResource(R.drawable.a);


        //CITATION DU JOUR
        quotes_text = (String) quotes_view.getText();

        //BUTTON AJOUTER AUX FAVORIS
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Citation citation = new Citation((String) quotes_view.getText());
                citationDb.insertCitation(citation);

                Snackbar.make(view, "Citation ajoutée aux favoris"+citation.getCitation_text(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quotes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_share was selected
            case R.id.action_share:

                //PARTAGE DE LA CITATION DANS LES MEDIAS
                quotes_text = (String) quotes_view.getText();
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, quotes_text);
                startActivity(Intent.createChooser(share, "Choisir le media"));

                break;

            // action with ID action_favoris was selected
            case R.id.action_favoris:

                //MES FAVORIS
                Intent intent = new Intent(this, FavorisActivity.class);
                this.startActivity(intent);

                break;

            default:
                break;
        }

        return true;
    }




    public void setFont(TextView textView, String fontName) {
        if(fontName != null){
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }

}
