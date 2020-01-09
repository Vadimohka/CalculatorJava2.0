package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements ActivityInterface {

    LinearLayout s_frag;
    TextView res;
    String str = "";
    boolean canFloat;
    final static String key = "viewString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = findViewById(R.id.resultView);
        canFloat = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(key, str);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        str = savedInstanceState.getString(key);
        res = findViewById(R.id.resultView);
        res.setText(str);
    }

    public void ChangeMode(View view){
        if (s_frag.getVisibility() == View.INVISIBLE)
            s_frag.setVisibility(View.VISIBLE);
        else
            s_frag.setVisibility(View.INVISIBLE);
    }

    public void InvalidExpression()
    {
        Toast.makeText(this, "Invalid expression.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean getcanFloat() {
        return canFloat;
    }

    @Override
    public String getStr()
    {
        return str;
    }

    @Override
    public TextView getRes()
    {
        return res;
    }

    @Override
    public void setcanFloat(boolean a)
    {
        canFloat = a;
    }

    @Override
    public void setStr(String a)
    {
        str = a;
    }
}
