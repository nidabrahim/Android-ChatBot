package res.layout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youssefnida.winou.ChatActivity;
import com.youssefnida.winou.DB.CITATION_DBMANAGER;
import com.youssefnida.winou.FavorisActivity;
import com.youssefnida.winou.Intromanager;
import com.youssefnida.winou.Jeux;
import com.youssefnida.winou.Main2;
import com.youssefnida.winou.NotificationReciever;
import com.youssefnida.winou.R;
import com.youssefnida.winou.SettingActivity;
import com.youssefnida.winou.TranclateActivity;

import java.util.Calendar;

public class AcceuilActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private Intromanager intromanager;
    private TextView [] dots;
    Button next,skip;
    private LinearLayout dotsLayout;
    private int [] layouts;
    private ViewPagerAdapter viewPagerAdapter;
    private CITATION_DBMANAGER citationDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** PERMISSIONS **/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /** ALARM NOTIFICATION **/
        citationDb = new CITATION_DBMANAGER(this);
        int heureAlarm = citationDb.getHeure1();
        int minAlarm = citationDb.getMinute1();
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(System.currentTimeMillis());

        if ( timerDepasser(minAlarm,heureAlarm)  )
        {
            calender.add(Calendar.DATE, 1);
        }

        calender.set(Calendar.HOUR_OF_DAY, heureAlarm);
        calender.set(Calendar.MINUTE, minAlarm);
        calender.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        if (savedInstanceState == null) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(),AlarmManager.INTERVAL_HALF_DAY , pendingIntent);
        }


        /**SLIDERS**/
        intromanager = new Intromanager(this);
        if(!intromanager.check()){
            intromanager.setFirst(false);
            Intent i = new Intent(AcceuilActivity.this, Main2.class);
            startActivity(i);
            finish();
        }

        if(Build.VERSION.SDK_INT >=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_acceuil);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout)findViewById(R.id.layoutDots);
        skip = (Button) findViewById(R.id.btn_skip);
        next = (Button) findViewById(R.id.btn_next);

        layouts = new int[] {R.layout.activity_slide1,R.layout.activity_slide2,R.layout.activity_slide3};

        addButtonDots(0);
        changeStatusBarColor();
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        skip.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Intent i = new Intent(AcceuilActivity.this, Main2.class);
                startActivity(i);
                finish();
            }

        });

        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                int current = getItem(+1);
                if(current<layouts.length){
                    viewPager.setCurrentItem(current);
                }else{

                    Intent i = new Intent(AcceuilActivity.this, Main2.class);
                    startActivity(i);
                    finish();
                }
            }

        });

    }

    private boolean timerDepasser(int minute, int heure){

        boolean val = false;
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(System.currentTimeMillis());

        int curHr = calender.get(Calendar.HOUR_OF_DAY);
        int curMin = calender.get(Calendar.MINUTE);

        if( minute == 59 ) {
            if (curHr >= heure+1 && curMin >= 0) val = true;
        }
        else
        if (curHr >= heure && curMin >= minute+1  ) val = true;

        return val;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acceuil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Chat
            Intent intent = new Intent(this, ChatActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            // Traducteur
            Intent intent = new Intent(this, TranclateActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            // Quotes FAVORIS
            Intent intent = new Intent(this, FavorisActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_manage) {
            // Game
            Intent intent = new Intent(this, Jeux.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_send) {
            // Parametre
            Intent intent = new Intent(this, SettingActivity.class);
            this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addButtonDots(int position){

        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(colorActive[position]);
    }

    private int getItem(int i){

        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

        }

        @Override
        public void onPageSelected(int position){

            addButtonDots(position);
            if(position==layouts.length-1){

                skip.setVisibility(View.GONE);
            }
            else
            {

                skip.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state){

        }

    };

    public void changeStatusBarColor(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class ViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            View v  = (View) object;
            container.removeView(v);
        }
    }
}
