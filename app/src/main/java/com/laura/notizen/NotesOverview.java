package com.laura.notizen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class NotesOverview extends ActionBarActivity implements View.OnClickListener {
    private ListView liste;
    private Adapter adapter;
    private Note[] allNotes;
    private Paging page;

    //private TextView note;

    MySQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_overview);

        page = new Paging(NotesOverview.this);

        helper = new MySQLiteHelper(this);

        liste = (ListView)findViewById(R.id.lV_dbInhalt);
        adapter = new Adapter(this);

        liste.setAdapter(adapter);
        adapter.clear();
        fillListView();
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = allNotes[position].getContent().toString();
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        startActivity(page.decidePage(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.lV_dbInhalt: showToast();
                break;
            default:
                break;
        }
    }

    public void showToast() {
        Toast.makeText(this, "Toast listview", Toast.LENGTH_SHORT).show();
    }

    public void fillListView()
    {
        allNotes = helper.getNotizen();

        for(Note n : allNotes)
        {
            adapter.add(n);
        }
        adapter.notifyDataSetChanged();

    }
}
