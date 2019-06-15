package cn.stu.ruiz.myapplication.view.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;

import cn.stu.ruiz.myapplication.R;
import cn.stu.ruiz.myapplication.utils.SizeUtil;
/***
 *
 * 9.0开始https，不能http
 *
 * */
public class PlayMusicControllView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "PlayMusicControllView";

    private ImageButton play_stop_btn;
    private TextView current_time, total_time;
    private SeekBar progress_seek_bar;
    private MediaPlayer mMediaPlayer;

    private String url;

    boolean startOnClick = false;

    private Handler mHandler = new Handler();

    public PlayMusicControllView(Context context) {
        this(context, null);
    }

    public PlayMusicControllView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayMusicControllView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        super.setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        int padding = SizeUtil.dip2Pixel(10, getContext());
        setPadding(padding, padding, padding, padding);
        setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.bg_music_control));
        LayoutInflater.from(getContext()).inflate(R.layout.view_play_music_controll, this, true);
        play_stop_btn = findViewById(R.id.play_stop_btn);
        play_stop_btn.setOnClickListener(this);
        current_time = findViewById(R.id.current_time);
        total_time = findViewById(R.id.total_time);
        progress_seek_bar = findViewById(R.id.progress_seek_bar);
        progress_seek_bar.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }


    @Override
    public void setOrientation(int orientation) {

    }


    private String caculateTime(int millSecond){
        String[] time = new String[2];
        int seconds = millSecond/1000;
        int minutes = seconds/60;
        int cuSeconds = seconds%60;
//        time[0] = cuSeconds;
//        time[1] = minutes;
        if (minutes<10){
            time[0] = "0"+minutes;
        }else {
            time[0] = minutes+"";
        }

        if (cuSeconds<10){
            time[1] = "0"+cuSeconds;
        }else {
            time[1] = cuSeconds+"";
        }
        return time[0]+":"+time[1];
    }

    public void setMediaPlayer(final MediaPlayer mediaPlayer) {
        mMediaPlayer = mediaPlayer;
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int millSeconds = mp.getDuration();
                total_time.setText(caculateTime(millSeconds));
            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int currentMillSeconds = mp.getCurrentPosition();
                mHandler.removeCallbacks(null);
            }
        });
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mMediaPlayer = null;
        mHandler.removeCallbacksAndMessages(null);
        mHandler=null;
    }

    public void setPlayUrl(String url) throws IllegalAccessException {
        this.url = url;
        startWithPlayUrl();
    }

    private boolean startWithPlayUrl() throws IllegalAccessException {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
            }
            try {
                mMediaPlayer.setDataSource(getContext(), Uri.parse(url));
                mMediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new IllegalAccessException("No MediaPlayer!!!");
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_stop_btn:
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    play_stop_btn.setImageResource(R.drawable.ic_play);
                    mMediaPlayer.pause();
                    mHandler.removeCallbacks(null);
                } else if (mMediaPlayer!=null && !mMediaPlayer.isPlaying()){
                    mMediaPlayer.start();
                    play_stop_btn.setImageResource(R.drawable.ic_pause);
                    mHandler.postDelayed(new CurrentMillesSecondRunnable(mMediaPlayer), 100);
                }
                break;
        }
    }


    class CurrentMillesSecondRunnable implements Runnable {

        private WeakReference<MediaPlayer> mMediaPlayerWeakReference;

        public CurrentMillesSecondRunnable(MediaPlayer mediaPlayer) {
            mMediaPlayerWeakReference = new WeakReference<>(mediaPlayer);
        }

        @Override
        public void run() {
            MediaPlayer mediaPlayer = mMediaPlayerWeakReference.get();
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {

                int currentMillSecond = mediaPlayer.getCurrentPosition();
                float totalMillSeconds =mediaPlayer.getDuration();
                progress_seek_bar.setProgress((int)((currentMillSecond / totalMillSeconds) * 1000));
                Log.e(TAG, "run: "+currentMillSecond+"/"+totalMillSeconds );
                current_time.setText(caculateTime(currentMillSecond));
                mHandler.postDelayed(new CurrentMillesSecondRunnable(mediaPlayer), 300);
            } else {

            }
        }
    }
}
