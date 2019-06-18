package cn.stu.ruiz.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.SeekBar;

import cn.stu.ruiz.myapplication.R;
import cn.stu.ruiz.myapplication.canvas.ColorOffsetImageView;

public class ColorMatrixActivity extends AppCompatActivity {

    private AppCompatSeekBar scale_seek_bar,
            saturation_seek_bar,
            rotate_seek_bar;

    private ColorOffsetImageView mColorOffsetImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);

        scale_seek_bar = findViewById(R.id.scale_seek_bar);
        saturation_seek_bar = findViewById(R.id.saturation_seek_bar);
        rotate_seek_bar = findViewById(R.id.rotate_seek_bar);

        scale_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = progress/100f;
                mColorOffsetImageView.setScale(scale);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        rotate_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mColorOffsetImageView.setRotateColor(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saturation_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float saturation = progress/100f;
                mColorOffsetImageView.setSaturation(saturation);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        mColorOffsetImageView = findViewById(R.id.img_view);

    }
}
