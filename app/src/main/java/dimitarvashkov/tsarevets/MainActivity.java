package dimitarvashkov.tsarevets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    /*
     Some improvement tips (with translations):
     - You should use the strings.xml file to save strings. Later on it's the easiest thing
       to translate your app to more than to languages. But you should use it from the beginning.
       Also mention to choose english as standard language.
     - You also should not implement the scanner and the media player two times. If there are
       more translation in the future and you have to change something in the code you have to
       change it everywhere. You should implement a single activity which plays the different
       audiofiles based on the button clicked in the mainactivity. This will be also less
       work to add translations.

     In the poster at the museums you maybe have to hide the example qr-code a little bit. In
     some cases the qr-code-scanner scans it (instead of the qr-code on the right side)
     and the process fails.

     I didn't change these things because I have not so much time but maybe in the future I can
     help you more.

     Best regards
     Daniel Sous (Coala App)
      */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout English = (LinearLayout) findViewById(R.id.English);
        English.setClickable(true);
        English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, English.class);
                startActivity(i);
            }
        });

        LinearLayout Bulgarian = (LinearLayout) findViewById(R.id.Bulgarian);
        Bulgarian.setClickable(true);
        Bulgarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Bulgarian.class);
                startActivity(i);
            }
        });

    }
}
