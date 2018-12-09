package team4.com.wig_aware;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {
    String user_name;
    public static int dbVersion = 6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        Intent intent_log = getIntent();
        user_name = intent_log.getStringExtra("username");
    }
    public void onClickNewPW(View view){

        EditText editPW = (EditText) findViewById(R.id.editpw);
        String pw = editPW.getText().toString();

        // 입력한 password를 userDB에 update
        final DBHelper dbHelper = new DBHelper(this, "WIG.db", null, dbVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE USER SET password = '" + pw + "' WHERE id = '" + user_name + "' ;");
        db.close();

        Toast.makeText(this, "비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show();

        finish();
    }
}
