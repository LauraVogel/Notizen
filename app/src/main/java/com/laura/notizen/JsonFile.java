package com.laura.notizen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonFile extends ActionBarActivity implements View.OnClickListener {
    private TextView output;
    private Button toast, jsonfile;
    private Paging page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_file);

        page = new Paging(JsonFile.this);
        output = (TextView)findViewById(R.id.output_tV);

        Intent intent2 = getIntent();
        output.setText(intent2.getStringExtra("ersterInten"));

        toast = (Button) findViewById(R.id.btn_Toast);
        toast.setOnClickListener(this);

        jsonfile = (Button)findViewById(R.id.btn_jsonFile);
        jsonfile.setOnClickListener(this);

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
            case R.id.btn_Toast: showToast();
                break;
            case R.id.btn_jsonFile: createJSON();
                break;
            default:
                break;
        }
    }

    public void showToast()
    {
        Toast.makeText(this, "Button wurde gedrückt", Toast.LENGTH_LONG).show(); //was genau besagt denn der content bzw die verschiedenen möglichkeiten?
        output.setText("geklappt");
    }

    public void createJSON() {
        JSONArray jarray = new JSONArray();


        MySQLiteHelper helper = new MySQLiteHelper(this);
        Note[] notizen = helper.getNotizen();

        for (Note n : notizen) {
            JSONObject jo = new JSONObject();
            //String hilf = n.getTitle();
            try {
                jo.put("Titel", n.getTitle());
                jo.put("Content", n.getContent());
                ///jarray.add(jo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jarray.put(jo);

        }
    }
}
