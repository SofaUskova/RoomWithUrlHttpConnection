package com.example.workswithserver.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ShowAll extends ListActivity {
    String[] name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        name = arguments.getStringArray("array");

        ShowAll();
    }

    private void ShowAll() {
        try {
            setListAdapter(new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, name));
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
