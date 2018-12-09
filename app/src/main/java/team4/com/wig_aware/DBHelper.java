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
        //여행지 -> 주소(PRIMARY KEY), 위도, 경도, 국가, 지리특색, 인구밀도, 기온
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
        db.execSQL("CREATE TABLE IF NOT EXISTS USER (" +
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
                db.execSQL("DROP TABLE IF EXISTS USER");
        }
    }


    //관광지 찍어주기
    public void drawPlaceMarker(GoogleMap googleMap){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM PLACE", null);

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



    //이하는 원래 읽고 쓰고 하는 코드인데...
    // 그냥 버튼에다가 SQL쿼리 직접 할당하는게 훨씬 나아보여서 안함.

    public void insert_to_visited(String create_at, String item, int price) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        //db.execSQL("INSERT INTO PLACES VALUES(" +"'test'"+ ",'KOR'"+", 38, 120, "+"'MOUNTAIN'"+", 1, 25);");
        db.close();
    }

    /*
    public void update(String item, int price) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE MONEYBOOK SET price=" + price + " WHERE item='" + item + "';");
        db.close();
    }

    public void delete(String item) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM MONEYBOOK WHERE item='" + item + "';");
        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MONEYBOOK", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getInt(2)
                    + "원 "
                    + cursor.getString(3)
                    + "\n";
        }

        return result;
    }
    */
}

