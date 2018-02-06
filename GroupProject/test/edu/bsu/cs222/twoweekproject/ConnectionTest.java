package edu.bsu.cs222.twoweekproject;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ConnectionTest {

    @Test
    public void testURLEncoderWithSpaces() {
       String urlString = "string with spaces";
        try {
            Assert.assertEquals("string+with+spaces", URLEncoder.encode(urlString, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSpecialCharacters() throws UnsupportedEncodingException {
        String urlString = "&*_#!";
        System.out.print(URLEncoder.encode(urlString, "UTF-8"));
        try {
            Assert.assertEquals("%26*_%23%21", URLEncoder.encode(urlString, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }




}
