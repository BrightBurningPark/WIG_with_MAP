package team4.com.wig_aware;

import android.app.Application;

public class MyApplication extends Application {
    private String user_id;

    public String getUser_id(){
        return user_id;
    }

    public void setUser_id(String in_user_id){
        this.user_id = in_user_id;
    }

}
