package br.com.mobilemind.api.security.key;

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


import java.security.MessageDigest;

/**
 * Classe utilitaria de criptografia
 * 
 * @author Ricardo Bocchi
 */
public class MD5Encryptor {

    public static String hex(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static String md5Hex(final String message) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (final Exception e) {
            
        }
        return null;
    }
}
