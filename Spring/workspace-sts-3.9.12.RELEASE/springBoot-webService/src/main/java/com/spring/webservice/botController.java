package com.spring.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.webservice.vo.botVO;
import com.spring.webservice.vo.loginVO;

@RestController
public class botController {
		private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
		
		@Autowired
		private SqlSession sqlSession;
		
		// BOT INFO ���
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/RegisterBotInfo", method = RequestMethod.POST)
		@ResponseBody
		public String registerBotInfo(Locale locale, Model model, @RequestBody botVO botVO, HttpServletRequest request) {
			System.out.println("## get Bot Info ##");
			System.out.println(botVO.getMember_token());
			String result = "";
			
			loginVO loginVO = new loginVO();
			loginVO.setMember_token(botVO.getMember_token());
			int tokenCount = sqlSession.selectOne("login.getTokenCount", loginVO);
			
			// �α���üũ
			if ( tokenCount > 0 ) {
				loginVO lvo = sqlSession.selectOne("login.getTokenInfo", loginVO);
				
				if (lvo.getMember_token().equals("")) {
					result = "100";
				} 
		
				String getToken = lvo.getMember_token();
				if (!loginVO.getMember_token().equals(getToken)) {
					result = "100";
				}
				
				botVO.setMember_num(lvo.getMember_num());
				
				int rst = sqlSession.insert("bot.InsertBot", botVO);
				
				if ( rst > 0) {
					result = "0";
				} else {
					result = "1";
				}
							
			} else {
				result = "100";
			}
			
			return result;
		}
		
		// BOT INFO ��������
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/getBotInfo", method = RequestMethod.POST)
		@ResponseBody
		public HashMap<String, String> getBotInfo(Locale locale, Model model, @RequestBody botVO botVO, HttpServletRequest request) {
			System.out.println("## botInfo Register ##");
			System.out.println(botVO.getMember_token());
			String result = "";
			
			loginVO loginVO = new loginVO();
			loginVO.setMember_token(botVO.getMember_token());
			int tokenCount = sqlSession.selectOne("login.getTokenCount", loginVO);
			
			HashMap<String, String> getBotInfoResult = new HashMap<String, String>();
			
			// �α���üũ
			if ( tokenCount > 0 ) {
				loginVO lvo = sqlSession.selectOne("login.getTokenInfo", loginVO);
				
				if (lvo.getMember_token().equals("")) {
					result = "100";
				} 
		
				String getToken = lvo.getMember_token();
				if (!loginVO.getMember_token().equals(getToken)) {
					result = "100";
				}
				
				botVO.setMember_num(lvo.getMember_num());
				botVO bvo = sqlSession.selectOne("bot.getBotInfo", botVO);
				
				
				if ( bvo == null ) {
					result = "1";
				} else {
					result = "0";
					getBotInfoResult.put("bot_token", bvo.getBot_token());
					getBotInfoResult.put("chat_id", bvo.getChat_id());
				}
							
				
			} else {
				result = "100";
			}
			
			getBotInfoResult.put("data", result);
			
			return getBotInfoResult;
		}
		
		// BOT INFO ����
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/ModifyBotInfo", method = RequestMethod.POST)
		@ResponseBody
		public String ModifyBotInfo(Locale locale, Model model, @RequestBody botVO botVO, HttpServletRequest request) {
			System.out.println("## Modify Bot Info ##");
			System.out.println(botVO.getMember_token());
			String result = "";
			
			loginVO loginVO = new loginVO();
			loginVO.setMember_token(botVO.getMember_token());
			int tokenCount = sqlSession.selectOne("login.getTokenCount", loginVO);
			
			// �α���üũ
			if ( tokenCount > 0 ) {
				loginVO lvo = sqlSession.selectOne("login.getTokenInfo", loginVO);
				
				if (lvo.getMember_token().equals("")) {
					result = "100";
				} 
		
				String getToken = lvo.getMember_token();
				if (!loginVO.getMember_token().equals(getToken)) {
					result = "100";
				}
				
				botVO.setMember_num(lvo.getMember_num());
				int rst = sqlSession.update("bot.ModifyBotInfo", botVO);
				
				
				if ( rst == 0 ) {
					result = "1";
				} else {
					result = "0";
				}
							
				
			} else {
				result = "100";
			}
			
			return result;
		}
}