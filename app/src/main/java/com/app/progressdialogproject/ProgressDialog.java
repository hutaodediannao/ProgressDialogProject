package com.app.progressdialogproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2018/5/27.
 */

public class ProgressDialog extends Dialog {

    public static final int dilogWidth = 200;
    public static final int dilogHeight = 200;
    private CustomPregressBar customPregressBar;
    private FrameLayout frameLayout;

    public ProgressDialog(@NonNull Context context) {
        this(context, R.style.CuomDialogStyle);
    }

    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.frameLayout = new FrameLayout(context);
        this.customPregressBar = new CustomPregressBar(context);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(
                (int)context.getResources().getDimension(R.dimen._10dp),
                (int)context.getResources().getDimension(R.dimen._10dp),
                (int)context.getResources().getDimension(R.dimen._10dp),
                (int)context.getResources().getDimension(R.dimen._10dp)
        );
        this.frameLayout.addView(customPregressBar, layoutParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                (int) getContext().getResources().getDimension(R.dimen._200dp),
                (int) getContext().getResources().getDimension(R.dimen._200dp)
        );
        this.setContentView(frameLayout, layoutParams);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    public void setProgress(int progress) {
        this.customPregressBar.setProgress(progress);
    }
}
