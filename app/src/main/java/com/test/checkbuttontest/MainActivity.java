package com.test.checkbuttontest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    //定义一个图片数组
    int[] images = new int[]{
            R.drawable.lijiang,
            R.drawable.qiao,
            R.drawable.shuangta,
            R.drawable.shui,
            R.drawable.xiangbi,
    };

    //定义默认显示图片
    int currentImg = 2;
    //定义初始图片透明度
    private int alpha = 255;
    //定义按钮成员变量
    Button plus;
    Button minus;
    Button next;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        next = (Button) findViewById(R.id.next);

        image = (ImageView) findViewById(R.id.image);

        //定义查看下一张图片的监听器
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //控制ImageView展示下一张图片
                image.setImageResource(
                        images[++currentImg % images.length]);
            }
        });

        //定义改变图片透明度的方法
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == plus) {
                    alpha += 20;
                }
                if (v == minus) {
                    alpha -= 20;
                }
                if (alpha >= 255) {
                    alpha = 255;
                }
                if (alpha <= 0) {
                    alpha = 0;
                }
                //改变图片的透明度
                image.setImageAlpha(alpha);

            }
        };
        //为两个按钮添加监听器
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();

                //获取第一个图片显示框中的位图
                Bitmap bitmap = bitmapDrawable.getBitmap();
                System.out.println(bitmap.getWidth());
                System.out.println(image.getWidth());

                //bitmap图片实际大小与第一个ImageView的缩放比例
                double scale = 1.0 * bitmap.getHeight() / image.getHeight();

                //获取需要显示的图片的开始点
                int x = (int) (event.getX() * scale);
                int y = (int) (event.getY() * scale);
                if (x + 120 > bitmap.getWidth()) {
                    x = bitmap.getWidth() - 120;
                }
                if (y + 120 > bitmap.getWidth()) {
                    y = bitmap.getHeight() - 120;
                }
                return false;
            }

        });

    }

}
