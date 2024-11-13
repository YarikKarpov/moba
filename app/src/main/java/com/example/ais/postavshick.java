package com.example.ais;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class postavshick extends AppCompatActivity {
private DatabaseConnect dbConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_postavshick);
        dbConnect = new DatabaseConnect(this);

        List<String> dataList = getDataFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
    }

    private List<String> getDataFromDatabase() {
        List<String> dataList = new ArrayList<>();
        try {
            dbConnect.openDatabase();


            String query = "SELECT * FROM Поставщик";
            Cursor cursor = dbConnect.getReadableDatabase().rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range")
                    int columnIndex = cursor.getColumnIndex("Код,ФИО,Телефон,Почта");
                    if (columnIndex != -1) {
                        String data = cursor.getString(columnIndex);
                        dataList.add(data);
                    }
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
            Log.e("postavshick", "Ошибка при получении данных из базы данных", e);
        } finally {
            dbConnect.close();
        }
        return dataList;
    }
}