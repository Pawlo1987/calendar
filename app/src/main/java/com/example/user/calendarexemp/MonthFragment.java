package com.example.user.calendarexemp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;

import static android.support.v4.content.ContextCompat.startActivity;


//  Фрагмент в котором содержится вывод одного месяца
public class MonthFragment extends Fragment {
    Context context;
    GridView gvMonth;
    ArrayAdapter<String> adapter;
    String[] data = new String[42];
    String[] data1 = new String[42];
    String[] data2 = new String[42];
    Utils utils;
    int page;
    int year = 2018;
    //int[] countDayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    public MonthFragment newInstance(int position, String[] data, String[] data1, String[] data2) {
        this.page = position;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        MonthFragment monthFragment = new MonthFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        monthFragment.setArguments(arguments);
        return monthFragment;
    }

    // Метод onAttach() вызывается в начале жизненного цикла фрагмента, и именно здесь
    // мы можем получить контекст фрагмента, в качестве которого выступает класс MainActivity.
    //onAttach(Context) не вызовется до API 23 версии вместо этого будет вызван onAttach(Activity),
    //коий устарел с 23 API
    //Так что вызовем onAttachToContext
    //https://ru.stackoverflow.com/questions/507008/%D0%9D%D0%B5-%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%D0%B5%D1%82-onattach
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }//onAttach

    //устарел с 23 API
    //Так что вызовем onAttachToContext
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }//if
    }//onAttach

    //Вызовется в момент присоединения фрагмента к активити
    protected void onAttachToContext(Context context) {
        //здесь всегда есть контекст и метод всегда вызовется.
        //тут можно кастовать контест к активити.
        //но лучше к реализуемому ею интерфейсу
        //чтоб не проверять из какого пакета активити в каждом из случаев
        this.context = context;
        utils = new Utils();
        page = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        Arrays.fill(data,"");
        Arrays.fill(data1,"");
        Arrays.fill(data2,"");

        //получаем первый день недели
        page++;
        int dayOfWeek = utils.getDayOfWeek(
                page < 10 ? String.format("01-0%s-2018", page)
                        : String.format("01-%s-2018", page)
        );


        //dayOfWeek коррекция дня недели так как исчесления начинаются с воскресенья
                switch (dayOfWeek){
                    case 2 : {
                        dayOfWeek = 0;
                        break;
                    }case 1 : {
                        dayOfWeek = 6;
                        break;
                    }case 3 : {
                        dayOfWeek = 1;
                        break;
                    }case 4 : {
                        dayOfWeek = 2;
                        break;
                    }case 5 : {
                        dayOfWeek = 3;
                        break;
                    }case 6 : {
                        dayOfWeek = 4;
                        break;
                    }case 7 : {
                        dayOfWeek = 5;
                        break;
                    }
                }
        Log.d("MyLog","date  " + (page < 10 ? String.format("01-0%s-2018", page)
                : String.format("01-%s-2018", page)));
        Log.d("MyLog","dayOfWeek " + String.valueOf(dayOfWeek));

        //получение количества дней в месяце
        int dayOfMonth = utils.getNumberOfDaysInMonth(year, page-1, 1);
//        int dayOfMonth = countDayOfMonth[page-1];
        int j = 1;
        for (int i = dayOfWeek; i < dayOfMonth + dayOfWeek; i++) {
            data[i] = String.valueOf(j);
            data1[i] = "00:00";
            data2[i] = "4";
            j++;
        }
    }//onAttachToContext

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_month, container, false);
        MonthAdapter adapter = new MonthAdapter(context, data, data1, data2);
        gvMonth = (GridView) result.findViewById(R.id.gvMonth);
        gvMonth.setAdapter(adapter);

        //слушатель нажатия на число месяца
        gvMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, data[position].toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DayScheduleActivity.class);
                intent.putExtra("day",Integer.valueOf(data[position]));
                intent.putExtra("month",page);
                intent.putExtra("year",year);
                startActivity(intent);
            }
        });
        adjustGridView();


        return result;
    } // onCreateView

    private void adjustGridView() {
        gvMonth.setNumColumns(7);//numColumns – кол-во столбцов в сетке
    }

}