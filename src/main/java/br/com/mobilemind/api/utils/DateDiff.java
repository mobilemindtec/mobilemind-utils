package br.com.mobilemind.api.utils;

/*
 * #%L
 * Mobile Mind - Utils
 * %%
 * Copyright (C) 2012 Mobile Mind Empresa de Tecnologia
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ricardo Bocchi
 */
public class DateDiff {

    private final Calendar calendarOne;
    private final Calendar calendarTwo;
    private long dias;
    private long horas;
    private long minutos;
    private long segundos;

    public DateDiff(long dateOne, long dateTwo) {
        this(dateOne, dateTwo, false);
    }

    public DateDiff(long dateOne, long dateTwo, boolean moreOrLess) {
        this.calendarOne = Calendar.getInstance();
        this.calendarTwo = Calendar.getInstance();

        this.calendarOne.setTimeInMillis(dateOne);
        this.calendarTwo.setTimeInMillis(dateTwo);

        this.calcule(moreOrLess);
    }

    public DateDiff(Date dateOne, Date dateTwo) {
        this(dateOne, dateTwo, false);
    }

    public DateDiff(Date dateOne, Date dateTwo, boolean moreOrLess) {
        this.calendarOne = Calendar.getInstance();
        this.calendarTwo = Calendar.getInstance();

        this.calendarOne.setTime(dateOne);
        this.calendarTwo.setTime(dateTwo);
        this.calcule(moreOrLess);
    }

    private void calcule(boolean moreOrLess) {
        long diff = this.calendarOne.getTimeInMillis() - this.calendarTwo.getTimeInMillis();

        if (moreOrLess && diff < 0) {
            diff *= -1;
        }

        if (!calculeDias(diff)) {
            if (!calculeHoras(diff)) {
                calculeMinutos(diff);
            }
        }
    }

    private boolean calculeDias(long value) {
        this.dias = milescondsToDay(value);

        if (this.dias <= 0) {
            return false;
        }

        calculeHoras(modMilescondsToDay(value));
        return true;
    }

    private boolean calculeHoras(long value) {
        this.horas = milescondsToHour(value);

        if (this.horas <= 0) {
            return false;
        }
        calculeMinutos(modMilescondsToHour(value));
        return true;
    }

    private boolean calculeMinutos(long value) {
        this.minutos = milescondsToMinute(value);

        if (this.minutos <= 0) {
            return false;
        }

        this.segundos = milescondsToSecond(modMilescondsToMinute(value));
        return true;
    }

    public long getDias() {
        return dias;
    }

    public long getHoras() {
        return horas;
    }

    public long getMinutos() {
        return minutos;
    }

    public long getSegundos() {
        return segundos;
    }

    public long getTotal() {
        long total = 0;

        if (this.dias > 0) {
            total += dayToMilescond(dias);
        }

        if (this.horas > 0) {
            total += hourToMilescond(horas);
        }
        if (this.minutos > 0) {
            total += minuteToMilescond(minutos);
        }
        if (this.segundos > 0) {
            total += secondToMilescond(segundos);
        }

        return total;
    }

    public boolean isDiff() {
        return this.horas > 0 || this.dias > 0
                || this.minutos > 0 || this.segundos > 0;
    }

    public static long secondToMilescond(long time) {
        return time * 1000;
    }

    public static long milescondsToSecond(long time) {
        return time / 1000;
    }

    public static long minuteToMilescond(long time) {
        return time * 1000 * 60;
    }

    public static long milescondsToMinute(long time) {
        return time / 1000 / 60;
    }

    public static long modMilescondsToMinute(long time) {
        return time % (60 * 1000);
    }

    public static long hourToMilescond(long time) {
        return time * 1000 * 60 * 60;
    }

    public static long milescondsToHour(long time) {
        return time / 1000 / 60 / 60;
    }

    public static long modMilescondsToHour(long time) {
        return time % (1000 * 60 * 60);
    }

    public static long dayToMilescond(long time) {
        return time * 1000 * 60 * 60 * 24;
    }

    public static long milescondsToDay(long time) {
        return time / 1000 / 60 / 60 / 24;
    }

    public static long modMilescondsToDay(long time) {
        return time % (1000 * 60 * 60 * 24);
    }
}
