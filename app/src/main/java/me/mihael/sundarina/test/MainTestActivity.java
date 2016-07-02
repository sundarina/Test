package me.mihael.sundarina.test;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import static me.mihael.sundarina.test.R.drawable.catroll;
import static me.mihael.sundarina.test.R.drawable.roll;

public class MainTestActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      //  Log.d("BTN", "Button clicked");
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        TextView infoTextView = (TextView) findViewById(R.id.textView);


        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_cat1:
                infoTextView.setText("1");
                return true;
            case R.id.action_cat2:
                infoTextView.setText("2");
                return true;
            case R.id.action_cat3:
                infoTextView.setText("3");
                return true;
            case R.id.action_favorite:
               // MenuItem item1 = menu.findItem(R.id.action_favorite);
                //item.setVisible(false);
                item.setEnabled(false);  //неактивная кнопка меню

               // infoTextView.setText("!!!");
               // Toast toast1 = Toast.makeText(getApplicationContext(),
               //         "Клац",
                //        Toast.LENGTH_SHORT);
               // toast1.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // у атрибута пункта меню Settings установлено значение android:onClick="onSettingsMenuClick"
    public void onSettingsMenuClick(MenuItem item) {
       // TextView infoTextView = (TextView) findViewById(R.id.textView);
        //infoTextView.setText("Вы выбрали пункт SettingsХуетинс, лучше бы сьели кота");

        Toast toast = Toast.makeText(getApplicationContext(),
                "Пора уничтожить весь выводок котов!", Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

   /* public void showToast(View view) {
        //создаем и отображаем текстовое уведомление
        Toast toast = Toast.makeText(getApplicationContext(),
                "Пора покормить кота!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }*/

    public void showToast(View view) {
        Toast toast3 = Toast.makeText(getApplicationContext(),
                R.string.catfood, Toast.LENGTH_LONG);
        toast3.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
        LinearLayout toastContainer = (LinearLayout) toast3.getView();
        ImageView catImageView = new ImageView(getApplicationContext());
        catImageView.setImageResource(catroll);
        toastContainer.addView(catImageView, 0);
        toast3.show();
    }

//копка вперед
    public void menuClick(MenuItem item){
        Intent scr2 = new Intent(this, MainTestActivity2.class);
        startActivity(scr2);

    }
}
