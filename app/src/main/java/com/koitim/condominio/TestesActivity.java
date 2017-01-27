package com.koitim.condominio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alamkanak.weekview.WeekView;

public class TestesActivity extends AppCompatActivity {

  private WeekView mcv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.testes_activity);
    mcv = (WeekView) findViewById(R.id.weekView);
    MeuCalendario meuCalendario = new MeuCalendario(getApplicationContext());
//    mcv.state().edit()
//            .setFirstDayOfWeek(Calendar.WEDNESDAY)
//            //.setMinimumDate(CalendarDay.from(2016, 3, 3))
//            //.setMaximumDate(CalendarDay.from(2016, 5, 12))
//            .setCalendarDisplayMode(CalendarMode.MONTHS)
//            .commit();
//
//    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
////    mcv.setSelectedDate(calendar);
////    calendar.set(2016, Calendar.DECEMBER, 5);
////    mcv.setSelectedDate(calendar);
//    com.prolificinteractive.materialcalendarview.WeekView w = new com.prolificinteractive.materialcalendarview.WeekView(mcv, CalendarDay.from(calendar), 2);
//    DayFormatter df = new DayFormatter() {
//      @NonNull
//      @Override
//      public String format(@NonNull CalendarDay day) {
//        return "Dia";
//      }
//    };
//    w.setDayFormatter(df);
//    w.setWeekDayTextAppearance(52);
//    WeekView wv = new WeekView(this);
//    wv.setDayBackgroundColor(1);
  }
}
