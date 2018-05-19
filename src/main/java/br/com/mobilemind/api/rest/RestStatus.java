package br.com.mobilemind.api.rest;

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


/**
 *  @see {@link http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html}
 * 
 * @author Ricardo Bocchi
 */
public class RestStatus {

    public static final int UNAUTHORIZED = 401;
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int NO_CONTENT = 204;
    public static final int CONNECTION_NOT_FOUND = 506;
    public static final int NOT_IS_A_PACIENTE = 507;
    public static final int NOT_IS_A_JOSON_OBJECT = 508;
    public static final int ERROR = 509;
    public static final int HTTP_CONNECTION_REFUSED = 510;
    public static final int HTTP_CONNECTION_TIME_OUT = 511;
    public static final int VALIDATION_ERROR = 512;
    public static final int RESOURCE_ALREADY  = 513;
}
