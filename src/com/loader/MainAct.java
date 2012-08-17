package com.loader;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class MainAct extends Activity {

	ListView listView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.myListView);
        
        Helper helper = new Helper(this);
        helper.open();
        
        helper.insert("bharat", "poptani");
        helper.insert("mukesh", "poptani");
        helper.insert("kavita", "poptani");
        helper.insert("amit", "poptani");
        helper.insert("lalit", "poptani");
        
        Cursor c = helper.getAll();
        c.moveToFirst();
        
        MyAdapter adapter = new MyAdapter(this, c, false);
        listView.setAdapter(adapter);
    }
}