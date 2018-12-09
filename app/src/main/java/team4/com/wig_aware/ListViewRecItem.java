package team4.com.wig_aware;

public class ListViewRecItem {
    private String title;
    private int recommend_lev;

    public String getTitle(){
        return title;
    }
    public int getRecommend_lev(){
        return recommend_lev;
    }

    public void setTitle(String in_title){
        this.title = in_title;
    }
    public void setRecommend_lev(int rec_lev){
        this.recommend_lev = rec_lev;
    }

}
