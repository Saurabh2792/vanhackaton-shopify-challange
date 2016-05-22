package shopify.app.shopifyme.presentation.view.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;

public class Fade {
    public static final int ZERO = 0;
    public static final int HALF_SECOND = 500;
    public static final int ONE_SECOND = 1000;
    public static final int ONE_AND_HALF_SECOND = 1500;

    public static void in(final View view, final int time, final int delay) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(time);

        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        animation.setStartOffset(delay);

        view.setAnimation(animation);
        view.setVisibility(View.VISIBLE);
    }

    public static void out(final View view, final int time, final int delay) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new DecelerateInterpolator());
        fadeOut.setDuration(time);

        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeOut);
        animation.setStartOffset(delay);

        view.setAnimation(animation);
        view.setVisibility(View.INVISIBLE);
    }
}