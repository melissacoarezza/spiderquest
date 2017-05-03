package com.example.alessia.appragni;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class InterfaceActivity extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener{
    private static final String LOGCAT = null;
    private Bitmap ragnatela_map;
    private RelativeLayout ragnatela;
    private String TAG = getClass().getSimpleName();
    private int ragnoR=1, ragnoB=1, ragnoG=1;

    int g1 = Color.argb(255, 255, 240, 75);
    int g2 = Color.argb(255, 255, 190, 75);
    int g3 = Color.argb(255, 255, 161, 75);
    int g4 = Color.argb(255, 255, 125, 75);
    int g5 = Color.argb(255, 255, 103, 75);
    int b1 = Color.argb(255, 57, 197, 212);
    int b2 = Color.argb(255, 57, 179, 212);
    int b3 = Color.argb(255, 57, 157, 212);
    int b4 = Color.argb(255, 57, 134, 212);
    int b5 = Color.argb(255, 57, 103, 212);
    int v1 = Color.argb(255, 174, 248, 85);
    int v2 = Color.argb(255, 174, 217, 85);
    int v3 = Color.argb(255, 174, 195, 85);
    int v4 = Color.argb(255, 174, 170, 85);
    int v5 = Color.argb(255, 174, 152, 85);

    int [] nodi = { v1, b1, g1, v2, b2, g2, v3, b3, g3, v4, b4, g4, v5, b5, g5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ragnatela_map = ((BitmapDrawable)getResources().getDrawable(R.drawable.ragnatela_bitmap_04)).getBitmap();
        ragnatela = (RelativeLayout)findViewById(R.id.ragnatelaLayout);

        findViewById(R.id.ragnatelaLayout).setOnDragListener(this);
        findViewById(R.id.ragnoR).setOnTouchListener(this);
        findViewById(R.id.ragnoR).setOnTouchListener(this);
        findViewById(R.id.ragnoG).setOnTouchListener(this);
        findViewById(R.id.ragnoB).setOnTouchListener(this);


        /*findViewById(R.id.ragnatelaLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:

                        float vRatio = (float)ragnatela.getHeight()/(float)ragnatela_map.getHeight();
                        float hRatio = (float)ragnatela.getWidth()/ (float)ragnatela_map.getWidth();

                        Log.d(TAG, "Vratio "+vRatio+" Hratio"+hRatio + " Xevent" + motionEvent.getX() + " Yevent"+ motionEvent.getY());

                        Log.d(TAG,  "bitmap W"+ragnatela_map.getWidth()+"bitmap H"+ ragnatela_map.getHeight()+" Bitmap X " + (int)(motionEvent.getX()/hRatio) + " Bitmap Y" + (int)(motionEvent.getY()/vRatio));

                        int color = ragnatela_map.getPixel((int)(motionEvent.getX()/hRatio), (int)(motionEvent.getY()/vRatio));

                        int b = Color.blue(color);
                        int r = Color.red(color);
                        int a = Color.alpha(color);
                        int g = Color.green(color);

                        Log.d(TAG,  "Alpha "+a+" R="+ r +" G=" + g + " B=" + b);


                        if(color == verde){
                            Log.d(TAG, "cliccato verde");
                        }else if(color == giallo){
                            Log.d(TAG, "cliccato giallo");
                        }
                        break;

                }
                return false;
            }
        });*/

    }

    public boolean onDrag(View layoutview, DragEvent dragevent){
        int action = dragevent.getAction();
        View view = (View) dragevent.getLocalState();

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:

                float vRatio = (float) ragnatela.getHeight() / (float) ragnatela_map.getHeight();
                float hRatio = (float) ragnatela.getWidth() / (float) ragnatela_map.getWidth();

                int color = ragnatela_map.getPixel((int) (dragevent.getX() / hRatio), (int) (dragevent.getY() / vRatio));

                for (int i = 0; i < nodi.length; i++) {
                    if (color == nodi[i]) {
                        if(color != nodi[ragnoG]&& color != nodi[ragnoR] && color != nodi[ragnoB]) {

                            String tagRagno = (String) view.getTag();


                            if (view.getTag().equals("ragnoR")) {
                                ragnoR = i;
                            } else if (view.getTag().equals("ragnoB")) {
                                ragnoB = i;
                            } else if (view.getTag().equals("ragnoG")) {
                                ragnoG = i;
                            }

                            Log.d(TAG, "Posizione di " + tagRagno + " si trova nel nodo numero " + i);
                            Log.d(TAG, "ragnoB= " + ragnoB + " ragnoR= " + ragnoR + " ragnoG= " + ragnoG);

                            ViewGroup owner = (ViewGroup) view.getParent();
                            owner.removeView(view);
                            RelativeLayout container = (RelativeLayout) layoutview;
                            RelativeLayout.LayoutParams lp = new RelativeLayout.
                                    LayoutParams(130, 130);

                            lp.setMargins((int) dragevent.getX() - 130 / 2, (int) dragevent.getY() - 130 / 2, 0, 0);
                            container.addView(view, lp);
                            container.getId();
                        }

                    }
                }
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(LOGCAT, "Drag ended");
                if (dropEventNotHandled(dragevent)) {
                    view.setVisibility(View.VISIBLE);
                }

                break;
            default:
                break;
        }
        return true;
    }

    private boolean dropEventNotHandled(DragEvent dragEvent) {
        return !dragEvent.getResult();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());
            String[] clipDescription = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData dragData = new ClipData((CharSequence)view.getTag(), clipDescription, item);
            view.startDrag(dragData, shadowBuilder, view, 0);

            view.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }

    public void onRestoreInstantState(Bundle savedInstateState){
        super.onRestoreInstanceState(savedInstateState);
    }
}
