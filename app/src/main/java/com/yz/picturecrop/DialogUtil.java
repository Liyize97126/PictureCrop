package com.yz.picturecrop;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @Desc:
 * @Author: YiZe
 */
public class DialogUtil {
    public static void showBottomDialog(Context context, final OnCameraEventListener onCameraEventListener, final OnPhotoAlbumEventListener onPhotoAlbumEventListener) {
        //获取View布局
        View view = LayoutInflater.from(context).inflate(R.layout.my_set_head_image, null);
        //建立Dialog对象（Context,自定义的属性）
        final Dialog dialog = new Dialog(context, R.style.DialogStyleFirst);
        //设置布局
        dialog.setContentView(view);
        //设置弹出后点击屏幕或物理返回键，dialog不消失
        //dialog.setCancelable(true);
        //设置弹出后点击屏幕，dialog不消失；点击物理返回键dialog消失
        dialog.setCanceledOnTouchOutside(false);

        //设置点击事件
        view.findViewById(R.id.my_mine_photo_linv_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(onCameraEventListener != null){
                    onCameraEventListener.onCameraEvent();
                }
            }
        });
        view.findViewById(R.id.my_mine_photo_linv_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(onPhotoAlbumEventListener != null){
                    onPhotoAlbumEventListener.onPhotoAlbumEvent();
                }
            }
        });
        view.findViewById(R.id.my_mine_photo_linv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //获取dialog的Windows对象
        Window window = dialog.getWindow();
        //设置显示位置
        window.setGravity(Gravity.BOTTOM);
        //设置屏幕边界距离
        /*getDecorView:这个方法是获取顶级视图
        注意点1：addView添加入的视图应该是默认在左上角，和group里面原有的视图无关
        注意点2:getDecorView既然是顶级视图，它包含整个屏幕，包括标题栏
        注意点3：根据实际测试发现，标题栏的左上角位置的坐标才是坐标原点位置*/
        window.getDecorView().setPadding(0, 0, 0, 0);
        //得到WindowManager.LayoutParams对象
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置宽高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //完成设置
        window.setAttributes(lp);
        //展示
        dialog.show();
    }

    //接口回调
    public interface OnCameraEventListener {
        void onCameraEvent();
    }

    public interface OnPhotoAlbumEventListener {
        void onPhotoAlbumEvent();
    }
}
