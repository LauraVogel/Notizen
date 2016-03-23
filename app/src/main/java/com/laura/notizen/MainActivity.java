package com.laura.notizen;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity  implements View.OnClickListener{
    private TextView result;
    private Button  dbanzahl ;
    private EditText input;
    private Paging page;

    MySQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.openOptionsMenu();
        page = new Paging(MainActivity.this);

       // output = (TextView)findViewById(R.id.ausgabe_tV);
        //drueck = (Button) findViewById(R.id.drueck_btn);
        input = (EditText) findViewById(R.id.eingabe_eT);

        //drueck.setOnClickListener(this);

        dbanzahl = (Button)findViewById(R.id.btn_dbanzahl);
        dbanzahl.setOnClickListener(this);

        result = (TextView)findViewById(R.id.tV_result);

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
        switch (item.getItemId())
        {
           // case R.id.seite2: toPage2();
            default: startActivity(page.decidePage(item.getItemId()));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {

            case R.id.btn_dbanzahl: dbgetAmount();
                break;
            default:
                break;
        }
    }

   /* public void toPage2()
    {
        Intent intent = new Intent(MainActivity.this, JsonFile.class);
        intent.putExtra("ersterInten", input.getText().toString());
        startActivity(intent);
    }*/

    public void dbgetAmount()
    {
        int number = helper.getNumber();
        result.setText(String.valueOf(number));
    }
}
