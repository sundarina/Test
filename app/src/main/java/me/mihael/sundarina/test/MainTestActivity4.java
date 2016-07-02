package me.mihael.sundarina.test;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainTestActivity4 extends AppCompatActivity {

    String[] color;
    List<String> color2; //создаем лист, так как мы не можем удалять элдементы массива.
   // ArrayAdapter<String> adapter;
    ListView listView;
    CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test4);


        color = getResources().getStringArray(R.array.color); //подтягиваем масив с string.xml

        color2 = new ArrayList<String>();
            for (int i = 0; i < color.length; i++) {   //записываем из массива в лист
            color2.add(color[i]);
        }

        listView = (ListView) findViewById(R.id.listView);

        registerForContextMenu(listView);

        // используем адаптер данных
      //  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, color2);



        //List<String> list = new ArrayList<String>();
       /* String [] numbers = {"One", "Two", "Three",
                "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten","Eleven",
                "Twelve", "Thirteen", "Fourteen", "Fifteen"};
        int size = numbers.length;
        for (int I=0; I<size; I++){
            list.add(numbers[I]);
        }*/

        mAdapter = new CustomAdapter(this,R.layout.custom_tv, color2);       // используем адаптер, созданный и описаный отдельным классом.
                                                                             // подтягиваем в него  наш лист-масив и текствью - макет для отображения одного элемента списка.
        listView.setAdapter(mAdapter);          // прикрепляем адаптер к лист-вью


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //на тот случай, если пользователь 1 раз нажмет на элемент списка, + использование анонимного класса
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String string = (String) parent.getAdapter().getItem(position); //подтягивается текст в элементе листвью учитывая позицию
                Toast.makeText(MainTestActivity4.this, string, Toast.LENGTH_SHORT).show(); //выводится название элемента всплывающим сообщением(опционально)

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { //когда пользователь долго жмет на элемент списк
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);   // вкл мультинажатия
                // Capture ListView item click
                listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                    @Override

                    //вызывается когда пунк выбран или не выбран во время selection mode.

                    public void onItemCheckedStateChanged(ActionMode mode,
                                                          int position, long id, boolean checked) {

                        // Prints the count of selected Items in title
                        mode.setTitle(listView.getCheckedItemCount() + " Selected");   //установка в аст бар оповещение про то, что нажато какоето количество элементов

                        // Toggle the state of item after every click on it  Переключение состояния элемента после каждого клика на него
                        mAdapter.toggleSelection(position);
                    }

                    /**
                     * Called to report a user click on an action button.
                     * @return true if this callback handled the event,
                     *          false if the standard MenuItem invocation should continue.
                     *
                     *          Вызывается чтобы сообщить пользователю нажать на кнопку действия.
                                          * @return true, если этот обратный вызов обрабатывается событие,
                                          * false, если стандартный MenuItem вызов должен продолжаться.
                     */
                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        if (item.getItemId() == R.id.delete){   //если нажато на символ delete
                            SparseBooleanArray selected = mAdapter.getSelectedIds();

                            /*
                            //SparseBooleanArrays отражает целые числа в логических значениях.
                           // В отличии от списочного массива, может иметь пропуски в индексах. Эфективней для экономии памяти, чем HashMap
                           Он представляет собой Map(int, boolean). Ключ (int) – это позиция элемента,
                            а значение (boolean) – это выделен пункт списка или нет. Причем SparseBooleanArray хранит инфу не о всех пунктах,
                             а только о тех, с которыми проводили действие (выделяли и снимали выделение).
                             Мы перебираем его содержимое, получаем позицию пункта .
                              */


                            short size = (short)selected.size();
                            for (byte I = 0; I<size; I++){
                                if (selected.valueAt(I)) {
                                    String selectedItem = mAdapter.getItem(selected.keyAt(I));
                                    mAdapter.remove(selectedItem); //скрытие/удаление выбраного элемента
                                }
                            }

                            // Close CAB (Contextual Action Bar)
                            mode.finish();
                            return true;
                        }
                        return false;
                    }

                    /**
                     * Called when action mode is first created.
                     * The menu supplied will be used to generate action buttons for the action mode.
                     * @param mode ActionMode being created
                     * @param menu Menu used to populate action buttons
                     * @return true if the action mode should be created,
                     *          false if entering this mode should be aborted.
                     *
                     *          Вызывается, когда режим действия сначала создается.
                                          * В меню подача будет использоваться для создания командных кнопок для режима действий.
                                          * @param Режим ActionMode создается
                                          *  @param меню используется для заполнения командных кнопок
                                          * @return true, если режим действия должны быть созданы,
                                          * false, если ввод этот режим должен быть прерван.
                     */



                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        mode.getMenuInflater().inflate(R.menu.menu_4, menu);
                        return true;
                    }

                    /**
                     * Called when an action mode is about to be exited and destroyed.
                     */
                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        //  mAdapter.removeSelection();
                    }

                    /**
                     * Called to refresh an action mode's action menu whenever it is invalidated.
                     * @return true if the menu or action mode was updated, false otherwise.
                     */
                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }
                });
                return false;
            }
        });
    }



    //cоздаем меню

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater(); //this можно не указывать, так как действие происходит внутри класса
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //кнопка перехода назад (установлено в xml)

    public void menuClick(MenuItem item) {
        Intent scr1 = new Intent(this, MainTestActivity.class);
        startActivity(scr1);
    }

    //кнопка сетингс (установлено в xml)
    public void onSettingsMenuClick(MenuItem item) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Клац",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    //обработка выбора в меню опций сверху
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
            case R.id.action_cat3:
                Toast toast3 = Toast.makeText(getApplicationContext(),
                        "Клац3",
                        Toast.LENGTH_SHORT);
                toast3.show();
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

    //создаем строку контекстных действий

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

    //вызываем строку контекстных действий при нажатии
    public void onClick(View v) {
        if (mActionMode == null)
            mActionMode = startActionMode(mActionModeCallback);
        else
            mActionMode.finish();
    }

}


