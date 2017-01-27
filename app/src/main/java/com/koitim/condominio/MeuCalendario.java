package com.koitim.condominio;

import android.content.Context;
import android.graphics.RectF;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 97903736515 on 15/12/16.
 */

public class MeuCalendario extends WeekView implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

  private Context context;

  public MeuCalendario(Context context) {
    super(context);
    this.context = context;
    setOnEventClickListener(this);
    setMonthChangeListener(this);
  }

  @Override
  public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
    // Popular a lista com eventos
    List<WeekViewEvent> events = new ArrayList<>();

    Calendar startTime = Calendar.getInstance();
    startTime.set(Calendar.HOUR_OF_DAY, 3);
    startTime.set(Calendar.MINUTE, 0);
    startTime.set(Calendar.MONTH, newMonth - 1);
    startTime.set(Calendar.YEAR, newYear);
    Calendar endTime = (Calendar) startTime.clone();
    endTime.add(Calendar.HOUR, 1);
    endTime.set(Calendar.MONTH, newMonth - 1);
    WeekViewEvent event = new WeekViewEvent(1, "Titulo", startTime, endTime);
    event.setColor(getResources().getColor(android.R.color.holo_blue_bright));
    events.add(event);

    return events;
  }

  @Override
  public void onEmptyViewLongPress(Calendar time) {
    Toast.makeText(context, "View vazia press: "+time.toString(), Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onEventClick(WeekViewEvent event, RectF eventRect) {
    Toast.makeText(context, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
    Toast.makeText(context, "LongPress " + event.getName(), Toast.LENGTH_SHORT).show();
  }
}
