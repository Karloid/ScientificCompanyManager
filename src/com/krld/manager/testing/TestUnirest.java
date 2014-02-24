package com.krld.manager.testing;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Andrey on 2/24/14.
 */
public class TestUnirest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("unirest.prop"));

        System.out.println("Make request");
        HttpResponse<String> response = Unirest.get("https://yoda.p.mashape.com/yoda?sentence=You%20will%20learn%20how%20to%20speak%20like%20me%20someday.%20%20Oh%20wait.")
                .header("X-Mashape-Authorization", properties.getProperty("unirestKey"))
                .asString();
        System.out.println(response.getBody());
    }
}
