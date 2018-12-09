package team4.com.wig_aware;

import android.graphics.drawable.Drawable;

public class ListViewRecomItem {

    private Drawable iconDrawable;
    private int id;
    private int dur;
    private String visited ;
    private String date;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setDur(int _dur){
        dur = _dur;
    }
    public void setId(int _id){
        id = id;
    }
    public void setVisited(String text) {
        visited = text ;
    }
    public void setDate(String text){
        date = text;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public int getDur(){
        return this.dur;
    }
    public int getId(){
        return this.id;
    }
    public String getVisited() {
        return this.visited ;
    }
    public String getDate() {
        return this.date ;
    }

}
