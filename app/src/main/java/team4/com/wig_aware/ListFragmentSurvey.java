package team4.com.wig_aware;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragmentSurvey extends ListFragment {
    ListViewRecAdapter adapter ;

    public ListFragmentSurvey(){
        this.adapter = new ListViewRecAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Adapter 생성 및 Adapter 지정.
        setListAdapter(adapter) ;

        return inflater.inflate(R.layout.fragment_layout1, container, false);

    }

    public void addItem(String title) {
        adapter.addItem(title) ;
    }


}
