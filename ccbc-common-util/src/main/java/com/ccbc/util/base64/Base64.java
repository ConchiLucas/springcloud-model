package com.ccbc.util.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class Base64 {
    private static final Base64Encoder encoder = new Base64Encoder();

    public static byte[] encode(byte[] data) {
        int len = (data.length + 2) / 3 * 4;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(len);
        try {
            encoder.encode(data, 0, data.length, outputStream);
        } catch (IOException e) {
            throw new RuntimeException("exception encoding base64 string: " + e);
        }

        return outputStream.toByteArray();
    }

    public static int encode(byte[] data, OutputStream out) throws IOException {
        return encoder.encode(data, 0, data.length, out);
    }

    public static int encode(byte[] data, int off, int length, OutputStream out) throws IOException {
        return encoder.encode(data, off, length, out);
    }

    public static byte[] decode(byte[] data) {
        int len = data.length / 4 * 3;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream(len);
        try {
            encoder.decode(data, 0, data.length, bOut);
        } catch (IOException e) {
            throw new RuntimeException("exception decoding base64 string: " + e);
        }

        return bOut.toByteArray();
    }

    public static byte[] decode(String data) {
        int len = data.length() / 4 * 3;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream(len);
        try {
            encoder.decode(data, bOut);
        } catch (IOException e) {
            throw new RuntimeException("exception decoding base64 string: " + e);
        }

        return bOut.toByteArray();
    }

    public static int decode(String data, OutputStream out) throws IOException {
        return encoder.decode(data, out);
    }

    public static String encode(String data, String charSet) throws UnsupportedEncodingException {
        if ((data == null) || (data.length() == 0)) {
            return "";
        }
        return new String(encode(data.getBytes(charSet)), charSet);
    }

    public static String encode(byte[] data, String charSet) throws UnsupportedEncodingException {
        return new String(encode(data), charSet);
    }

    public static String decode(String data, String charSet) throws UnsupportedEncodingException {
        if ((data == null) || (data.length() == 0)) {
            return "";
        }
        return new String(decode(data.getBytes(charSet)), charSet);
    }
}