package com.example.user.calendarexemp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//Адаптер для расписания на день по рабочим часам
public class DayRecyclerAdapter extends
        RecyclerView.Adapter<DayRecyclerAdapter.ViewHolder> implements View.OnTouchListener{
    Context context;
    Utils utils;
    //начало и конец рабочего временни в минутах
    int startTime;
    int finishTime;
    private int mX;
    private int mY;
    //лист рабочего времени (с периодом в 1 час) в минутах
    List<Integer> listWorkTime = new ArrayList<>();

    //поля класса DayRecyclerAdapter
    private LayoutInflater inflater;

    //конструктор
    public DayRecyclerAdapter(Context context, int startTime, int finishTime) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.startTime = startTime;
        this.finishTime = finishTime;
        utils = new Utils();

        //заполняем лист с рабочим врменем
        int tempTime = startTime;
        listWorkTime.add(tempTime);
        while(tempTime < finishTime) {
            tempTime = tempTime + 60;
            listWorkTime.add(tempTime);
        }//while

    }//DayRecyclerAdapter

    //создаем новую разметку(View) путем указания разметки
    @Override
    public DayRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_adapter_day, parent, false);
        return new ViewHolder(view);
    }

    //привязываем элементы разметки к переменным объекта(в данном случае к курсору)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int time = listWorkTime.get(position);
        holder.tvTimeReAdDay.setText(utils.convertTime(time)); //Время

    }

    @Override
    public int getItemCount() {
        return listWorkTime.size();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //Определение координат через getRawX() и getRawY() дает
        //координаты по отношению к размерам экрана устройства:
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        //.getLocationOnScreen - координаты объекта
        //сохраняются в int[] массив, размер 2 числа
        //координаты левого верхнего угла

        //.getWidth() - ширина объекта
        //от начальной точки объекта до конечной точки объекта
        //по оси абсцисс

        //.getHeight() - высота объекта
        //от начальной точки объекта до конечной точки объекта
        //по оси ординат

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

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            //ACTION_DOWN срабатывает при прикосновении к экрану,
            //здесь определяется начальное стартовое положение объекта:
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                mX = X - lParams.leftMargin;
                mY = Y - lParams.topMargin;
                break;

            //ACTION_MOVE обрабатывает случившиеся в процессе прикосновения изменения, здесь
            //содержится информация о последней точке, где находится объект после окончания действия прикосновения ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = X - mX;
                layoutParams.topMargin = Y - mY;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
//            //ACTION_UP обрабатывает случившиеся в процессе отпускания прикосновения
//            case MotionEvent.ACTION_UP: // отпускание
//            case MotionEvent.ACTION_CANCEL:
//                if((X > 500) && (Y > 500)){
//                mImageView.setX(300);
//                mImageView.setY(300);
//                }
//                break;
        }
        return true;
    }

    //Создаем класс ViewHolder с помощью которого мы получаем ссылку на каждый элемент
    //отдельного пункта списка и подключаем слушателя события нажатия меню
    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTimeReAdDay;
              View view1, view2, view3, view4, view5;
              View view6, view7, view8, view9, view10;
              View view11, view12, view13, view14, view15;
              View view16, view17, view18, view19, view20;
              View view21;

        ViewHolder(View view) {
            super(view);
            tvTimeReAdDay = view.findViewById(R.id.tvTimeReAdDay);
            view1 = view.findViewById(R.id.view1);
            view2 = view.findViewById(R.id.view2);
            view3 = view.findViewById(R.id.view3);
            view4 = view.findViewById(R.id.view4);
            view5 = view.findViewById(R.id.view5);
            view6 = view.findViewById(R.id.view6);
            view7 = view.findViewById(R.id.view7);
            view8 = view.findViewById(R.id.view8);
            view9 = view.findViewById(R.id.view9);
            view10 = view.findViewById(R.id.view10);
            view11 = view.findViewById(R.id.view11);
            view12 = view.findViewById(R.id.view12);
            view13 = view.findViewById(R.id.view13);
            view14 = view.findViewById(R.id.view14);
            view15 = view.findViewById(R.id.view15);
            view16 = view.findViewById(R.id.view16);
            view17 = view.findViewById(R.id.view17);
            view18 = view.findViewById(R.id.view18);
            view19 = view.findViewById(R.id.view19);
            view20 = view.findViewById(R.id.view20);
            view21 = view.findViewById(R.id.view21);
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int test1[] = new int[2];
                    view1.getLocationOnScreen(test1);
                    Log.d("MyLog1", "x1: "+ test1[0] + " y1: "+ test1[1]);
                }
            });
        } // ViewHolder
    }
}//DayRecyclerAdapter
