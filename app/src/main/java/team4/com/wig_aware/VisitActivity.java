package team4.com.wig_aware;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//사용자가 여행기록 조회 하면 그거를 빼내와가지고 보여주는 액티비티
public class VisitActivity extends AppCompatActivity implements ListViewBtnAdapter.ListBtnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_visit);


        // 코드 계속 ...

        ListView listView;
        ListViewBtnAdapter adapter;
        ArrayList<ListViewBtnItem> items = new ArrayList<ListViewBtnItem>() ;

        adapter = new ListViewBtnAdapter(this, R.layout.listview_btn_item, items, this) ;

        // 리스트뷰 참조 및 Adapter 달기
        listView = (ListView) findViewById(R.id.visitList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //아이템 클릭 이벤트
            }
        });


    }

    //버튼 2 누르면 구동
    @Override
    public void onListBtnClick(int position) {
        //지도에서 확인시키는 버튼으로 만들 듯...
    }
}
