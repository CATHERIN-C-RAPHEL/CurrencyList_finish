package abc.def.currencylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Add_Dialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button add;
    String s1,s2;

    EditText cnty,cry;

    DBManager dbManager;

    public Add_Dialog(@NonNull Activity a) {
        super(a);

        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_dialog);

        cnty=findViewById(R.id.cntry);
        cry=findViewById(R.id.crncy);

        dbManager=new DBManager(c);


        add = (Button) findViewById(R.id.addb);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


s1 = String.valueOf(cnty.getText());
s2 = String.valueOf(cry.getText());
        dbManager.Open();
        dbManager.Insert(s1,s2);
        dbManager.Close();

        Intent intent = new Intent(c,MainActivity.class);
        c.startActivity(intent);

        dismiss();
    }
}