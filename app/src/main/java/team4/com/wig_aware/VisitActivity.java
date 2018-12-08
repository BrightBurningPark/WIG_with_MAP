package team4.com.wig_aware;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static team4.com.wig_aware.NowActivity.dbVersion;

//사용자가 여행기록 조회 하면 그거를 빼내와가지고 보여주는 액티비티
public class VisitActivity extends AppCompatActivity implements ListViewBtnAdapter.ListBtnClickListener {

    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_visit);

        Intent intent_now = getIntent();
        user_name = intent_now.getStringExtra("username");

        // 코드 계속 ...

        //객체 파기 문제로 저쪽에서 리스트 전부 만듬.
        if(!buildListView()){
            finish();
        }


    }

    //버튼 2 누르면 구동
    @Override
    public void onListBtnClick(int position) {
        //삭제버튼

        final DBHelper dbHelper = new DBHelper(this, "WIG.db", null, 5);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //여기 막힘
        //db.rawQuery("DELETE FROM '" + user_name + "' WHERE name="+  +" AND start="+  +";", null);

        Toast.makeText(this, Integer.toString(position+1) + " Item is selected..", Toast.LENGTH_SHORT).show() ;
    }



    public boolean buildListView() {

        ArrayList<ListViewBtnItem> list = new ArrayList<ListViewBtnItem>() ;


        // 아이템 생성.
        final DBHelper dbHelper = new DBHelper(this, "WIG.db", null, dbVersion);

        list = dbHelper.getVisitedHistory(user_name);

        //Toast msg =Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT);
        //msg.show();


        //리스트뷰 만들기

        ListView listView;
        ListViewBtnAdapter adapter;

        adapter = new ListViewBtnAdapter(this, R.layout.listview_btn_item, list, this) ;

        // 리스트뷰 참조 및 Adapter 달기
        listView = (ListView) findViewById(R.id.visitList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //아이템 클릭 이벤트
            }
        });

        return true ;
    }


}
