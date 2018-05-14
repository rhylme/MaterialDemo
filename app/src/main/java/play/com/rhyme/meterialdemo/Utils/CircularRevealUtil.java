package play.com.rhyme.meterialdemo.Utils;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/11.
 * 描述: [].
 */
public class CircularRevealUtil {

    //基本view揭露
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void start(View lv){
        int cr=lv.getWidth()/2>lv.getHeight()/2?lv.getWidth()/2:lv.getHeight()/2;
        //参数介绍
        //ViewAnimationUtils.createCircularReveal(目标view，中心点X，中心点Y,开始半径，结束半径)
        Animator animator= ViewAnimationUtils.createCircularReveal(lv,lv.getWidth()/2,lv.getHeight()/2,0,cr);
        animator.setDuration(1000);
        animator.start();
    }
    //获取屏幕高
    private static int getHeight(Context context){
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * @param touchView 点击的view
     * @param tagetView 目标的view
     * @param isnormal 是为扩展，否为收缩
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Animator formatAnimator(View touchView, View tagetView,boolean isnormal){
        //目标view
        int tagetCenterX=tagetView.getWidth()/2;
        int tagetCenterY=tagetView.getHeight()/2;
        int toolbarsise=getHeight(tagetView.getContext())-tagetView.getHeight();

        //点击的view
        int touchCenterX= (int) (touchView.getX()+(touchView.getWidth()/2));
        int touchCentetY= (int) (touchView.getY()+touchView.getHeight())-toolbarsise;

        int destanceX=tagetCenterX-touchCenterX;//距离终点的距离
        int destanceY=tagetCenterY-touchCentetY;//距离终点的距离

        int cr=0;
        int x=0;
        int y=0;
        if (destanceX>0&&destanceY>0){//位于左上
            y=tagetView.getHeight()-destanceY;
            x=tagetView.getWidth()-destanceX;

        }else if (destanceX<0&&destanceY<0){//位于右下
            y=destanceY;
            x=destanceX;

        }else if (destanceX>0&&destanceY<0){//位于左下
            x=tagetView.getWidth()-destanceX;
            y=destanceY;

        }else if (destanceX<0&&destanceY>0){//位于右上
            x=destanceX;
            y=tagetView.getHeight()-destanceY;

        }
        cr= (int) Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        Animator animator=ViewAnimationUtils.createCircularReveal(tagetView,touchCenterX,touchCentetY,isnormal?0:cr,isnormal?cr:0);
        animator.setDuration(1000);

        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        return animator;
    }
}
