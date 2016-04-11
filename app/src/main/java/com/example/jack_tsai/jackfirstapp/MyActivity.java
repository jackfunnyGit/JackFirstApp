package com.example.jack_tsai.jackfirstapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

public class MyActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.asus.jackfirstapp.MESSAGE";
    private String[] Number_ID;
    private String[] Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getContactsName();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Number_ID);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        autoComplete.setAdapter(adapter);

        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Name);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public String[] getContactsName() {
        //取得內容解析器
        ContentResolver contentResolver = this.getContentResolver();
        //設定你要從電話簿取出的欄位
        String[] projection = new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME};
        //取得所有聯絡人
        Cursor cursor = contentResolver.query(Contacts.CONTENT_URI, projection, null, null, null);
        Number_ID = new String[cursor.getCount()];
        Name=new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            //移到指定位置
            cursor.moveToPosition(i);
            //取得第一個欄位
            Number_ID[i] = cursor.getString(0);
            Name[i]=cursor.getString(1);

        }
        return Number_ID;
    }
}
