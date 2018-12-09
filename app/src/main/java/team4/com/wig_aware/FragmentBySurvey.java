package team4.com.wig_aware;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;
import static team4.com.wig_aware.NowActivity.dbVersion;


public class FragmentBySurvey extends Fragment implements ListViewRecomAdapter.ListRecomClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        if(!buildListView()){
            Toast msg =Toast.makeText(this.getContext(), "done", Toast.LENGTH_LONG);
            msg.show();
            return;
        }

    }

    public boolean buildListView() {


        ArrayList<ListViewRecomItem> toplist = new ArrayList<ListViewRecomItem>() ;
        String user_name = ((MyApplication)this.getActivity().getApplication()).getUser_id();

        //Toast msg =Toast.makeText(this.getContext(), user_name, Toast.LENGTH_LONG);
        //msg.show();
        //진입 확인됨

        // 아이템 생성. 을 위한 데이터베이스 오픈
        final DBHelper dbHelper = new DBHelper(this.getContext(), "WIG.db", null, dbVersion);


        //탑 바텀 리스트 만들기
        toplist = dbHelper.getRecomList(user_name, 0);

        //Toast msg =Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT);
        //msg.show();


        //리스트뷰 만들기
        ListView toplistView;
        ListViewRecomAdapter topadapter;

        topadapter = new ListViewRecomAdapter(this.getContext(), R.layout.listview_recom_item, toplist, this) ;

        // 리스트뷰 참조 및 Adapter 달기
        toplistView = (ListView) this.getActivity().findViewById(R.id.recom_list);
        toplistView.setAdapter(topadapter);

        toplistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //아이템 클릭 이벤트
            }
        });


        return true ;
    }

    @Override
    public void onListBtnClick(int position) {

    }
}
