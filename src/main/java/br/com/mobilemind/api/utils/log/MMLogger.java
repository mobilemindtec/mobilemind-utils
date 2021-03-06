package br.com.mobilemind.api.utils.log;

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


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Ricardo Bocchi
 */
public class MMLogger {

    private static boolean logable = true;
    private static List<AppLogger> loggers;

    static {
        loggers = new ArrayList<AppLogger>();
    }

    public static void addLogger(AppLogger logger) {
        loggers.add(logger);
    }

    public static List<AppLogger> getLoggers() {
        return loggers;
    }

    public static void log(Level level, Class clazz, String message) {
        for (AppLogger jl : loggers) {
            jl.log(level, clazz, message);
        }
    }

    public static void log(Level level, Class clazz, String message, Exception e) {
        for (AppLogger jl : loggers) {
            jl.log(level, clazz, message, e);
        }
    }

    public static void log(Level level, Class clazz, Exception e) {
        for (AppLogger jl : loggers) {
            jl.log(level, clazz, e);
        }
    }

    public static boolean isLogable() {
        return MMLogger.logable;
    }

    public static void setActive(boolean logable) {
        MMLogger.logable = logable;
    }
}
