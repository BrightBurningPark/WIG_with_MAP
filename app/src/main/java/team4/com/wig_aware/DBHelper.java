package team4.com.wig_aware;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {

        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        //db.execSQL("CREATE TABLE MONEYBOOK (_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, price INTEGER, create_at TEXT);");

        //상기 코드 참고해서 데이터베이스 튜플 설정.
        //여행지 -> 이름(primary key), 위도 경도, 국가, 지형특징, 국내국외(크라우드 이름 바꾸기 귀찮아서) thermo
        //칼럼 별 세부사항은 README 참고
        db.execSQL("CREATE TABLE IF NOT EXISTS PLACE (" +
                "name TEXT PRIMARY KEY, " +
                "lat REAL, " +
                "lon REAL, " +
                "nat TEXT, " +
                "geo TEXT, " +
                "crowd INTEGER, " +
                "thermo REAL " +
                ");");

        //사용자 테이블 USER -> id, password, 설문조사 결과 스트링(나중에 파싱해서 쓰면 됨)
        db.execSQL("CREATE TABLE IF NOT EXISTS USERS (" +
                "id TEXT PRIMARY KEY, " +
                "password TEXT, " +
                "survey TEXT " +
                ");");

    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            default :
                db.execSQL("DROP TABLE IF EXISTS PLACE");
                db.execSQL("DROP TABLE IF EXISTS USERS");
        }
    }


    //관광지 찍어주기
    public void drawPlaceMarker(GoogleMap googleMap){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM PLACES", null);

        while(cursor.moveToNext()){
            //마커 생성 & 옵션 설정.
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions
                    .position(new LatLng(cursor.getDouble(1), cursor.getDouble(2)))
                    .title(cursor.getString(0));

            //마커 지도에 추가
            googleMap.addMarker(markerOptions);
        }

        db.close();
    }


    //방문 기록 불러오기
    public ArrayList<ListViewBtnItem> getVisitedHistory(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<ListViewBtnItem> rtn = new ArrayList<ListViewBtnItem>();

        Cursor cursor = db.rawQuery("SELECT * FROM '" + id + "'", null);

        while(cursor.moveToNext()){
            ListViewBtnItem item = new ListViewBtnItem();

            item.setId(cursor.getInt(0));
            item.setVisited(cursor.getString(1));
            item.setDate(cursor.getString(2));
            item.setDur(cursor.getInt(3));

            rtn.add(item);

        }

        return rtn;

    }

    public ArrayList<ListViewRecItem> getRecommend(String id, int opFlag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<ListViewRecItem> list = new ArrayList<ListViewRecItem>();

        List<Integer> geo;
        List<Integer> crowd;
        List<Integer> thermo;

        //위리스트: 설문을 기반으로 추천
        if(opFlag == 0) {
            Cursor cursor = db.rawQuery("SELECT geo AS G, crowd AS C, thermo AS T FROM PLACES", null);

            int[] ans1 = new int[cursor.getCount()];
            int[] ans2 = new int[cursor.getCount()];
            int[] ans3 = new int[cursor.getCount()];
            int i = 0;
            while(cursor.moveToNext()){
                ans1[i] = cursor.getInt(0);
                ans2[i] = cursor.getInt(1);
                ans3[i] = cursor.getInt(2);
                i++;
            }
            geo = mode(ans1);
            crowd = mode(ans2);
            thermo = mode(ans3);


            cursor = db.rawQuery("SELECT name FROM PLACES WHERE geo="+geo.get(0)+" AND crowd="+crowd.get(0)+" AND thermo="+thermo.get(0)+";", null);

            while(cursor.moveToNext()){
                ListViewRecItem item = new ListViewRecItem();
                item.setTitle(cursor.getString(0));
                list.add(item);
            }

        }
        else if(opFlag == 1){
            Cursor cursor = db.rawQuery("SELECT geo AS G, crowd AS C, thermo AS T FROM PLACES", null);

            int[] ans1 = new int[cursor.getCount()];
            int[] ans2 = new int[cursor.getCount()];
            int[] ans3 = new int[cursor.getCount()];
            int i = 0;
            while(cursor.moveToNext()){
                ans1[i] = cursor.getInt(0);
                ans2[i] = cursor.getInt(1);
                ans3[i] = cursor.getInt(2);
                i++;
            }
            geo = mode(ans1);
            crowd = mode(ans2);
            thermo = mode(ans3);


            cursor = db.rawQuery("SELECT name FROM PLACES WHERE geo="+geo.get(0)+" AND crowd="+crowd.get(0)+" AND thermo="+thermo.get(0)+";", null);

            while(cursor.moveToNext()){
                ListViewRecItem item = new ListViewRecItem();
                item.setTitle(cursor.getString(0));
                list.add(item);
            }


        }

        //ListViewRecItem item = new ListViewRecItem();
        //item.setTitle("test");
        //list.add(item);
        //아래 리스트: 기록을 바탕으로 추천



        return list;

    }

    public static List<Integer> mode(final int[] numbers) {
        final List<Integer> modes = new ArrayList<Integer>();
        final Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();

        int max = -1;

        for (final int n : numbers) {
            int count = 0;

            if (countMap.containsKey(n)) {
                count = countMap.get(n) + 1;
            } else {
                count = 1;
            }

            countMap.put(n, count);

            if (count > max) {
                max = count;
            }
        }

        for (final Map.Entry<Integer, Integer> tuple : countMap.entrySet()) {
            if (tuple.getValue() == max) {
                modes.add(tuple.getKey());
            }
        }

        return modes;
    }

}

