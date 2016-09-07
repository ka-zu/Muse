package com.shun.prototype;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Sound_Back extends Activity {
    static final int RESULT = 1000;
    int sound[]={0,0,0,0};//データ保存用変数
    int beat=100;
    int edit=0;

    private GrafhicView graphicView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundback);

        Intent intent=getIntent();
        sound[0]=intent.getIntExtra("S1",0);//データ受け取り
        sound[1]=intent.getIntExtra("S2",0);
        sound[2]=intent.getIntExtra("S3",0);
        sound[3]=intent.getIntExtra("S4",0);
        beat=intent.getIntExtra("BEAT",0);

        /*Button sendbutton1 = (Button) findViewById(R.id.send_button1);
        sendbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplication(), Option.class);
                intent1.putExtra("S1",sound[0]);//各データの転送
                intent1.putExtra("S2",sound[1]);
                intent1.putExtra("S3",sound[2]);
                intent1.putExtra("S4",sound[3]);
                intent1.putExtra("BEAT",beat);
                int requestCode=RESULT;
                startActivityForResult(intent1,requestCode);
            }
        });

        Button sendbutton2 = (Button) findViewById(R.id.send_button2);
        sendbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.putExtra("RES_S1",sound[0]);//データを返す
                intent2.putExtra("RES_S2",sound[1]);
                intent2.putExtra("RES_S3",sound[2]);
                intent2.putExtra("RES_S4",sound[3]);
                intent2.putExtra("RES_BEAT",beat);
                setResult(RESULT_OK,intent2);
                finish();
            }
        });*/

        //GraphicViewのオブジェクト生成
        graphicView = new GrafhicView(this);
        setContentView(graphicView);
        graphicView.setScene(false);
        graphicView.onResume();

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if(resultCode==RESULT_OK  && requestCode==RESULT && null!=intent){
            sound[0]=intent.getIntExtra("RES_S1",0);//データの受取と反映
            sound[1]=intent.getIntExtra("RES_S2",0);
            sound[2]=intent.getIntExtra("RES_S3",0);
            sound[3]=intent.getIntExtra("RES_S4",0);
            beat=intent.getIntExtra("RES_BEAT",0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {//タッチイベントを拾う
        float getx=motionEvent.getX();
        float gety=motionEvent.getY();
        float nowx[]=graphicView.getBxpoint();
        float nowy[]=graphicView.getBypoint();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN://押した時
                Log.d("", "ACTION_DOWN");
                Log.d("", "EventLocation X:" + getx + ",Y:" + gety);
                break;
            case MotionEvent.ACTION_UP://離した時
                Log.d("", "ACTION_UP");
                long eventDuration2 = motionEvent.getEventTime() - motionEvent.getDownTime();
                Log.d("", "eventDuration2: " +eventDuration2+" msec");
                Log.d("", "Pressure: " + motionEvent.getPressure());
                Log.d("", "edit="+edit);
                Log.d("","getx   ="+getx+",gety   ="+gety);
                Log.d("","nowx[0]="+nowx[0]+",nowy[0]="+nowy[0]);


                if(getx<60 && gety<120) {//座標判定
                    Intent intent1 = new Intent(getApplication(), Option.class);
                    intent1.putExtra("S1", sound[0]);//各データの転送
                    intent1.putExtra("S2", sound[1]);
                    intent1.putExtra("S3", sound[2]);
                    intent1.putExtra("S4", sound[3]);
                    intent1.putExtra("BEAT", beat);
                    int requestCode = RESULT;
                    startActivityForResult(intent1, requestCode);
                }

                else if(getx>1090 && gety<120){
                    Intent intent2 = new Intent();
                    intent2.putExtra("RES_S1",sound[0]);//データを返す
                    intent2.putExtra("RES_S2",sound[1]);
                    intent2.putExtra("RES_S3",sound[2]);
                    intent2.putExtra("RES_S4",sound[3]);
                    intent2.putExtra("RES_BEAT",beat);
                    setResult(RESULT_OK,intent2);
                    finish();
                }

                else if(getx>nowx[0]+20 && getx<nowx[0]+90 && gety>nowy[0]+60 && gety<nowy[0]+130){
                    graphicView.setBxpoint(0,-1);
                    graphicView.setBypoint(0,-1);
                }
                else if(getx>nowx[1]+20 && getx<nowx[1]+90 && gety>nowy[1]+60 && gety<nowy[1]+130){
                    graphicView.setBxpoint(1,-1);
                    graphicView.setBypoint(1,-1);
                }
                else if(getx>nowx[2]+20 && getx<nowx[2]+90 && gety>nowy[2]+60 && gety<nowy[2]+130){
                    graphicView.setBxpoint(2,-1);
                    graphicView.setBypoint(2,-1);
                }
                else if(getx>nowx[3]+20 && getx<nowx[3]+90 && gety>nowy[3]+60 && gety<nowy[3]+130){
                    graphicView.setBxpoint(3,-1);
                    graphicView.setBypoint(3,-1);
                }
                else if(getx>nowx[4]+20 && getx<nowx[4]+90 && gety>nowy[4]+60 && gety<nowy[4]+130){
                    graphicView.setBxpoint(4,-1);
                    graphicView.setBypoint(4,-1);
                }

                else if(getx<60 && gety>430 && gety<929){
                    edit=((int)gety-430)/100;
                }

                else if(getx<60 && gety>930 && gety<1010){
                    for(int i=0;i<5;i++) {
                        graphicView.setBxpoint(i, -1);
                        graphicView.setBypoint(i, -1);
                    }
                }

                else{
                    graphicView.setBxpoint(edit,getx-20);
                    graphicView.setBypoint(edit,gety-60);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("", "ACTION_CANCEL");
                break;
        }

        return false;
    }
}
