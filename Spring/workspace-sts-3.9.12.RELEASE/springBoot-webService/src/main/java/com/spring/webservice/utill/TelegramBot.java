package com.spring.webservice.utill;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.spring.webservice.vo.botVO;
import com.spring.webservice.vo.loginVO;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramBot extends TelegramLongPollingBot {
	
	private final static Logger logger = Logger.getGlobal();
	
   	@Autowired
	private SqlSession sqlSession;
   	
    private final String BOT_NAME = "Kim_Scheduler_Bot"; //Bot Name
    private final String AUTH_KEY = "1334891991:AAGkrXOd3oPP92tQzRclNY4nmxwuvMPIUqM"; //Bot Auth-Key
    // private final String CHAT_ID = "-381510796";
    //private final String CHAT_ID = "-381510796"; //Chat ID 616043197
    
    @Override 
   public void onUpdateReceived(Update update) {
    	
    	System.out.println(update);
    	logger.info(update.getMessage().getText());
    	
    	String getOriginText = update.getMessage().getText();
    	String getText = getOriginText.replaceAll("/", "");
    	
    	SendMessage message = new SendMessage();
    	long CHAT_ID = update.getMessage().getChatId();
    	
    	message.setChatId(CHAT_ID);
    	message.setText("스케줄러 봇이 곰곰이 생각중입니다. \n잠시만 기다려주세요.");
    	
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
    	
		
		String Func = getText.substring(0, 3);
		String helper = getText.substring(0, 4);
		
		//등록하기 
		if ( Func.equals("REG")) {
			logger.info("Register Bot Info");
			String[] InsertData = getText.split("#");
			String MEMBER_ID = InsertData[1];
		
			int InsertResult = InsertBotInfo(MEMBER_ID, CHAT_ID);
			
			if ( InsertResult == 1 ) {
				message.setChatId(CHAT_ID);
	        	message.setText("채팅방 아이디가 등록되었습니다.");
	        	
	    		try {
	    			execute(message);
		    		return;
	    		} catch (TelegramApiException e) {
	    			e.printStackTrace();
	    		}
	    		
			} else {
				message.setChatId(CHAT_ID);
	        	message.setText("채팅방 아이디 등록 중 오류가 발생하였습니다. \n존재하지 않는 아이디거나 오류입니다. 관리자에게 문의바랍니다.");
	        	
	    		try {
	    			execute(message);
	    			return;
	    		} catch (TelegramApiException e) {
	    			e.printStackTrace();
	    		}
			}
		} 
		
		// Helper
		if ( helper.equals("help")) {
			message.setChatId(CHAT_ID);
        	message.setText("안녕하세요.\n여러분의 스케줄을 도와줄 스케줄러 봇입니다. \n하루에 아침, 점심, 저녁으로 총 3번 등록하신 일정을 보내드리고 있습니다. "
        			+ "\n그 외에도 저에게 오늘의 날짜(/2020903) 이렇게 메세지를 보내주시면 바쁜 여러분의 일정을 보내드립니다."
        			+ "\n\n그 외의 명령어는 아래와 같습니다."
        			+ "\n/REG#id를 입력하시면 채팅방 아이디를 등록할 수 있어요. \n그러나 이것은 관리자와 꼭 함께해주세요.\n한 계정에서 하나의 채팅방만 설정할 수 있습니다.\n꼬여버리면 제가 머리가 너무 아프거든요."
        			+ "\n\n제가 아직 어려서 머리가 나빠요. "
        			+ "\n생각하는 시간이 조금 걸리더라도 이해해주세요."
        			+ "\n그럼 이만 !");
        	
        	try {
    			execute(message);
    			return;
    		} catch (TelegramApiException e) {
    			e.printStackTrace();
    		}
		}
		
		// MEMBER_NUM 가져오기
    	int MEMBER_NUM = getMEMBER_NUM(CHAT_ID);
    	
    	if ( getText.length() != 8 ) {
        	message.setChatId(CHAT_ID);
        	message.setText("날짜를 잘못입력하였습니다. \n20200823 형태로 입력해주세요.");
        	
    		try {
    			execute(message);
    		} catch (TelegramApiException e) {
    			e.printStackTrace();
    		}
    		
    	} else {
    		String date = getText.substring(0,4) + "-" + getText.substring(4,6) + "-" + getText.substring(6,8) + "%";
    	
	    	/* DB Connection */
	    	String sqlHost = "jdbc:mysql://localhost:3306/webservice?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
	    	String user = "root";
	    	String pw = "root";
	    	
	    	try {
	          String myDriver = "com.mysql.cj.jdbc.Driver";
	          Class.forName(myDriver);
	          Connection conn = DriverManager.getConnection(sqlHost, user, pw);
	          
	          String query = "SELECT "
          						+ "REPLACE(CONTENT, \"%0A\", \"\\n\") AS CONTENT "
          						+ ", ID "
          						+ "FROM MEMO "
          						+ "WHERE REGDATE LIKE " + "\"" + date + "\""
          						+ "AND MEMBER_NUM =" + MEMBER_NUM;
	
	          Statement st = conn.createStatement();
	          
	          ResultSet rs = st.executeQuery(query);
	          
	          logger.info("SEND MESSAGE");
	          logger.info(query);
	          
	          if(rs.isBeforeFirst()) {
		          while (rs.next()) {
		            String Content = rs.getString("CONTENT");
		            String id = rs.getString("ID");
		            
		            System.out.format(Content, id);
		    		
		            message.setText(Content);
		    		message.setChatId(CHAT_ID);
		    		
		    		try {
		    			execute(message);
		    		} catch (TelegramApiException e) {
		    			e.printStackTrace();
		    		}
		          }
	          } else {
	            message.setText("등록된 스케줄이 없습니다. \n스케줄을 등록해주세요.");
	    		message.setChatId(CHAT_ID);
	    		
	    		try {
	    			execute(message);
	    		} catch (TelegramApiException e) {
	    			e.printStackTrace();
	    		}
	          }
	          
	          st.close();
	        } catch (Exception e) {
	          System.err.println("Got an exception! ");
	          System.err.println(e.getMessage());
	        }
    	}
   }

   @Override 
   public String getBotUsername() {
        // TODO 
       return BOT_NAME;
   }

   @Override 
   public String getBotToken() {
        // TODO 
       return  AUTH_KEY;
   }
   
   // ChatID를 통한 MEMBER_NUM 가져오기
   public int getMEMBER_NUM(long CHAT_ID) {
	   
	/* DB Connection */
	String sqlHost = "jdbc:mysql://localhost:3306/webservice?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
	String user = "root";
	String pw = "root";
	
	try {
	    String myDriver = "com.mysql.cj.jdbc.Driver";
	    Class.forName(myDriver);
	    Connection conn = DriverManager.getConnection(sqlHost, user, pw);
	 
		String query = "SELECT "
			+ "MEMBER_NUM "
			+ "FROM BOT_INFO "
			+ "WHERE CHAT_ID =" + CHAT_ID;
	
	 	Statement st = conn.createStatement();
	 
	 	ResultSet rs = st.executeQuery(query);
		 
		 if(rs.isBeforeFirst()) {
		      while (rs.next()) {
		        int MEMBER_NUM = rs.getInt("MEMBER_NUM");
		        
		        System.out.println(MEMBER_NUM);
		        return MEMBER_NUM;

		      }
		 } else {
		   int MEMBER_NUM = 0;
		   return MEMBER_NUM;
	     }
	     
	     st.close();
	   } catch (Exception e) {
	     System.err.println("Got an exception! ");
	     System.err.println(e.getMessage());
       }
	  return 0;
   }
   
   // Insert Data
   public int InsertBotInfo(String MEMBER_ID, long CHAT_ID) {
	  
	   SimpleDateFormat dateformat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	   
	   Date time = new Date();
	   String CurrentTime = dateformat.format(time);
	   
	   logger.info("###" + CurrentTime);
	   
		/* DB Connection */
		String sqlHost = "jdbc:mysql://localhost:3306/webservice?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String pw = "root";
		
		try {
		    String myDriver = "com.mysql.cj.jdbc.Driver";
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(sqlHost, user, pw);
		    
			String query = "SELECT "
				+ "MEMBER_NUM "
				+ "FROM MEMBER "
				+ "WHERE MEMBER_ID=" + "\"" + MEMBER_ID + "\"";
		
		 	Statement st = conn.createStatement();
		 	ResultSet rs = st.executeQuery(query);
			 
		 	logger.info("InsertBotInfo - Select");
		 	logger.info(query);
			 
			 if(rs.isBeforeFirst()) {
			      while (rs.next()) {
			        int MEMBER_NUM = rs.getInt("MEMBER_NUM");
			        
			        String InsertQuery = "INSERT INTO BOT_INFO (MEMBER_NUM, CHAT_ID, BOT_REGDATE)"
			        		+ "VALUES ("
			        		+ MEMBER_NUM 
			        		+ "," + "\"" + CHAT_ID + "\""
			    			+ "," + "\"" + CurrentTime + "\""
			    			+ ")";
			        
		    	 	int InsertResult = st.executeUpdate(InsertQuery);
		    	 	
		    	 	logger.info("InsertBotInfo - Insert");
		    	 	logger.info(InsertQuery);
					
			        return InsertResult;
	
			      }
			 } else {
			   return 0;
		     }
		     
			 st.close();
	   } catch (Exception e) {
	     System.err.println("Got an exception! ");
	     System.err.println(e.getMessage());
       }
	  return 0;
   }
}
