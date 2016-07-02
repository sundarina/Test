package me.mihael.sundarina.test;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String>{

    private SparseBooleanArray mSelectedItemsIds;
    private LayoutInflater inflater;
    private Context mContext;

    //Activity является подклассом Context, поэтому мы можем использовать ее – this.
    // Вкратце, Context – это объект, который предоставляет доступ к базовым функциям приложения?
    // таким как: доступ к ресурсам, к файловой системе, вызов Activiy и т.д.

    private List<String> list;

    public CustomAdapter (Context context, int resourceId, List<String> list) {
        super(context, resourceId, list);  //?????
        mSelectedItemsIds = new SparseBooleanArray();
        mContext = context;
        inflater = LayoutInflater.from(mContext); //static from() метод с класса LayoutInflater для получения данных LayoutInflater  из Context
    }

    private static class ViewHolder {
        TextView itemName;
        }

    /*
        View getView (int position,
                View convertView,
                ViewGroup parent)
    Получить View, который отображает данные в указанной позиции в наборе данных.
    Вы можете либо создать представление вручную или раздуть его из файла макета XML.
    Когда View надувается, родительский View (GridView, ListView ...) будут применяться параметры макета по умолчанию,
     если не используется раздувать (INT, android.view.ViewGroup, булево) указать вид корневой системы и,
     чтобы предотвратить присоединение к корню.

     getView()  отвечает за создание отдельных элементов списка. Он вызывается для каждого элемента списка, чтобы определить, какие данные нужно отобразить

     */

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.custom_tv, null);
            holder.itemName = (TextView) view.findViewById(R.id.custom_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

            // Tag – это некое Object-хранилище у каждого View, куда вы можете поместить нужные вам данные.
            // В нашем случае каждый выбор элемента помещает в  Tag номер позиции пункта списка.
            // Далее в обработчике нажатия можно этот номер позиции извлечь и определить, в каком пункте списка был нажат элемент.
        }
        holder.itemName.setText(list.get(position));
        return view;
    }

    @Override
    public void remove(String string) {
        list.remove(string);
        notifyDataSetChanged(); //Уведомляет  приложение, что основные данные были изменены и любые View отражающие набор данных должны обновить себя.
    }

    public void toggleSelection(int position) {                    // Переключение состояния элемента после каждого клика на него
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}

