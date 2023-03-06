package abc.def.currencylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_add;
    ListView ls;
//    List<String> simpleAdapter;

    Add_Dialog add_dialog;

    DBManager dbManager;
    Cursor fetch;

    final String [] fromDatabase = new String[] {
            DBHelper._ID,
            DBHelper._NAME,
            DBHelper._CURRENCY
    };

    SimpleCursorAdapter adapter;

    final int [] to = new int[] {
            R.id.no,
            R.id.txt1,
            R.id.txt2

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ls= (ListView) findViewById(R.id.LV_Country);

        add_dialog= new Add_Dialog(this);

        dbManager = new DBManager(this);

        dbManager.Open();

        fab_add = (FloatingActionButton) findViewById(R.id.FAB_add_Country);

        ls = findViewById(R.id.LV_Country);

        registerForContextMenu(ls);

        fab_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"FAB Clicked",Snackbar.LENGTH_LONG).show();

//                simpleAdapter = new ArrayList<>();
//                simpleAdapter.add("India");
//                simpleAdapter.add("Germany");
//                simpleAdapter.add("Thailand");

                add_dialog.show();

//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,simpleAdapter);
//                ls.setAdapter(adapter);




            }
        });

        FetchDatabase();
    }

    private void FetchDatabase() {


        Cursor fetch = dbManager.fetch();
        dbManager.Close();
//
//        adapter.notifyDataSetChanged();

        adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.customlist,fetch,fromDatabase,to);
        ls.setAdapter(adapter);



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.context_menu_delete:
                Toast.makeText(getApplicationContext(), "Menu Delete Clicked", Toast.LENGTH_SHORT).show();

                break;
            case R.id.context_menu_edit:
                Toast.makeText(getApplicationContext(), "Menu Edit Clicked", Toast.LENGTH_SHORT).show();

                TextView tv_id = (TextView) menuInfo.targetView.findViewById(R.id.no);

                TextView tv_country = (TextView) menuInfo.targetView.findViewById(R.id.txt1);

                TextView tv_currency = (TextView) menuInfo.targetView.findViewById(R.id.txt2);


                String id = tv_id.getText().toString();
//                int idd = Integer.parseInt(id);

                String country = tv_country.getText().toString();
                String currency = tv_currency.getText().toString();

                Intent modifyIntent = new Intent(MainActivity.this,Update.class);

//                modifyIntent.putExtra("id",id);

                Bundle bundle = new Bundle();

                bundle.putString("id",id);
                bundle.putString("country",country);
                bundle.putString("currency",currency);

                modifyIntent.putExtra("bundle",bundle);

                startActivity(modifyIntent);
                break;
        }
        return super.onContextItemSelected(item);
    }

    //menu creation code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.options_menu_add)
        {
            Toast.makeText(getApplicationContext(), "Menu Item Add Clicked", Toast.LENGTH_SHORT).show();

            add_dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }


}