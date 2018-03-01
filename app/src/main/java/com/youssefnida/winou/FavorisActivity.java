package com.youssefnida.winou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.youssefnida.winou.DB.CITATION_DBMANAGER;

import java.util.List;

public class FavorisActivity extends AppCompatActivity {

    private ListView l;
    private FavorisAdapter adapter;
    private List<Citation> citations;
    private CITATION_DBMANAGER citationDb;
    private Citation itemCitation;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        /**MENU**/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        citationDb = new CITATION_DBMANAGER(this);

        //Initialiser les citations
        initCitations();

        //Liaison entre la source des données (Citations) et les controles du layout item :
        adapter = new FavorisAdapter(this, R.layout.item, citations);

        //Binding resources Array to ListAdapter
        l = (ListView) findViewById(R.id.list);
        l.setAdapter(adapter);

        Toast.makeText(this, citations.size() + " citations", Toast.LENGTH_LONG).show();

        //Click item
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                onListItemClick(l, view, position, id);
            }
        });


    }

    private void initCitations() {

        citations = citationDb.getAllCitation();
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {

        itemCitation = (Citation) l.getAdapter().getItem(position);
        menuVisible(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favoris, menu);
        this.menu = menu;
        menuVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_share was selected
            case R.id.action_share:

                //PARTAGE DE LA CITATION DANS LES MEDIAS
                if( itemCitation != null ) {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_TEXT, itemCitation.getCitation_text());
                    startActivity(Intent.createChooser(share, "Choisir le media"));
                    itemCitation = null;
                }else
                    Toast.makeText(this, " Selectionner une citation", Toast.LENGTH_LONG).show();

                break;

            // action with ID action_delete was selected
            case R.id.action_delete:
                //SUPPRIMER
                if(itemCitation != null) {
                    citationDb.supprimerCitation(itemCitation);
                    refraicher();
                    itemCitation = null;
                }else
                    Toast.makeText(this, " Selectionner une citation", Toast.LENGTH_LONG).show();

                break;

            default:
                break;
        }

        return true;
    }


    public void refraicher(){

        //Initialiser les citations
        initCitations();

        //Liaison entre la source des données (Citations) et les controles du layout item :
        adapter = new FavorisAdapter(this, R.layout.item, citations);

        //Binding resources Array to ListAdapter
        l.setAdapter(adapter);

        Toast.makeText(this, citations.size()+" citations", Toast.LENGTH_LONG).show();

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                onListItemClick(l, view, position, id);
            }
        });
    }

    private void menuVisible(Boolean bool){
        menu.findItem(R.id.action_share).setVisible(bool);
        menu.findItem(R.id.action_delete).setVisible(bool);
    }

}
