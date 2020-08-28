package com.spring.webservice.utill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TelegramSend {
	public static void SendMessage(String Content){
		
		System.out.println("@@@@@@@@@@");
		System.out.println(Content);
	
	    String BOT_NAME = "Kim_Scheduler_Bot"; //Bot Name
	    String AUTH_KEY = "1334891991:AAGkrXOd3oPP92tQzRclNY4nmxwuvMPIUqM"; //Bot Auth-Key
	    String CHAT_ID = "-381510796";
	    // String CHAT_ID = "-381510796"; //Chat ID 616043197
	    String TEXT = "To-do Register Success ! %0ASend a message to the bot.";
	    /* Content.replace("\n", "%0A"); */
	   
	    String sendURL = "https://api.telegram.org/bot" 
	    					+ AUTH_KEY 
	    					+ "/sendmessage?chat_id=" + CHAT_ID 
	    					+ "&text=" + TEXT;
	   
		BufferedReader in = null; 
		try { 
			URL obj = new URL(sendURL); // ȣ���� url 
			HttpURLConnection con = (HttpURLConnection)obj.openConnection(); 
			
			con.setRequestMethod("GET"); 
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
			
			String line; 
			while((line = in.readLine()) != null) { // response�� ���ʴ�� ��� 
				System.out.println(line); 
			} 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally { 
			if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); } 
		}
	}
}