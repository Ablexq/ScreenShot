package com.example.mycustomview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mbtn;
    private TextView mtv;
    private RelativeLayout rv;
    private ImageView mIvResult;
    private ArrayList<View> arrayList = new ArrayList<>();
    private Button screenshotBtn1;
    private Button screenshotBtn2;
    private Button screenshotBtn3;
    private Button screenshotBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtn = ((Button) this.findViewById(R.id.btn3));
        mtv = ((TextView) this.findViewById(R.id.tv2));
        rv = ((RelativeLayout) this.findViewById(R.id.rv1));
        arrayList.add(rv);
        arrayList.add(mtv);
        arrayList.add(mbtn);

        screenshotBtn1 = ((Button) this.findViewById(R.id.screenshotBtn1));
        screenshotBtn2 = ((Button) this.findViewById(R.id.screenshotBtn2));
        screenshotBtn3 = ((Button) this.findViewById(R.id.screenshotBtn3));
        screenshotBtn4 = ((Button) this.findViewById(R.id.screenshotBtn4));
        mIvResult = ((ImageView) this.findViewById(R.id.iv));
        final Random random = new Random();
        screenshotBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextInt = random.nextInt(3);
                mbtn.setText("111==" + nextInt);
                mtv.setText("111==" + nextInt);
                mIvResult.setImageBitmap(screenshot1(arrayList.get(nextInt)));
            }
        });

        screenshotBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextInt = random.nextInt(3);
                mtv.setText("222==" + nextInt);
                mbtn.setText("222==" + nextInt);
                mIvResult.setImageBitmap(screenshot2(arrayList.get(nextInt)));
            }
        });

        screenshotBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvResult.setImageBitmap(screenshot2(MainActivity.this.getWindow().getDecorView()));
            }
        });

        screenshotBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvResult.setImageBitmap(screenshot2(MainActivity.this.getWindow().getDecorView()));
            }
        });
    }

    @SuppressWarnings("deprecation")
    private Bitmap screenshot1(View view) {
        //启用DrawingCache并创建位图
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //创建一个DrawingCache的拷贝，因为DrawingCache得到的位图在禁用后会被回收
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        //禁用DrawingCahce否则会影响性能
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private Bitmap screenshot2(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        bitmap.setDensity(getResources().getDisplayMetrics().densityDpi);
        Canvas canvas = new Canvas(bitmap);
        //把view中的内容绘制在画布上
        view.draw(canvas);
        canvas.setBitmap(null);
        return bitmap;
    }

}
