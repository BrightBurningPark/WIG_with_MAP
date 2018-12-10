package team4.com.wig_aware;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    String user_name;
    private static int dbVersion = 6;

    Button[] mButton = new Button[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mButton[0] = (Button) findViewById(R.id.BtnModify);
        mButton[1] = (Button) findViewById(R.id.BtnRetry);
        mButton[2] = (Button) findViewById(R.id.btnSetDaily);

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
        if(newButton == mButton[2]){
            final DBHelper dbHelper = new DBHelper(this, "WIG.db", null, dbVersion);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            double latitude = ((MyApplication) this.getApplication()).getCurrent_lat();
            double longitude = ((MyApplication) this.getApplication()).getCurrent_lon();

            db.execSQL("UPDATE USERS SET latitude = " + latitude + ", longitude = " + longitude + " WHERE id = '" + user_name + "' ;");
            db.close();
            Toast msg = Toast.makeText(this, "일상 활동지역이 변경되었습니다", Toast.LENGTH_LONG);
            msg.show();
        }
    }
}