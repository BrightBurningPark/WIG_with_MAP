package team4.com.wig_aware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewRecAdapter extends BaseAdapter {
    public ArrayList<ListViewRecItem> listViewRecItemList = new ArrayList<ListViewRecItem>() ;

    //생성자
    public ListViewRecAdapter(){
        //TODO: 아무것도 안해도 됨...
    }

    @Override
    public int getCount() {
        return listViewRecItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewRecItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_recom_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.recom_image) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.recom_name) ;
        //TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewRecItem listViewRecItem = listViewRecItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextView.setText(listViewRecItem.getTitle());

        return convertView;
    }

    public void addItem(String title) {
        ListViewRecItem item = new ListViewRecItem();

        item.setTitle(title);

        listViewRecItemList.add(item);
    }

}
