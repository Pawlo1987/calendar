package com.example.user.calendarexemp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

public class DayScheduleActivity extends AppCompatActivity {
//        implements View.OnTouchListener {
    int day;
    int month;
    int year;

    private ImageView mImageView;
    private ViewGroup mReAdLayout;
    private ViewGroup mMoveLayout;
//    private int mX;
//    private int mY;
    TextView tvNameFirstMaster;
    TextView tvNameSecondMaster;
    //фрагмент расписания рабочего дня у администратора
    DayFragment dayFragment;
    FragmentTransaction fTrans;
    RecyclerView rvScheduleDaFr;

    public DayScheduleActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_schedule);

        //Связываемся с нашими объектами, определяя изображение через заданный ViewGroup:
        mMoveLayout = (ViewGroup) findViewById(R.id.move);

        //получаем доступ к layout "fragment_day"
        LayoutInflater inflater = LayoutInflater.from(this);
        View myView = inflater.inflate(R.layout.fragment_day, mReAdLayout);

        //Связываемся с нашими объектами, определяя изображение через заданный ViewGroup:
        mReAdLayout = (ViewGroup) myView.findViewById(R.id.dayReAd);

        rvScheduleDaFr = (RecyclerView) mReAdLayout.findViewById(R.id.rvScheduleDaFr);

        mImageView = (ImageView) mMoveLayout.findViewById(R.id.ImageView);
        tvNameFirstMaster = (TextView) findViewById(R.id.tvNameFirstMaster);
        tvNameSecondMaster = (TextView) findViewById(R.id.tvNameSecondMaster);


        //Создаем программно RelativeLayout с параметрами 100*100:
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(200, 200);

        //Применяем эти параметры к нашему изображению:
        mImageView.setLayoutParams(layoutParams);
        //И настраиваем ему слушателя (обработчик) прикосновений:
//        mImageView.setOnTouchListener(this);

        //создаем экземпляр объекта DayFragment
        dayFragment = new DayFragment();

        Intent intent = getIntent();
        day = intent.getIntExtra("day",0);
        month = intent.getIntExtra("month",0);
        year = intent.getIntExtra("year",0);

        // объект для передачи параметров во фрагмент
        Bundle args = new Bundle();
        args.putInt("day", day);
        args.putInt("month", month);
        args.putInt("year", year);
        dayFragment.setArguments(args);

        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragment_schedule_layout, dayFragment);
        fTrans.commit();

    }//onCreate

//    //Обрабатываем прикосновения к объекту:
//    @Override
//    public boolean onTouch(View view, MotionEvent event) {
//
//        //Определение координат через getRawX() и getRawY() дает
//        //координаты по отношению к размерам экрана устройства:
//        final int X = (int) event.getRawX();
//        final int Y = (int) event.getRawY();
//
//        //.getLocationOnScreen - координаты объекта
//        //сохраняются в int[] массив, размер 2 числа
//        //координаты левого верхнего угла
//
//        //.getWidth() - ширина объекта
//        //от начальной точки объекта до конечной точки объекта
//        //по оси абсцисс
//
//        //.getHeight() - высота объекта
//        //от начальной точки объекта до конечной точки объекта
//        //по оси ординат
//
//        int test1[] = new int[2];
////        tvNameFirstMaster.getWidth();
////        Log.d("MyLog", "W: "+ view1.getHeight());
//        tvNameFirstMaster.getLocationOnScreen(test1);
//        Log.d("MyLog", "x1: "+ test1[0] + "y1: "+ test1[1]);
//        tvNameSecondMaster.getLocationOnScreen(test1);
//        Log.d("MyLog", "x2: "+ test1[0] + "y2: "+ test1[1]);
//        Log.d("MyLog", "x: "+X+" y: "+Y);
//        tvNameFirstMaster.setText(String.valueOf(X));
//        tvNameSecondMaster.setText(String.valueOf(Y));
//
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//
//            //ACTION_DOWN срабатывает при прикосновении к экрану,
//            //здесь определяется начальное стартовое положение объекта:
//            case MotionEvent.ACTION_DOWN:
//                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                mX = X - lParams.leftMargin;
//                mY = Y - lParams.topMargin;
//                break;
//
//            //ACTION_MOVE обрабатывает случившиеся в процессе прикосновения изменения, здесь
//            //содержится информация о последней точке, где находится объект после окончания действия прикосновения ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
//                        .getLayoutParams();
//                layoutParams.leftMargin = X - mX;
//                layoutParams.topMargin = Y - mY;
//                layoutParams.rightMargin = -250;
//                layoutParams.bottomMargin = -250;
//                view.setLayoutParams(layoutParams);
//                break;
////            //ACTION_UP обрабатывает случившиеся в процессе отпускания прикосновения
////            case MotionEvent.ACTION_UP: // отпускание
////            case MotionEvent.ACTION_CANCEL:
////                if((X > 500) && (Y > 500)){
////                mImageView.setX(300);
////                mImageView.setY(300);
////                }
////                break;
//        }
//        return true;
//    }
}
