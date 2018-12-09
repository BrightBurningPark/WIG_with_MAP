package team4.com.wig_aware;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static team4.com.wig_aware.NowActivity.dbVersion;

public class RecomActivity extends AppCompatActivity implements ListViewRecomAdapter.ListRecomClickListener {

    public String user_name;

    FragmentTabHost tabHost;
    private static final String TAB1 = "설문조사로 추천";
    private static final String TAB2 = "여행기록으로 추천";


    //하나의 프래그먼트 뷰 양식(XML파일)을 공유합니다.
    //XML을 공유하기 때문에 기본적인 레이아웃은 같고, 속의 내용을 Fragment클래스를 통해 서로 다르게 생성해줍니다.
    //리스트도 각 클래스 내에서 각자 따로 만들어줘야 합니다.
    //기존의 작업, 리스트 어댑터를 사용해서 리스트뷰에 커스텀리스트아이템을 연결하던 작업을 Fragment의 onCreate 생성자 안에서 하면 되는 것 같습니다.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent_now = getIntent();
        user_name = intent_now.getStringExtra("username");

        setContentView(R.layout.activity_recom);

        tabHost = (FragmentTabHost)findViewById(R.id.tabHost1);

        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        tabHost.addTab(tabHost.newTabSpec(TAB1).setIndicator("TAB1"), FragmentBySurvey.class, null);
        tabHost.addTab(tabHost.newTabSpec(TAB2).setIndicator("TAB2"), FragmentByVisit.class, null);

        // 코드 계속 ...

        //객체 파기 문제로 저쪽에서 리스트 전부 만듬.
        /*
        if(!buildListView()){
            finish();
        }
        */



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


/*
    public boolean buildListView() {

        ArrayList<ListViewRecomItem> toplist = new ArrayList<ListViewRecomItem>() ;
        ArrayList<ListViewRecomItem> bottomlist = new ArrayList<ListViewRecomItem>() ;

        // 아이템 생성. 을 위한 데이터베이스 오픈
        final DBHelper dbHelper = new DBHelper(this, "WIG.db", null, dbVersion);


        //탑 바텀 리스트 만들기
        toplist = dbHelper.getRecomList(user_name, 0);
        bottomlist = dbHelper.getRecomList(user_name, 1);

        //Toast msg =Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT);
        //msg.show();


        //리스트뷰 만들기
        ListView toplistView;
        ListView bottomlistView;
        ListViewRecomAdapter topadapter;
        ListViewRecomAdapter bottomadapter;

        topadapter = new ListViewRecomAdapter(this, R.layout.listview_recom_item, toplist, this) ;
        bottomadapter = new ListViewRecomAdapter(this, R.layout.listview_recom_item, bottomlist, this) ;


        // 리스트뷰 참조 및 Adapter 달기
        toplistView = (ListView) findViewById(R.id.recom_by_survey);
        toplistView.setAdapter(topadapter);

        toplistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //아이템 클릭 이벤트
            }
        });

        //리스트뷰 아래거
        bottomlistView = (ListView) findViewById(R.id.recom_by_visited);
        bottomlistView.setAdapter(bottomadapter);

        bottomlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //아이템 클릭 이벤트
            }
        });

        return true ;
    }
    */

    public String getUser_name(){
        return this.user_name;
    }
}
