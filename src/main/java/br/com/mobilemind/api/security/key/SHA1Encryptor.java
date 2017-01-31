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
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ricardo Bocchi
 */
public final class SHA1Encryptor {

    private static final String hexDigits = "0123456789abcdef";
    private static final String ALGORITIMO = "SHA-1";

    /** 
     * Realiza um digest em um array de bytes através do algoritmo especificado 
     * @param input - O array de bytes a ser criptografado 
     * @param algoritmo - O algoritmo a ser utilizado 
     * @return byte[] - O resultado da criptografia 
     * @throws NoSuchAlgorithmException - Caso o algoritmo fornecido não seja 
     * válido 
     */
    public static byte[] digest(byte[] input)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(ALGORITIMO);
        md.reset();
        return md.digest(input);
    }

    /** 
     * Converte o array de bytes em uma representação hexadecimal. 
     * @param input - O array de bytes a ser convertido. 
     * @return Uma String com a representação hexa do array 
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < b.length; i++) {
            int j = ((int) b[i]) & 0xFF;
            buf.append(hexDigits.charAt(j / 16));
            buf.append(hexDigits.charAt(j % 16));
        }

        return buf.toString();
    }

    /** 
     * Converte uma String hexa no array de bytes correspondente. 
     * @param hexa - A String hexa 
     * @return O vetor de bytes 
     * @throws IllegalArgumentException - Caso a String não sej auma 
     * representação haxadecimal válida 
     */
    public static byte[] hexStringToByteArray(String hexa)
            throws IllegalArgumentException {

        //verifica se a String possui uma quantidade par de elementos  
        if (hexa.length() % 2 != 0) {
            throw new IllegalArgumentException("String hexa inválida");
        }

        byte[] b = new byte[hexa.length() / 2];

        for (int i = 0; i < hexa.length(); i += 2) {
            b[i / 2] = (byte) ((hexDigits.indexOf(hexa.charAt(i)) << 4)
                    | (hexDigits.indexOf(hexa.charAt(i + 1))));
        }
        return b;
    }
}
