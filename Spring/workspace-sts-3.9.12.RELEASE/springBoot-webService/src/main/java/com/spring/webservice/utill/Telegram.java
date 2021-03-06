package com.spring.webservice.utill;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Telegram {
	
    public static void makeBot() {
    	ApiContextInitializer.init();
    	TelegramBotsApi botsApi = new TelegramBotsApi();
    	
        try {
        	botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }	
}
