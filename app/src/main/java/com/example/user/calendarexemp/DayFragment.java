package com.example.user.calendarexemp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

// Фрагмент для расписания на рабочий день по времени
public class DayFragment extends Fragment {
    Context context;
    int day;
    int month;
    int year;

    // адаптер для отображения recyclerView
    DayRecyclerAdapter dayRecyclerAdapter;

    // объект для отображения recyclerView
    RecyclerView rvScheduleDaFr;

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

        // прочитать данные, переданные из активности (из точки вызова Фрагмента)
        day = getArguments().getInt("day");
        month = getArguments().getInt("month");
        year = getArguments().getInt("year");
    }//onAttachToContext

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_day, container, false);

        // RecyclerView для отображения таблицы users БД
        rvScheduleDaFr = result.findViewById(R.id.rvScheduleDaFr);

        // создаем адаптер, передаем в него курсор
        dayRecyclerAdapter = new DayRecyclerAdapter(context,480,1020);

        //привязываем адаптер к recycler объекту
        rvScheduleDaFr.setAdapter(dayRecyclerAdapter);

        Toast.makeText(context,String.format("%d/%d/%d",day,month,year),Toast.LENGTH_SHORT).show();

        return result;
    } // onCreateView

}//DayFragment
