package com.gakes.util;

import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommontUtils {

    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    /**
     * Emoji表情校验
     *
     * @param string
     * @return
     */
    public static boolean isEmoji(String string) {
        //过滤Emoji表情
        Pattern pattern = Pattern.compile("[^\\u0000-\\uFFFF]");

        Matcher matcher = pattern.matcher(string);
//        if (matcher.find()) {
//            return true;
//        }

        //过滤Emoji表情和颜文字
//        pattern = Pattern.compile("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]|[\\ud83e\\udd00-\\ud83e\\uddff]|[\\u2300-\\u23ff]|[\\u2500-\\u25ff]|[\\u2100-\\u21ff]|[\\u0000-\\u00ff]|[\\u2b00-\\u2bff]|[\\u2d06]|[\\u3030]");
//        matcher = pattern.matcher(string);

        return matcher.find();
    }


    public static CharSequence replaceNull(CharSequence content) {

        boolean isSpanned = content instanceof Spanned;
        if (isSpanned) {
            return TextUtils.replace(content, new String[]{" "}, new CharSequence[]{"\u00A0"});
        } else {
            return content.toString().replaceAll(" ", "\u00A0");
        }
    }
}
