package com.youssefnida.winou;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Jeux extends Activity {
	// Added for sound
	private static int i= 100 ;

	private SoundPool soundPool;
	private int soundIDbear;
	private int soundIDcat;
	private int soundIDdog;
	private int soundIDtiger;
	private int soundIDlion;
	private int soundIDelephant;
	private int soundIDhorse;
	private int soundIDpig;
	private int soundIDcrow;
	private int soundIDparrot;
	private int soundIDeagle;
	private int soundIDturkey;
	private int soundIDrooster;
	private int soundIDowl;
	private int soundIDduck;
	private int soundIDpeacock;
	// Toast t;
	boolean loaded = false;

	String[] sounds = new String[] {
			"Sound 1",
			"Sound 2",
			"Sound 3",
			"Sound 4",
			"Sound 5",
			"Sound 6",
			"Sound 7",
			"Sound 8",
			"Sound 9",
			"Sound 10",
			"Sound 11",
			"Sound 12",
			"Sound 13",
			"Sound 14"

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jeux);
		/*
		 * ImageView v1=new ImageView(this); LinearLayout.LayoutParams
		 * layoutParams1 = new LinearLayout.LayoutParams(60, 60);
		 * layoutParams1.weight=1; layoutParams1.topMargin=20;
		 * layoutParams1.bottomMargin=20;
		 * 
		 * 
		 * v1.setImageResource(R.drawable.catsil2);
		 * v1.setLayoutParams(layoutParams1); ImageView v2=new ImageView(this);
		 * LinearLayout.LayoutParams layoutParams2 = new
		 * LinearLayout.LayoutParams(60, 60); layoutParams2.weight=1;
		 * layoutParams2.topMargin=20; layoutParams2.bottomMargin=20;
		 * 
		 * v2.setImageResource(R.drawable.roostersil);
		 * v2.setLayoutParams(layoutParams2);
		 */

		List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

		for(int i=0;i<10;i++){
			HashMap<String, String> hm = new HashMap<String,String>();
			hm.put("txt", sounds[i]);
			hm.put("flag", Integer.toString( R.drawable.son) );
			aList.add(hm);
		}
		// Keys used in Hashmap
		String[] from = { "flag","txt"};

		// Ids of views in listview_layout
		int[] to = { R.id.flag,R.id.txt};

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		// spec1.setIndicator("ANIMALS");
		spec1.setIndicator("", getResources().getDrawable(R.drawable.sound));

		TabSpec spec2 = tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("", getResources()
				.getDrawable(R.drawable.animal));

		spec2.setContent(R.id.tab2);
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		/*
		 * for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) { TextView
		 * tv = (TextView)
		 * tabHost.getTabWidget().getChildAt(i).findViewById(android
		 * .R.id.title); tv.setTextColor(0); }
		 */

		final GridView gridview1 = (GridView) findViewById(R.id.gridview1);
		//gridview1.setAdapter(new ImageAdapter1(this));
		final GridView gridview2 = (GridView) findViewById(R.id.gridview2);
		gridview1.setBackgroundResource(R.drawable.fleurgris);
		// Instantiating an adapter to store each items
		// R.layout.listview_layout defines the layout of each item
		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.gridview_layout, from, to);

		// Getting a reference to gridview of MainActivity


		// Setting an adapter containing images to the gridview
		gridview1.setAdapter(adapter);
		gridview2.setAdapter(new ImageAdapter2(this));
		// Added for sound
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			// @Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
									   int status) {
				loaded = true;
			}
		});
		soundIDbear = soundPool.load(this, R.raw.bear, 1);

		soundIDcat = soundPool.load(this, R.raw.cat, 1);
		soundIDdog = soundPool.load(this, R.raw.dog, 1);
		soundIDelephant = soundPool.load(this, R.raw.elephant, 1);
		soundIDhorse = soundPool.load(this, R.raw.horse, 1);
		soundIDlion = soundPool.load(this, R.raw.lion, 1);
		soundIDpig = soundPool.load(this, R.raw.pig, 1);
		soundIDtiger = soundPool.load(this, R.raw.tiger, 1);

		soundIDcrow = soundPool.load(this, R.raw.crow, 1);
		soundIDduck = soundPool.load(this, R.raw.duck, 1);
		soundIDeagle = soundPool.load(this, R.raw.eagle, 1);
		soundIDowl = soundPool.load(this, R.raw.owl, 1);
		soundIDparrot = soundPool.load(this, R.raw.parrot, 1);
		soundIDpeacock = soundPool.load(this, R.raw.peacock, 1);
		soundIDrooster = soundPool.load(this, R.raw.rooster, 1);
		soundIDturkey = soundPool.load(this, R.raw.turkey, 1);
		Toast.makeText(this, "Switch Tabs to select between Animals and Sounds",
				Toast.LENGTH_LONG).show();

		gridview1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
									int position, long id) {
				soundPool.stop(soundIDbear);
				soundPool.stop(soundIDcat);
				soundPool.stop(soundIDdog);
				soundPool.stop(soundIDelephant);
				soundPool.stop(soundIDhorse);
				soundPool.stop(soundIDlion);
				soundPool.stop(soundIDpig);
				soundPool.stop(soundIDtiger);
				soundPool.stop(soundIDcrow);
				soundPool.stop(soundIDpeacock);
				soundPool.stop(soundIDrooster);
				soundPool.stop(soundIDturkey);
				soundPool.stop(soundIDowl);
				soundPool.stop(soundIDeagle);
				soundPool.stop(soundIDparrot);
				soundPool.stop(soundIDduck);

				// int id1=(int)id;
				// Resources.getSystem().getResourceEntryName(id1);
				// ImageView img=(ImageView)gridview1.getChildAt(position);
				// Drawable draw=img.getDrawable().getCurrent();
				AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
				float actualVolume = (float) audioManager
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				float maxVolume = (float) audioManager
						.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float volume = actualVolume / maxVolume;
				// int a=draw.hashCode();
				// String s=Resources.getSystem().getString(R.drawable.cat_1);
				// String //display_name="";
				switch (position) {
					case 0:
						// //display_name="Bear";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDbear, volume, volume, 1, 0, 1f);

						}
						break;
					case 1:
						// display_name="Cat";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDcat, volume, volume, 1, 0, 1f);

						}
						break;

					case 2:
						// display_name="Elephant";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDelephant, volume, volume, 1, 0,
									1f);

						}
						break;
					case 3:
						// display_name="Horse";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDhorse, volume, volume, 1, 0, 1f);

						}
						break;

					case 4:
						// display_name="Lion";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDlion, volume, volume, 1, 0, 1f);

						}
						break;


					case 5:
						// display_name="Pig";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDpig, volume, volume, 1, 0, 1f);

						}
						break;
					case 6:
						// display_name="Tiger";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDtiger, volume, volume, 1, 0, 1f);

						}
						break;
					case 7:
						// display_name="Crow";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDcrow, volume, volume, 1, 0, 1f);

						}
						break;



					case 8:
						// display_name="Peacock";
						if (loaded) {
							i=position ;
							soundPool
									.play(soundIDpeacock, volume, volume, 1, 0, 1f);

						}

						break;

					case 9:
						// display_name="Turkey";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDturkey, volume, volume, 1, 0, 1f);

						}
						break;

					case 10:
						// display_name="Owl";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDowl, volume, volume, 1, 0, 1f);

						}
						break;
					case 11:
						// display_name="bald eagle";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 12:
						// display_name="Duck";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDduck, volume, volume, 1, 0, 1f);

						}
						break;
					case 13:
						// display_name="Parrot";
						if (loaded) {
							i=position ;
							soundPool.play(soundIDparrot, volume, volume, 1, 0, 1f);

						}
						break;

					default:

						break;
				}

				/*
				 * 
				 * t=Toast.makeText(MainActivity.this, //display_name,
				 * Toast.LENGTH_SHORT); t.cancel(); //t.cancel(); t.show();
				 */

			}
		});

		gridview2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
									int position, long id) {
				soundPool.stop(soundIDcrow);
				soundPool.stop(soundIDpeacock);
				soundPool.stop(soundIDrooster);
				soundPool.stop(soundIDturkey);
				soundPool.stop(soundIDowl);
				soundPool.stop(soundIDeagle);
				soundPool.stop(soundIDparrot);
				soundPool.stop(soundIDduck);
				soundPool.stop(soundIDbear);
				soundPool.stop(soundIDcat);
				soundPool.stop(soundIDdog);
				soundPool.stop(soundIDelephant);
				soundPool.stop(soundIDhorse);
				soundPool.stop(soundIDlion);
				soundPool.stop(soundIDpig);
				soundPool.stop(soundIDtiger);
				AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
				float actualVolume = (float) audioManager
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				float maxVolume = (float) audioManager
						.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float volume = actualVolume / maxVolume;
				// display_name="";
				Toast toast = Toast.makeText(getApplicationContext(), "Bravo !!", Toast.LENGTH_SHORT);
				Toast toast1 = Toast.makeText(getApplicationContext(), "Essay encore !!", Toast.LENGTH_SHORT);
				Toast toast2 = Toast.makeText(getApplicationContext(), "Choisi un son premièrement", Toast.LENGTH_SHORT);
				switch (position) {
					case 0:
						// display_name="Bald Eagle";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 1:
						// display_name="Crow";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 2:
						// display_name="Parrot";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 3:
						// display_name="Duck";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 4:
						// display_name="Peacock";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 5:
						// display_name="Owl";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 6:
						// display_name="Turkey";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 7:
						// display_name="Rooster";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 8:
						// display_name="Rooster";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 9:
						// display_name="Rooster";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 10:
						// display_name="Rooster";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 11:
						// display_name="Rooster";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 12:
						// display_name="Rooster";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;
					case 13:
						// display_name="Rooster";
						if (loaded) {

							if(position==13-i)
							{
								toast.show();
							}
							else if(position==100)
							{
								toast2.show();
							}
							else
							{
								toast1.show();
							}
							//soundPool.play(soundIDeagle, volume, volume, 1, 0, 1f);

						}
						break;

					default:

						break;
				}

				// Toast.makeText(MainActivity.this, display_name,
				// Toast.LENGTH_SHORT).show();
			}
		});

		// @Override
		/*
		 * public boolean onCreateOptionsMenu(Menu menu) {
		 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
		 */

	}
}
