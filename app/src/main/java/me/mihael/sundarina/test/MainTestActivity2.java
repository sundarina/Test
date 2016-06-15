package me.mihael.sundarina.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainTestActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main_test2);
        setContentView(R.layout.activity_main_test2);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

     public boolean onOptionsItemSelected(MenuItem item) {
        //  Log.d("BTN", "Button clicked");
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        TextView infoTextView = (TextView) findViewById(R.id.textView2);

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_cat1:
                infoTextView.setText("Вы еще не сьели кота!");
                return true;
            case R.id.action_cat2:
                infoTextView.setText("Вы еще не сьели кошку!");
                return true;
            case R.id.action_cat3:
                 infoTextView.setText("Вы еще не сьели котёнка!");
                return true;
            case R.id.action_favorite:
                Intent scr1 = new Intent(this, MainTestActivity.class);
                startActivity(scr1);
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSettingsMenuClick(MenuItem item) {
        Toast toast = Toast.makeText(getApplicationContext(),
        "Пора уничтожить весь выводок котов!", Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    public void menuClick(MenuItem item) {
        Intent scr3 = new Intent(this, MainTestActivity3.class);
        startActivity(scr3);
    }
}
