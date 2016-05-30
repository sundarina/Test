package me.mihael.sundarina.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainTestActivity3 extends AppCompatActivity {

    // определяем массив типа String


    String[] avengersName;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test3);

        avengersName = getResources().getStringArray(R.array.avengers_Name);

        ListView listView = (ListView)findViewById(R.id.listView);
        TextView textViev3 = (TextView)findViewById(R.id.textView3);

        registerForContextMenu(listView);
        registerForContextMenu(textViev3);

    // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, avengersName);
        listView.setAdapter(adapter);
    }


        public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void onSettingsMenuClick(MenuItem item) {
        TextView infoTextView = (TextView) findViewById(R.id.textView3);
        infoTextView.setText("Хуетинс");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //  Log.d("BTN", "Button clicked");
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        TextView infoTextView = (TextView) findViewById(R.id.textView3);

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
                 infoTextView.setText("!!!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void menuClick(MenuItem item) {
        Intent scr1 = new Intent(this, MainTestActivity.class);
        startActivity(scr1);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
           switch (v.getId()) {
            case R.id.textView3:
                menu.add(0, MENU_COLOR_RED, 0, "Red");
                menu.add(0, MENU_COLOR_GREEN, 0, "Green");
                menu.add(0, MENU_COLOR_BLUE, 0, "Blue");
                break;
           }
    }


}
