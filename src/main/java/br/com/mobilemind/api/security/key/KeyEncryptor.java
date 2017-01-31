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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.RSAKeyGenParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Ricardo Bocchi
 */
public class KeyEncryptor {

    private static final int RSAKEYSIZE = 1024;

    public byte[][] encrypt(PublicKey publicKey, byte[] message) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        byte[] textoCifrado = null;
        byte[] chaveCifrada = null;

        //-- A) Gerando uma chave simétrica de 128 bits  
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey sk = kg.generateKey();
        byte[] chave = sk.getEncoded();
        //-- B) Cifrando o texto com a chave simétrica gerada  
        Cipher aescf = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivspec = new IvParameterSpec(new byte[16]);
        aescf.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"), ivspec);
        textoCifrado = aescf.doFinal(message);
        //-- C) Cifrando a chave com a chave pública  
        Cipher rsacf = Cipher.getInstance("RSA");
        rsacf.init(Cipher.ENCRYPT_MODE, publicKey);
        chaveCifrada = rsacf.doFinal(chave);

        return new byte[][]{textoCifrado, chaveCifrada};
    }

    public byte[] decrypt(PrivateKey privateKey, byte[] messageEncrypted, byte[] keyEncrypted) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        byte[] textoDecifrado = null;

        //-- A) Decifrando a chave simétrica com a chave privada  
        Cipher rsacf = Cipher.getInstance("RSA");
        rsacf.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] chaveDecifrada = rsacf.doFinal(keyEncrypted);
        //-- B) Decifrando o texto com a chave simétrica decifrada  
        Cipher aescf = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivspec = new IvParameterSpec(new byte[16]);
        aescf.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chaveDecifrada, "AES"), ivspec);
        textoDecifrado = aescf.doFinal(messageEncrypted);

        return textoDecifrado;
    }

    public PublicKey loadPublicKey(File fPub) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fPub));
        PublicKey ret = (PublicKey) ois.readObject();
        ois.close();
        return ret;
    }

    public PrivateKey loadPrivateKey(File fPvk) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fPvk));
        PrivateKey ret = (PrivateKey) ois.readObject();
        ois.close();
        return ret;
    }

    public PublicKey loadPublicKey(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        PublicKey ret = (PublicKey) ois.readObject();
        ois.close();
        return ret;
    }

    public PrivateKey loadPrivateKey(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        PrivateKey ret = (PrivateKey) ois.readObject();
        ois.close();
        return ret;
    }

    public void generateKeys(File filePublicKey, File filePrivateKey)
            throws IOException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            CertificateException, KeyStoreException {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(new RSAKeyGenParameterSpec(RSAKEYSIZE, RSAKeyGenParameterSpec.F4));
        KeyPair kpr = kpg.generateKeyPair();
        PrivateKey priv = kpr.getPrivate();
        PublicKey pub = kpr.getPublic();

        //-- Gravando a chave pública em formato serializado  
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePublicKey));
        oos.writeObject(pub);
        oos.close();

        //-- Gravando a chave privada em formato serializado  
        //-- Não é a melhor forma (deveria ser guardada em um keystore, e protegida por senha),   
        //-- mas isto é só um exemplo  
        oos = new ObjectOutputStream(new FileOutputStream(filePrivateKey));
        oos.writeObject(priv);
        oos.close();

    }
}
