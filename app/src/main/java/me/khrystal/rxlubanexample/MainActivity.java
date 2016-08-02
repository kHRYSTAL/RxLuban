package me.khrystal.rxlubanexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;
import me.khrystal.rxluban.HandleCallback;
import me.khrystal.rxluban.RxLuban;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "RxLubanExample";
    private TextView fileSize;
    private TextView imageSize;
    private TextView thumbFileSize;
    private TextView thumbImageSize;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileSize = (TextView) findViewById(R.id.file_size);
        imageSize = (TextView) findViewById(R.id.image_size);
        thumbFileSize = (TextView) findViewById(R.id.thumb_file_size);
        thumbImageSize = (TextView) findViewById(R.id.thumb_image_size);
        image = (ImageView) findViewById(R.id.image);

        Button button = (Button) findViewById(R.id.open_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(MainActivity.this, PhotoPicker.REQUEST_CODE);

            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);

                File imgFile = new File(photos.get(0));
                fileSize.setText(imgFile.length() / 1024 + "k");
                imageSize.setText(RxLuban.getInstance(this).getImageSize(imgFile.getPath())[0] + " * " + RxLuban.getInstance(this).getImageSize(imgFile.getPath())[1]);

                RxLuban.getInstance(this)
                        .load(new File(photos.get(0)))
                        .putGear(RxLuban.THIRD_GEAR)
                        .launch(new HandleCallback<File>() {
                            @Override
                            public void beforeHandle() {
                                Log.d(TAG, "beforeHandle");
                            }

                            @Override
                            public void handleError(String msg) {
                                Log.d(TAG, "handleError" + msg);
                            }

                            @Override
                            public void handleComplete() {
                                Log.d(TAG, "handleComplete");
                            }

                            @Override
                            public void handleSuccess(File data) {
                                Glide.with(MainActivity.this).load(data).into(image);

                                thumbFileSize.setText(data.length() / 1024 + "k");
                                thumbImageSize.setText(RxLuban.getInstance(getApplicationContext())
                                        .getImageSize(data.getPath())[0] + " * "
                                        + RxLuban.getInstance(getApplicationContext())
                                        .getImageSize(data.getPath())[1]);
                            }
                        });
            }
        }
    }
}
