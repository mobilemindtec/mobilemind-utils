package br.com.mobilemind.api.test;

import br.com.mobilemind.api.security.key.Base64;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

public class Base64Test {

    public static void main(String[] args) throws Exception{


        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        FileInputStream ios = new FileInputStream("/home/ricardo/Downloads/IMG_20170628_100516787.jpg");
        byte[] buffer = new byte[4096];
        int read = 0;

        while ((read = ios.read(buffer)) != -1) {
            bStream.write(buffer, 0, read);
        }

        buffer = null;
        ios.close();

        String content = Base64.encodeBytes(bStream.toByteArray(), Base64.NO_OPTIONS);

        
        FileWriter writer = new FileWriter("/home/ricardo/Downloads/base64Gzip.txt");
        writer.write(content);
        writer.flush();
        writer.close();
        

    }

}
