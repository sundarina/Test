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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainTestActivity3 extends AppCompatActivity {

    // определяем массив типа String


    String[] avengersName;
    List<String> avengersName2; //создаем лист, так как мы не можем удалять элдементы массива.
    ArrayAdapter<String> adapter;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test3);

        avengersName = getResources().getStringArray(R.array.avengers_Name);
        avengersName2 = new ArrayList<>();
        for(int i=0;i<avengersName.length;i++) {   //записываем из массива в лист
            avengersName2.add(avengersName[i]);
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        TextView textViev3 = (TextView) findViewById(R.id.textView3);

        registerForContextMenu(listView);
        registerForContextMenu(textViev3);

        // используем адаптер данных
        //adapter = new ArrayAdapter<>(this,
         //       android.R.layout.simple_list_item_1, avengersName);

        /*  this : объект класса Context, то есть в данном случае текущий объект activity
            android.R.layout.simple_list_item_1 : файл разметки списка, который фреймворк представляет по умолчанию.
            Он находится в папке Android SDK по пути platforms/[android-номер_версии]/data/res/layout.
            Если нас не удовлетворяет стандартная разметка списка, мы можем создать свою и потом в коде изменить id на id нужной нам разметки
            vengersName2  : массив данных  */

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, avengersName2);
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
                Intent scr2 = new Intent(this, MainTestActivity2.class);
                startActivity(scr2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //кнопка перехода назад (установлено в xml)

    public void menuClick(MenuItem item) {
        Intent scr4 = new Intent(this, MainTestActivity4.class);
        startActivity(scr4);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contex_menu, menu);
    }

    @Override

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.edit:
                // editNote(info.id);
                Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.edit) +
                                " context menu option for " + avengersName[(int) info.id],
                        Toast.LENGTH_SHORT).show();
                return true;

            case R.id.delete:
                //deleteNote(info.id);
                adapter.remove(adapter.getItem(info.position));

                Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.delete) +
                                " context menu option for " + avengersName[(int) info.id],
                        Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

}


