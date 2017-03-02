package com.li.library.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Li on 2017/3/2.
 * size
 */

public class SizeUtil {
    /**
     * calculate and set the listView's height
     *
     * @param listView the listView to calculate and set
     */
    public static void setListViewHeight(ListView listView) {
        int totalHeight = 0;
        int singleHeight;
        int divider = listView.getDividerHeight();
        ListAdapter adapter = listView.getAdapter();
        int count = adapter.getCount();
        if (count < 0) {
            return;
        }
        View view = adapter.getView(0, null, listView);
        view.measure(0, 0);
        singleHeight = view.getMeasuredHeight();
        totalHeight += divider * (count - 1) + singleHeight * count;
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        listView.setLayoutParams(params);
    }

    /**
     * calculate and set the gridView's height
     *
     * @param gridView the gridView to calculate and set
     * @param columns  the numColumns
     */
    public static void setGridViewHeight(GridView gridView, int columns) {
        ListAdapter adapter = gridView.getAdapter();
        int count = adapter.getCount();
        int totalHeight = 0;
        int singleHeight;
        count = count % columns == 0 ? (count / columns) : (count / columns + 1);
        View view = adapter.getView(0, null, gridView);
        view.measure(0, 0);
        singleHeight = view.getMeasuredHeight();
        totalHeight += singleHeight * count;
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }
}
