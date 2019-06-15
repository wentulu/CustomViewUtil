package cn.stu.ruiz.myapplication;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import cn.stu.ruiz.myapplication.view.music.PlayMusicControllView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    MediaPlayer mMediaPlayer;

    private PlayMusicControllView play_music_control_view,
            play_music_control_view_1,
            play_music_control_view_2,
            play_music_control_view_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMediaPlayer = new MediaPlayer();

        play_music_control_view = findViewById(R.id.play_music_control_view);
        play_music_control_view_1 = findViewById(R.id.play_music_control_view_1);

        play_music_control_view_2 = findViewById(R.id.play_music_control_view_2);
        play_music_control_view_3 = findViewById(R.id.play_music_control_view_3);


        play_music_control_view.setMediaPlayer(mMediaPlayer);
        play_music_control_view_1.setMediaPlayer(new MediaPlayer());
        play_music_control_view_2.setMediaPlayer(new MediaPlayer());
        play_music_control_view_3.setMediaPlayer(new MediaPlayer());
//
//        mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//
//            }
//        });
//        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                Log.e(TAG, "onPrepared: ");
////                mp.start();
//            }
//        });

    }


    private void initMediaPlayer() {
        try {
//            mMediaPlayer.setDataSource(this,Uri.parse("http://192.168.31.26:8080/html/123.mp3"));
//            mMediaPlayer.prepare();
//            mMediaPlayer = MediaPlayer.create(this,Uri.parse("http://192.168.31.26:8080/html/abc.mp3"));
            play_music_control_view.setPlayUrl("http://192.168.31.26:8080/html/abc.mp3");
            play_music_control_view_1.setPlayUrl("http://192.168.31.26:8080/html/abc.mp3");
            play_music_control_view_2.setPlayUrl("http://192.168.31.26:8080/html/abc.mp3");
            play_music_control_view_3.setPlayUrl("http://192.168.31.26:8080/html/abc.mp3");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: IOException");
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        initMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}
