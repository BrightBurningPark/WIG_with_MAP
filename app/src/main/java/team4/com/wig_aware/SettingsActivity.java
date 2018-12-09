package team4.com.wig_aware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Button[] mButton = new Button[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mButton[0] = (Button) findViewById(R.id.BtnModify);
        mButton[1] = (Button) findViewById(R.id.BtnRetry);

        Intent intent = getIntent();
        String pw = intent.getStringExtra("입력한 pw");
        TextView pwtextview = (TextView) findViewById(R.id.password);
        pwtextview.setText(pw);
    }

    public void onClick(View view){

        Button newButton = (Button) view;

        if(newButton == mButton[0]){
            Intent intent = new Intent(this, UserinfoActivity.class);
            startActivity(intent);
            finish();
        }
        if(newButton == mButton[1]){
            Intent intent = new Intent(this, SurveyActivity.class);
            startActivity(intent);
        }

    }
}