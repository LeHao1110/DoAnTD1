package com.example.tdav;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	Button btnThem;
	ListView lvTu;
	ArrayList<Tu> arrayTu;
	TuAdapter adapter;
	public static Database database;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnThem=(Button)findViewById(R.id.buttonThem);
        
        lvTu = (ListView)findViewById(R.id.listViewTu);
        arrayTu = new ArrayList<Tu>();
        
        adapter  = new TuAdapter(this, R.layout.dong_tu, arrayTu);
        lvTu.setAdapter(adapter);
        
       database = new Database(this, "TuDien.sqlite", null, 1);
        
        database.QueryData("CREATE TABLE IF NOT EXISTS Tu (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), MoTa VARCHAR(250),HinhAnh BLOB)");
        
        //get data
        Cursor cursor = database.GetData("SELECT * FROM Tu");
        while(cursor.moveToNext()){
        	arrayTu.add(new Tu(
        			cursor.getInt(0),
        			cursor.getString(1),
        			cursor.getString(2),
        			cursor.getBlob(3)
        			) {
			});
        			
        			
        	
        	adapter.notifyDataSetChanged();

        			
        			
        }
        btnThem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, ThemTuActivity.class));
			}
		});
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
