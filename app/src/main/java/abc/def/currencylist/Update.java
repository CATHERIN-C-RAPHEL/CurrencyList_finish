package abc.def.currencylist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Update extends AppCompatActivity implements View.OnClickListener {

    TextView id1;
    EditText t1,t2;
    Button b1,b2;

    DBManager dbManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setTitle("Update Record");

        id1 = findViewById(R.id.id1);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);



        Intent intent = getIntent();

//        Bundle extras = intent.getExtras();

        Bundle extras = intent.getBundleExtra("bundle");

        String id = extras.getString("id");
        String country = extras.getString("country");
        String currency = extras.getString("currency");



        id1.setText(id);
        t1.setText(country);
        t2.setText(currency);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        dbManager = new DBManager(this);
        dbManager.Open();



    }

    @Override
    public void onClick(View v) {

        Long _id = Long.valueOf(id1.getText().toString());

        String country = t1.getText().toString();
        String currency = t2.getText().toString();

        switch (v.getId())
        {
            case R.id.b1:

                dbManager.Update(_id,country,currency);
                dbManager.Close();

                Returnintent();

                break;

            case R.id.b2:

                dbManager.Delete(_id);
                dbManager.Close();

                Returnintent();

                break;
        }

    }

    private void Returnintent() {

        Intent intent = new Intent(Update.this,MainActivity.class);
        startActivity(intent);
    }


}