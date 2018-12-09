package team4.com.wig_aware;

import android.app.Application;

public class MyApplication extends Application {
    private String user_id;
    private double current_lat;
    private double current_lon;
    private double daily_lat;
    private double daily_lon;

    public String getUser_id(){
        return user_id;
    }

    public void setUser_id(String in_user_id){
        this.user_id = in_user_id;
    }

    // 현재 위치 받기, 주기
    public void setCurrent_lat(double in_lat){
        this.current_lat = in_lat;
    }

    public void setCurrent_lon(double in_lon){
        this.current_lon = in_lon;
    }

    public double getCurrent_lat(){
        return current_lat;
    }

    public double getCurrent_lon(){
        return current_lon;
    }

    // 사용자 일상 활동좌표 받기, 주기
    public void setDaily_lat(double in_lat){
        this.daily_lat = in_lat;
    }

    public void setDaily_lon(double in_lon){
        this.daily_lon = in_lon;
    }

    public double getDaily_lat(){
        return daily_lat;
    }

    public double getDaily_lon(){
        return daily_lon;
    }

}
