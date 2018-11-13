package graduationwork.buskingtown;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Statistics extends AppCompatActivity implements View.OnClickListener{

    Button oneMonth, threeMonth, sixMonth, oneYearz, coinSD, coinED;
    int coinStartYear, coinStartMonth, coinStartDay , coinEndYear, coinEndMonth, coinEndDay;
    SimpleDateFormat simpleDateFormat;

    private final int oneFRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        callFragment(oneFRAGMENT);

//        LinearLayout clickDropdownBox = (LinearLayout) findViewById(R.id.clickDropdownBox);
//        ImageButton dropdown= (ImageButton)findViewById(R.id.dropdownImg);
//        dropdown.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                clickDropdownBox.setVisibility(View.VISIBLE);
//                dropdown.setVisibility(View.INVISIBLE);
//            }
//        });

        //버튼 참조변수
        oneMonth = (Button) findViewById(R.id.oneMonth);
        threeMonth = (Button) findViewById(R.id.threeMonth);
        sixMonth = (Button) findViewById(R.id.sixMonth);
        oneYearz = (Button) findViewById(R.id.oneYearz);

        //클릭메소드 연결
        oneMonth.setOnClickListener(this);
        threeMonth.setOnClickListener(this);
        sixMonth.setOnClickListener(this);
        oneYearz.setOnClickListener(this);

        //달력 객체 불러옴
        final GregorianCalendar startCalendar = new GregorianCalendar();

        coinStartYear = startCalendar.get(Calendar.YEAR);
        coinStartMonth = startCalendar.get(Calendar.MONTH);
        coinStartDay = startCalendar.get(Calendar.DAY_OF_MONTH);

        final GregorianCalendar endCalendar = new GregorianCalendar();
        coinEndYear = endCalendar.get(Calendar.YEAR);
        coinEndMonth = endCalendar.get(Calendar.MONTH);
        coinEndDay = endCalendar.get(Calendar.DAY_OF_MONTH);

        //날짜형태
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

        //데이트 피커
        coinSD = (Button) findViewById(R.id.coinSD);
        coinSD.setText(String.format("%2d년 %02d월 %02d일",coinStartYear,coinStartMonth,coinStartDay));
        coinSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog.OnDateSetListener dateStartPicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        coinSD.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
                    }
                };


                new SpinnerDatePickerDialogBuilder()
                        .callback(dateStartPicker)
                        .context(Statistics.this)
                        .spinnerTheme(R.style.DatePickerSpinner)
                        .showTitle(true)
                        .defaultDate(coinStartYear,coinStartMonth,coinStartDay)
                        .build()
                        .show();
            }
        });

        coinED = (Button) findViewById(R.id.coinED);
        coinED.setText(String.format("%2d년 %02d월 %02d일",coinEndYear,coinEndMonth+1,coinEndDay));
        coinED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener dateEndPicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        coinED.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
                    }
                };

                new SpinnerDatePickerDialogBuilder()
                        .callback(dateEndPicker)
                        .context(Statistics.this)
                        .spinnerTheme(R.style.DatePickerSpinner)
                        .showTitle(true)
                        .defaultDate(coinEndYear,coinEndMonth,coinEndDay)
                        .build()
                        .show();
            }
        });

        //아래는 뒤로가기 버튼 클릭시 뒤로가는거임
        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Statistics.super.onBackPressed(); }
        });

        Spinner spinner = (Spinner)findViewById(R.id.spinner_drop);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.statistics_menu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(parent.getContext(), "Spinner item 1!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(parent.getContext(), "Spinner item 2!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Spinner item 3!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(parent.getContext(), "Spinner item 4!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });
    }

    public void previousActivity(View v){
        onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.oneMonth :
                //1개월 버튼색 활성화
                callFragment(oneFRAGMENT);
                oneMonth.setBackground(getDrawable(R.drawable.able_btn));
                oneMonth.setTextColor(Color.parseColor("#ffffff"));
                coinSD.setText(String.format("%2d년 %02d월 %02d일",coinStartYear,coinStartMonth,coinStartDay));
                //나머지 버튼색 비활성화
                threeMonth.setBackground(getDrawable(R.drawable.date_rounded));
                threeMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                sixMonth.setBackground(getDrawable(R.drawable.date_rounded));
                sixMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                oneYearz.setBackground(getDrawable(R.drawable.date_rounded));
                oneYearz.setTextColor(getResources().getColor(R.color.mainPurple));
                break;

            case R.id.threeMonth :
                //3개월 버튼색 활성화
                threeMonth.setBackground(getDrawable(R.drawable.able_btn));
                threeMonth.setTextColor(Color.parseColor("#ffffff"));
                coinSD.setText(String.format("%2d년 %02d월 %02d일",coinStartYear,coinStartMonth-2,coinStartDay));
                //나머지 버튼색 비활성화
                oneMonth.setBackground(getDrawable(R.drawable.date_rounded));
                oneMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                sixMonth.setBackground(getDrawable(R.drawable.date_rounded));
                sixMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                oneYearz.setBackground(getDrawable(R.drawable.date_rounded));
                oneYearz.setTextColor(getResources().getColor(R.color.mainPurple));
                break;

            case R.id.sixMonth :
                //6개월 버튼색 활성화
                sixMonth.setBackground(getDrawable(R.drawable.able_btn));
                sixMonth.setTextColor(Color.parseColor("#ffffff"));
                coinSD.setText(String.format("%2d년 %02d월 %02d일",coinStartYear,coinStartMonth-5,coinStartDay));
                //나머지 버튼색 비활성화
                oneMonth.setBackground(getDrawable(R.drawable.date_rounded));
                oneMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                threeMonth.setBackground(getDrawable(R.drawable.date_rounded));
                threeMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                oneYearz.setBackground(getDrawable(R.drawable.date_rounded));
                oneYearz.setTextColor(getResources().getColor(R.color.mainPurple));
                break;

            case R.id.oneYearz :
                //1년 버튼색 활성화
                oneYearz.setBackground(getDrawable(R.drawable.able_btn));
                oneYearz.setTextColor(Color.parseColor("#ffffff"));
                coinSD.setText(String.format("%2d년 %02d월 %02d일",coinStartYear-1,coinStartMonth+1,coinStartDay));
                //나머지 버튼색 비활성화
                oneMonth.setBackground(getDrawable(R.drawable.date_rounded));
                oneMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                threeMonth.setBackground(getDrawable(R.drawable.date_rounded));
                threeMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                sixMonth.setBackground(getDrawable(R.drawable.date_rounded));
                sixMonth.setTextColor(getResources().getColor(R.color.mainPurple));
                break;
        }
    }



    private void callFragment(int frament_no) {
        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no) {
            case 1:
                // '그래프' 호출
                Graph graphFragment = new Graph();
                transaction.replace(R.id.fragmentContainer, graphFragment);
                transaction.commit();

                break;

        }
    }




}
