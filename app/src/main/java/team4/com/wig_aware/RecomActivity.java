package team4.com.wig_aware;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static team4.com.wig_aware.NowActivity.dbVersion;

public class RecomActivity extends AppCompatActivity implements View.OnClickListener {

    ListFragmentSurvey listFragmentSurvey;
    ListFragmentVisit listFragmentVisit;

    public String user_name;

    //ListFragmentSurvey customListFrgmt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent_now = getIntent();
        user_name = intent_now.getStringExtra("username");

        setContentView(R.layout.activity_recom);

        //데이터베이스 로드
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "WIG.db", null, dbVersion);

        // 코드 계속 ...

        findViewById(R.id.btn_frag1).setOnClickListener(this);
        findViewById(R.id.btn_frag2).setOnClickListener(this);
        //findViewById(R.id.btn_frag3).setOnClickListener(this);

        listFragmentSurvey = new ListFragmentSurvey();
        listFragmentVisit = new ListFragmentVisit();

        ArrayList<ListViewRecItem> list1 = new ArrayList<ListViewRecItem>();

        list1 = dbHelper.getRecommend(user_name, 0);
        for(int i=0; i<list1.size(); i++) {
            listFragmentSurvey.addItem(list1.get(i).getTitle());
        }


        ArrayList<ListViewRecItem> list2 = new ArrayList<ListViewRecItem>();

        list2 = dbHelper.getRecommend(user_name, 1);
        for(int i=0; i<list2.size(); i++) {
            listFragmentVisit.addItem(list2.get(i).getTitle());
        }



        //ArrayList<ListViewRecItem> list1 = new ArrayList<ListViewRecItem>();
        //ArrayList<ListViewRecItem> list2 = new ArrayList<ListViewRecItem>();


        /*
        list1 = dbHelper.getRecommend(user_name, 0);
        for(int i=0; i<list1.size(); i++) {
            listFragmentSurvey.addItem(list1.get(i).getTitle());
        }
        */

        /*
        list2 = dbHelper.getRecommend(user_name, 1);
        for(int i=0; i<list2.size(); i++) {
            listFragmentVisit.addItem(list2.get(i).getTitle());
        }
        */
        //listFragmentVisit.add

        this.onClick(findViewById(R.id.btn_frag1));

    }

    @Override
    public void onClick(View v) {
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "WIG.db", null, dbVersion);

        switch (v.getId()) {
            case R.id.btn_frag1:

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_, listFragmentSurvey)
                        .commit();
                break;
            case R.id.btn_frag2:

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_, listFragmentVisit)
                        .commit();
                break;

        }
    }

}
