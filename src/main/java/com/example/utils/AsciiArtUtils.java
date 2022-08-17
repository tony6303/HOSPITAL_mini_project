package com.example.utils;

import java.io.FileReader;
import java.io.IOException;

public class AsciiArtUtils {
    private AsciiArtUtils() {
    }

    public static void show(String fileName) {
    	String path = "src/main/resources/";
        try {
            FileReader reader = new FileReader(fileName);

            try {
                int ch;
                while((ch = reader.read()) != -1) {
                    System.out.print((char)ch);
                }

                System.out.println();
            } catch (Throwable var5) {
                try {
                    reader.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            reader.close();
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        }
    }
}

