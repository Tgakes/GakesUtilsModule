package com.gakes.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyBoardUtils {
    /**
     * 如果输入法在窗口上已经显示，则隐藏，反之则显示
     */
    public static void show(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制显示键盘
     *
     * @param view
     */
    public static void forceShow(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 强制显示键盘
     *
     * @param view
     */
    public static void forceHide(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 隐藏系统默认的输入法
     *
     * @param activity
     */
    public static void hideSystemKDefaulteyBoard(Activity activity) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断键盘状态
     *
     * @return 若返回true，则表示输入法打开
     */
    public static boolean isShowForkeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

    public static boolean isSoftKeyBoardVisible(Activity activity){
        final View decorView = activity.getWindow().getDecorView();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        //计算出可见屏幕的高度
        int displayHeight = rect.bottom - rect.top;
        //获得屏幕整体的高度
        int height = decorView.getHeight();
        //获得键盘高度
//        int keyboardHeight = height - displayHeight;
        boolean visible = (double) displayHeight / height < 0.8;

        return  visible;
    }
}
