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


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;

/**
 *
 * @author Ricardo Bocchi
 */
public class KeyTools {

    public static PrivateKey getPrivateKeyFromFile(File cert, String alias, String password) throws Exception {
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] pwd = password.toCharArray();
        InputStream is = new FileInputStream(cert);
        ks.load(is, pwd);
        is.close();
        Key key = ks.getKey(alias, pwd);
        if (key instanceof PrivateKey) {
            return (PrivateKey) key;
        }
        return null;
    }

    /** 
     * Extrai a chave pública do arquivo. 
     */
    public static PublicKey getPublicKeyFromFile(File cert, String alias, String password) throws Exception {
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] pwd = password.toCharArray();
        InputStream is = new FileInputStream(cert);
        ks.load(is, pwd);
        Key key = ks.getKey(alias, pwd);
        Certificate c = ks.getCertificate(alias);
        PublicKey p = c.getPublicKey();
        return p;
    }

    /** 
     * Retorna a assinatura para o buffer de bytes, usando a chave privada. 
     * @param key PrivateKey 
     * @param buffer Array de bytes a ser assinado. 
     */
    public static byte[] createSignature(PrivateKey key, byte[] buffer) throws Exception {
        Signature sig = Signature.getInstance("SHA-1");
        sig.initSign(key);
        sig.update(buffer, 0, buffer.length);
        return sig.sign();
    }

    /** 
     * Verifica a assinatura para o buffer de bytes, usando a chave pública. 
     * @param key PublicKey 
     * @param buffer Array de bytes a ser verficado. 
     * @param sgined Array de bytes assinado (encriptado) a ser verficado. 
     */
    public static boolean verifySignature(PublicKey key, byte[] buffer, byte[] signed) throws Exception {
        Signature sig = Signature.getInstance("SHA-1");
        sig.initVerify(key);
        sig.update(buffer, 0, buffer.length);
        return sig.verify(signed);
    }

    /** 
     * Converte um array de byte em uma representação, em String, de seus hexadecimais. 
     */
    public static String txt2Hexa(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        String hexDigits = "0123456789abcdef";
        StringBuffer sbuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int j = ((int) bytes[i]) & 0xFF;
            sbuffer.append(hexDigits.charAt(j / 16));
            sbuffer.append(hexDigits.charAt(j % 16));
        }
        return sbuffer.toString();
    }
}
