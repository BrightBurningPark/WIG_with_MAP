package team4.com.wig_aware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    String user_name;

    Button[] mButton = new Button[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mButton[0] = (Button) findViewById(R.id.BtnModify);
        mButton[1] = (Button) findViewById(R.id.BtnRetry);

        Intent intent_log = getIntent();
        user_name = intent_log.getStringExtra("username");

        TextView id_textview = (TextView) findViewById(R.id.user_id);
        id_textview.setText(user_name);
    }

    public void onClickSetting(View view){

        Button newButton = (Button) view;

        if(newButton == mButton[0]){
            Intent intent = new Intent(this, UserInfoActivity.class);
            intent.putExtra("username", user_name);
            startActivity(intent);
        }
        if(newButton == mButton[1]){
            Intent intent = new Intent(this, SurveyActivity.class);
            intent.putExtra("username", user_name);
            startActivity(intent);
        }
    }
}