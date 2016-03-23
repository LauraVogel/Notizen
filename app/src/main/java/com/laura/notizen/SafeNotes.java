package com.laura.notizen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SafeNotes extends ActionBarActivity implements View.OnClickListener {
    MySQLiteHelper helper;

    private Button safe;
    private EditText note;
    private EditText titel;
    private Paging page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_notes);

        page = new Paging(SafeNotes.this);

        safe = (Button) findViewById(R.id.btn_safe);
        safe.setOnClickListener(this);

        note = (EditText)findViewById(R.id.eT_dbNote);
        titel = (EditText)findViewById(R.id.eT_dbTitel);

        helper = new MySQLiteHelper(this);
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
            case R.id.btn_safe: eintragSpeichern();
                break;
            default:
                break;
        }
    }

    public void eintragSpeichern()
    {
        Note n = new Note();
        n.setTitle(titel.getText().toString());
        n.setContent(note.getText().toString());
        helper.setEntry(n);
        titel.setText("");
        note.setText("");
        Toast.makeText(this, "Eintrag gespeichert", Toast.LENGTH_SHORT).show();
    }
/*
    public void paging(Class a)
    {
        Intent intent = new Intent(SafeNotes.this,a);
        startActivity(intent);
    }
    */

}
