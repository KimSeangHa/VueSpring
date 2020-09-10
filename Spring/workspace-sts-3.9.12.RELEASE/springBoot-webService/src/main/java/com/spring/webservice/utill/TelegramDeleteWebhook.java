package com.spring.webservice.utill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramDeleteWebhook {
	
	private final static Logger logger = Logger.getGlobal();
	
    public static void deleteWebhook() {
        // 실행간격 지정(60초)
        int sleepSec = 60 ;
        // 주기적인 작업을 위한
        final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
         
        exec.scheduleAtFixedRate(new Runnable(){
            public void run(){
                try {
                    // deleteWebhook 호출
                	logger.info("####### Delete Webhook #######");
                    
                    BufferedReader in = null;
                    
                    try {
                        URL obj = new URL("https://api.telegram.org/bot1334891991:AAGkrXOd3oPP92tQzRclNY4nmxwuvMPIUqM/deleteWebhook"); // 호출할 url
                        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
             
                        con.setRequestMethod("GET");
             
                        in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                    
                        String line;
                        while((line = in.readLine()) != null) { // response를 차례대로 출력
                        	logger.info(line);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    } finally {
                        if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }

                    } 
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    // 에러 발생시 Executor를 중지시킨다
                    exec.shutdown() ;
                }
            }
        }, 0, sleepSec, TimeUnit.SECONDS);
    }	 
}
