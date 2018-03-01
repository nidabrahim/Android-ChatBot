package com.youssefnida.winou;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class TranclateActivity extends AppCompatActivity {

    TextToSpeech tts;
    Translate translator;
    private EditText MyInputText;
    private Button MyTranslateButton;
    private TextView MyOutputText;

    private Toolbar toolbar;
    private Spinner spinner_nav;
    private Spinner spinner_nav2;

    private String from = "Français";
    private String to = "Français";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranclate);

        /** PERMISSIONS **/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /** DECLARATION **/
        MyInputText = (EditText)findViewById(R.id.InputText);
        MyTranslateButton = (Button)findViewById(R.id.TranslateButton);
        MyOutputText = (TextView)findViewById(R.id.OutputText);

        /** MENU **/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /** SPINNER **/
        spinner_nav = (Spinner) findViewById(R.id.spinner_nav);
        spinner_nav2 = (Spinner) findViewById(R.id.spinner_nav2);

        /** CLICK TRANSLATE**/
        MyTranslateButton.setOnClickListener(MyTranslateButtonOnClickListener);

        addItemsToSpinner();

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });

    }

    /** ADD ITEMS LANGUAGE INTO SPINNER DYNAMICALLY **/
    public void addItemsToSpinner() {

        ArrayList<String> list = new ArrayList<String>();
        list.add("Français");
        list.add("Anglais");
        list.add("Arabe");
        list.add("Néerlandais");
        list.add("Danois");
        list.add("Allemand");
        list.add("Grec");
        list.add("Hindi");
        list.add("Malais");
        list.add("Russe");
        list.add("Espanol");
        list.add("Italien");
        list.add("Coréen");
        list.add("Suédois");
        list.add("Turc");
        list.add("Vietnamien");
        list.add("Portugais");

        /** ADAPTER FOR SPINNER **/
        CustomSpinnerAdapter spinAdapter = new CustomSpinnerAdapter(
                getApplicationContext(), list);

        spinner_nav.setAdapter(spinAdapter);
        spinner_nav2.setAdapter(spinAdapter);

        /** CHOICE FROM LANGUAGE **/
        spinner_nav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

                String item = adapter.getItemAtPosition(position).toString();
                from = item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        /** CHOICE TO LANGUAGE **/
        spinner_nav2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                String item = adapter.getItemAtPosition(position).toString();
                to = item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


    /** ONCLICK **/
    private Button.OnClickListener MyTranslateButtonOnClickListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            new TransProc().execute();
        }
    };


    /** TRANSLATE PROCESS **/
    private class TransProc extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;
        protected void onError(Exception ex) {}

        @Override
        protected Void doInBackground(Void... params) {
            try {
                translator = new Translate();
                Thread.sleep(1000);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(TranclateActivity.this, null, "Traduction ...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();
            super.onPostExecute(result);
            translating(from,to);
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


    /** TRANSLATOR **/
    public void translating(String from, String to){

        String input = MyInputText.getText().toString();
        String output = translator.translte(input, langueMapping(from), langueMapping(to));
        MyOutputText.setText(output);
        tts.setLanguage(new Locale(langueMapping(to)));
        tts.speak(output, TextToSpeech.QUEUE_FLUSH, null);
    }


    /** MAPPING LANGUAGE **/
    private String langueMapping(String langue){
        String abr = "fr";

        if( langue.equals("Français") ) abr ="fr";
        else if( langue.equals("Anglais") ) abr ="en";
        else if( langue.equals("Arabe") ) abr ="ar";
        else if( langue.equals("Néerlandais") ) abr ="nl";
        else if( langue.equals("Danois") ) abr ="da";
        else if( langue.equals("Allemand") ) abr ="de";
        else if( langue.equals("Grec") ) abr ="el";
        else if( langue.equals("Hindi") ) abr ="hi";
        else if( langue.equals("Malais") ) abr ="ms";
        else if( langue.equals("Russe") ) abr ="ru";
        else if( langue.equals("Espanol") ) abr ="es";
        else if( langue.equals("Italien") ) abr ="it";
        else if( langue.equals("Coréen") ) abr ="ko";
        else if( langue.equals("Suédois") ) abr ="sv";
        else if( langue.equals("Turc") ) abr ="tr";
        else if( langue.equals("Vietnamien") ) abr ="vi";
        else if( langue.equals("Portugais") ) abr ="pt";

        return abr;
    }


    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

}
