package br.com.mobilemind.api.test;

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


import br.com.mobilemind.api.utils.DateDiff;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Ricardo Bocchi
 */
public class DateDiffTestCase {

    @Test
    public void testConvertSecondToMilesconds() {
        int value = 60;

        Assert.assertEquals(60000, DateDiff.secondToMilescond(value));
    }

    @Test
    public void testConvertMilescondsToSecond() {
        int value = 60000;

        Assert.assertEquals(60, DateDiff.milescondsToSecond(value));
    }

    @Test
    public void testConvertMinuteToMilescond() {
        int value = 1;

        Assert.assertEquals(60000, DateDiff.minuteToMilescond(value));
    }

    @Test
    public void testConvertMilescondsToMinute() {
        int value = 60000;

        Assert.assertEquals(1, DateDiff.milescondsToMinute(value));
    }

    @Test
    public void testConvertHourToMilescond() {
        int value = 1;

        Assert.assertEquals(3600000, DateDiff.hourToMilescond(value));
    }

    @Test
    public void testConvertMilescondsToHour() {
        int value = 3600000;

        Assert.assertEquals(1, DateDiff.milescondsToHour(value));
    }

    @Test
    public void testConvertDayToMilescond() {
        int value = 1;

        Assert.assertEquals(86400000, DateDiff.dayToMilescond(value));
    }

    @Test
    public void testConvertMilescondsToDay() {
        int value = 86400000;

        Assert.assertEquals(1, DateDiff.milescondsToDay(value));
    }

    @Test
    public void testConvertModeMilescondsToMinute() {
        int value = 61000;

        Assert.assertEquals(1000, DateDiff.modMilescondsToMinute(value));

        value = 65000;

        Assert.assertEquals(5000, DateDiff.modMilescondsToMinute(value));
    }

    @Test
    public void testConvertModeMilescondsToHour() {
        int value = 3660000;

        Assert.assertEquals(60000, DateDiff.modMilescondsToHour(value));

        value = 3630000;

        Assert.assertEquals(30000, DateDiff.modMilescondsToHour(value));
    }

    @Test
    public void testConvertModMilescondsToDay() {
        int value = 86460000;

        Assert.assertEquals(60000, DateDiff.modMilescondsToDay(value));
    }
}
