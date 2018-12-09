package team4.com.wig_aware;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserinfoActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
    }
    public void onClick(View view){

        EditText editPW = (EditText) findViewById(R.id.editpw);
        String pw = editPW.getText().toString();

        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("입력한 pw", pw);
        startActivity(intent);

        Toast.makeText(this, "비밀번호가 " +pw+ "로 변경되었습니다", Toast.LENGTH_SHORT).show();
        finish();
    }
}
