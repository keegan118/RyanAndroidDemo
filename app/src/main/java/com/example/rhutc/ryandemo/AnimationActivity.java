package com.example.rhutc.ryandemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends BaseActivity {

    Animation alphaAnimation;
    Animation rotateAnimation;
    Animation scaleAnimation;
    Animation setAnimation;
    Animation transposeAnimation;

    @BindView(R.id.anm_textView)
    TextView textView;

    @OnClick(R.id.anm_alpha)
    public void alpha(){
        textView.startAnimation(alphaAnimation);
    }

    @OnClick(R.id.anm_scale)
    public void scale(){
        textView.startAnimation(scaleAnimation);
    }

    @OnClick(R.id.anm_rotation)
    public void rotation(){
        textView.startAnimation(rotateAnimation);
    }

    @OnClick(R.id.anm_transpose)
    public void transpose(){
        textView.startAnimation(transposeAnimation);
    }

    @OnClick(R.id.anm_set)
    public void set(){
        textView.startAnimation(setAnimation);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        initialAnimation();
    }

    private void initialAnimation() {
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        setAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
        transposeAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_trans);
    }
}
