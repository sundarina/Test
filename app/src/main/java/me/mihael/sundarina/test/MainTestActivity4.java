package me.mihael.sundarina.test;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainTestActivity4 extends AppCompatActivity {

    String[] color;
    List<String> color2; //создаем лист, так как мы не можем удалять элдементы массива.
    ArrayAdapter<String> adapter;
    ListView listView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test4);


        color = getResources().getStringArray(R.array.color);
        color2 = new ArrayList<>();
        for (int i = 0; i < color.length; i++) {   //записываем из массива в лист
            color2.add(color[i]);
        }

        listView = (ListView) findViewById(R.id.listView);

        registerForContextMenu(listView);

        // используем адаптер данных
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, color2);
        listView.setAdapter(adapter);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void menuClick(MenuItem item) {
        Intent scr1 = new Intent(this, MainTestActivity.class);
        startActivity(scr1);
    }

    public void onSettingsMenuClick(MenuItem item) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Клац",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //  Log.d("BTN", "Button clicked");
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_cat1:
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Клац1",
                        Toast.LENGTH_SHORT);
                toast1.show();
                return true;
            case R.id.action_cat2:
                Toast toast2 = Toast.makeText(getApplicationContext(),
                        "Клац2",
                        Toast.LENGTH_SHORT);
                toast2.show();
                return true;
            case R.id.action_favorite:
                Intent scr3 = new Intent(this, MainTestActivity3.class);
                startActivity(scr3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Button button  = (Button) findViewById(R.id.button);

    ActionMode mActionMode;


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {


            // Called when the action mode is created; startActionMode() was called
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate a menu resource providing context menu items
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.contex_menu, menu);
                return true;
            }

            // Called each time the action mode is shown. Always called after onCreateActionMode, but
            // may be called multiple times if the mode is invalidated.
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false; // Return false if nothing is done
            }

            // Called when the user selects a contextual menu item
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                switch (item.getItemId()) {

                    // case R.id.menu_share:
                    //     shareCurrentItem();
                    //    mode.finish(); // Action picked, so close the CAB
                    case R.id.edit:
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Edit",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;

                    case R.id.delete:
                        Toast toast2 = Toast.makeText(getApplicationContext(),
                                "Delete",
                                Toast.LENGTH_SHORT);
                        toast2.show();
                        return true;
                    default:
                        return false;
                }
            }

            // Called when the user exits the action mode
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mActionMode = null;
            }
        };

    public void onClick(View v) {
        if (mActionMode == null)
            mActionMode = startActionMode(mActionModeCallback);
        else
            mActionMode.finish();
    }


       /* button.setOnLongClickListener(new View.OnLongClickListener()

        {

            // Called when the user long-clicks on someView
            public boolean onLongClick (View v){
            if (mActionMode != null) {
                return false;
            }

            // Start the CAB using the ActionMode.Callback defined above
            mActionMode = getActivity().startActionMode(mActionModeCallback);
            button.setSelected(true);
            return true;
        }
        }

        );*/

}


