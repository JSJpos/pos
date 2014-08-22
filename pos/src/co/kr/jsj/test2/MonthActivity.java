package co.kr.jsj.test2;


import java.lang.reflect.Field;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MonthActivity extends Activity {
   private LinearLayout datePick;
   private TextView pick_year, pick_month;
   private int myYear, myMonth, myDay;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_month);

      init();
      functionClick();

   }

   public void init() {
      datePick = (LinearLayout) findViewById(R.id.datePick);
      pick_year = (TextView) findViewById(R.id.pick_year);
      pick_month = (TextView) findViewById(R.id.pick_month);

   }

   public void functionClick() {
      datePick.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            try{
               DatePickerDialog datepicker = new DatePickerDialog(MonthActivity.this,
                     dateSetListener, myYear, myMonth, myDay);
               
                Field[] f = datepicker.getClass().getDeclaredFields();
                for (Field dateField : f) {
                    if(dateField.getName().equals("mDatePicker")) {
                        dateField.setAccessible(true);
                             
                        DatePicker datePicker = (DatePicker)dateField.get(datepicker);
                             
                        Field datePickerFields[] = dateField.getType().getDeclaredFields();
                        
                        for(Field datePickerField : datePickerFields) {
                            if("mDaySpinner".equals(datePickerField.getName())) {
                                datePickerField.setAccessible(true);
                                Object dayPicker = new Object();
                                dayPicker = datePickerField.get(datePicker);
                                ((View)dayPicker).setVisibility(View.GONE);
                            }
                        }
                    }
                }

               datepicker.show();
            }
            catch (IllegalArgumentException e) {
               e.printStackTrace();
            }
            catch (IllegalAccessException e) {
               e.printStackTrace();
            }
            
         }
      });

      final Calendar c = Calendar.getInstance();
      myYear = c.get(Calendar.YEAR);
      myMonth = c.get(Calendar.MONTH);

   }

   public void updateDisplay() {
      pick_year.setText(new StringBuilder().append(myYear));
      pick_month.setText(new StringBuilder().append(myMonth+1));
   }

   DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear,
            int dayOfMonth) {
         myYear = year;
         myMonth = monthOfYear;
         updateDisplay();
      }
   };

}