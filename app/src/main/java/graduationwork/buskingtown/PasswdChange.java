package graduationwork.buskingtown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PasswdChange extends AppCompatActivity {

    private TextView passwdok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwd_change);


        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { PasswdChange.super.onBackPressed(); }
        });

        passwdok = (TextView) findViewById(R.id.passwdOK);
        passwdok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { PasswdChange.super.onBackPressed(); }
        });
    }

    public void previousActivity(View v){
        onBackPressed();
    }

}
