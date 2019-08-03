package cn.stu.ruiz.myapplication.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.stu.ruiz.myapplication.R;

public class TranslateAnimActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    View line1;

    LinearLayout line_one, line_two;
    TextView text_one, text_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_anim);

        initView();
    }

    private void initView() {
        line_one = findViewById(R.id.line_one);
        line_two = findViewById(R.id.line_two);

        text_one = findViewById(R.id.line_1text);
        text_two = findViewById(R.id.line_2text);

    }

    public void onClick(View view) {
        AnimationSet animatonSet1 = new AnimationSet(false);
        TranslateAnimation line_1translate = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0.5f);
        line_1translate.setDuration(1000);
        ScaleAnimation line_1sacle = new ScaleAnimation(1f,0.6f,1f,0.9f);
        line_1sacle.setDuration(1000);
        animatonSet1.addAnimation(line_1translate);
        animatonSet1.addAnimation(line_1sacle);
        animatonSet1.setAnimationListener(new Animation.AnimationListener() {
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

        AnimationSet animationSet2 = new AnimationSet(false);
        TranslateAnimation line_2translate = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_PARENT, -0.5f);
        line_2translate.setDuration(1000);
        animationSet2.addAnimation(line_2translate);
        animationSet2.addAnimation(line_1sacle);

        line_one.startAnimation(animatonSet1);
        line_two.startAnimation(animationSet2);
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
