package team4.com.wig_aware;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SurveyActivity extends AppCompatActivity {
    private static final String TAG = "Survey";

    //데이터베이스 버전
    public static final int dbVersion = 5;

    //현재 로그인중인 사용자명
    String user_name;

    @BindView(R.id.a_important)
    RadioGroup group_important;
    @BindView(R.id.a_like)
    RadioGroup group_like;
    @BindView(R.id.a_weather)
    RadioGroup group_weather;
    @BindView(R.id.buttonAnswer)
    Button btnConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent_log = getIntent();
        user_name = intent_log.getStringExtra("username");

        setContentView(R.layout.activity_survey);
        ButterKnife.bind(this);
    }

    public void onClick(View view) {
        String ret_answer = validate();

        if (ret_answer.equals("000")) {
            Toast.makeText(this, "설문에 응답해주세요", Toast.LENGTH_SHORT).show();
        } else {
            final DBHelper dbHelper = new DBHelper(this, "WIG.db", null, dbVersion);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("UPDATE USER SET survey = '" + ret_answer + "' WHERE id = '" + user_name + "' ;");
            db.close();
            Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // RadioGroup에서 check된 Button이 같은 Group 내에서 가지는 index를 찾는다.
    public String validate() {
        String answer;

        int id_important = group_important.getCheckedRadioButtonId();
        int id_like = group_like.getCheckedRadioButtonId();
        int id_weather = group_weather.getCheckedRadioButtonId();

        View view_important = group_important.findViewById(id_important);
        View view_like = group_like.findViewById(id_like);
        View view_weather = group_weather.findViewById(id_weather);

        int idx_important = group_important.indexOfChild(view_important);
        int idx_like = group_like.indexOfChild(view_like);
        int idx_weather = group_weather.indexOfChild(view_weather);

        // 미응답 항목이 있는지 확인
        if ((idx_important == -1) || (idx_like == -1) || (idx_weather == -1))
            answer = "000";
        else {
            String ret_important = Integer.toString(idx_important + 1);
            String ret_like = Integer.toString(idx_like + 1);
            String ret_weather = Integer.toString(idx_weather + 1);

            answer = (ret_important + ret_like + ret_weather);
            Log.d(TAG, answer);
        }
        return answer;
    }

}
