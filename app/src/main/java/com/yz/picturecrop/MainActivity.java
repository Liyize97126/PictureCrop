package com.yz.picturecrop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yz.picturecrop.crop.Crop;
import com.yz.picturecrop.roundedimageview.RoundedImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView mBack;
    private TextView mModifyAvatar;
    private RoundedImageView mRImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        mBack = findViewById(R.id.back);
        mModifyAvatar = findViewById(R.id.modify_avatar);
        mRImage = findViewById(R.id.r_image);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mModifyAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showBottomDialog(MainActivity.this, new DialogUtil.OnCameraEventListener() {
                    @Override
                    public void onCameraEvent() {

                    }
                }, new DialogUtil.OnPhotoAlbumEventListener() {
                    @Override
                    public void onPhotoAlbumEvent() {
                        Intent intentFromGallery = new Intent(Intent.ACTION_PICK);//返回被选中项的URI
                        // 设置文件类型
                        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
                        //intentFromGallery.setType("image/*");    // 这个方法也能用    上面的这个方法也能用  暂时临时先放着吧
                        startActivityForResult(intentFromGallery, 300);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 300 && data != null) {
            Uri uri = data.getData();
            //进入裁剪页
            Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped" + System.currentTimeMillis()));
            Crop.of(uri, destination).asSquare().start(this);
            return;
        }
        if (requestCode == Crop.REQUEST_CROP && data != null) {
            Glide.with(this)
                    .load(Crop.getOutput(data).toString())
                    .into(mRImage);
        }
    }
}
