package cn.stu.ruiz.myapplication;

import android.animation.AnimatorSet;
import android.graphics.Outline;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import cn.stu.ruiz.myapplication.utils.DisplayScreenUtil;
import cn.stu.ruiz.myapplication.utils.SizeUtil;
import cn.stu.ruiz.myapplication.view.music.PlayMusicControllView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    View line1;

    LinearLayout line_one, line_two;
    TextView text_one, text_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        line_one = findViewById(R.id.line_one);
        line_two = findViewById(R.id.line_two);

        text_one = findViewById(R.id.line_1text);
        text_two = findViewById(R.id.line_2text);

    }

    public void onClick(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        TranslateAnimation line_1translate = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0.5f);
        line_1translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if ("The first line.".equalsIgnoreCase(text_one.getText().toString().trim())) {
                    text_one.setText("The second line.");
                    text_two.setText("The first line.");
                }else {
                    text_one.setText("The first line.");
                    text_two.setText("The second line.");
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        TranslateAnimation line_2translate = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_PARENT, -0.5f);
        line_2translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        line_1translate.setDuration(1000);
        line_2translate.setDuration(1000);
        line_one.startAnimation(line_1translate);
        line_two.startAnimation(line_2translate);
    }


    /**
     * 故障不断额outline
     */
    private void outline() {
//        line1 = findViewById(R.id.line1);
        line1.setEnabled(true);
        line1.setClickable(true);
        line1.setClipToOutline(true);
        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                line1.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
//                            outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), 50);
//                            if (view.getMeasuredHeight() == 0 || view.getMeasuredWidth() == 0)
//                                return;
                        outline.setOval(0, 0, view.getMeasuredWidth(), view.getMeasuredWidth());//这个好有意思不等的话画不出来，似乎只能画圆
                    }
                });

            }
        });
    }


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public void onClick(View view) {
//        line1.setClipToOutline(true);
//        line1.setOutlineProvider(new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
////                    outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(),50);
//                if (view.getMeasuredHeight() == 0 || view.getMeasuredWidth() == 0)
//                    return;
//                outline.setOval(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//            }
//        });
//
//    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
