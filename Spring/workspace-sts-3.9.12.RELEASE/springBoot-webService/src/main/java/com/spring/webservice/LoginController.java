package com.spring.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.spring.webservice.utill.MD5Utill;
import com.spring.webservice.utill.MakeToken;
import com.spring.webservice.vo.loginVO;

@RestController
public class LoginController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	// 회원 가입
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/InsertMember", method = RequestMethod.POST)
	@ResponseBody
	public String InsertMember(Locale locale, Model model, @RequestBody loginVO loginVO, HttpServletRequest request) {
		logger.info("## InsertMember ##");
		logger.info(loginVO.getMember_id());
		logger.info(loginVO.getMember_name());

		String password = loginVO.getMember_password();
		password = MD5Utill.encryptMD5(password);
		
		loginVO.setMember_password(password);
		
		int rst = sqlSession.insert("login.InsertMember", loginVO);
		String result = "";
		
		if ( rst > 0) {
			result = "0";
		} else {
			result = "1";
		}
	
		return result;
	}
	
	// ID 중복체크
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/MemberChk", method = RequestMethod.POST)
	@ResponseBody
	public String MemberChk(Locale locale, Model model, @RequestBody loginVO loginVO, HttpServletRequest request) {
		logger.info("## MemberChk ##");
		logger.info(loginVO.getMember_id());
		
		int rst = sqlSession.selectOne("login.MemberChk", loginVO);
		String result = "";
		
		if ( rst == 0) {
			result = "0";
		} else {
			result = "1";
		}
	
		return result;
	}
	
	// 로그인
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	@ResponseBody
	public List<String> LoginChk(Locale locale, Model model, @RequestBody loginVO loginVO, HttpServletRequest request) {
		logger.info("## Login ##");
		logger.info(loginVO.getMember_id());
		
		String password = loginVO.getMember_password();
		password = MD5Utill.encryptMD5(password);
		
		loginVO.setMember_password(password);
		
		int rst = sqlSession.selectOne("login.loginChk", loginVO);
		ArrayList<String> LoginResultInfo = new ArrayList<String>();
		
		String result = "";
		String token = MakeToken.NewToken(100);
		
		if ( rst > 0) {
			
			loginVO.setMember_token(token);
			int updateToken = sqlSession.update("login.setMemberToken", loginVO);
			
			if (updateToken > 0) {
				result = "0";
				LoginResultInfo.add(result);
				LoginResultInfo.add(token);
			
			} else {
				result = "1";
				LoginResultInfo.add(result);
			}
		} else {
			result = "1";
			LoginResultInfo.add(result);
		}
		return LoginResultInfo;
	}
	
	// 로그아웃
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	@ResponseBody
	public String Logout(Locale locale, Model model, @RequestBody loginVO loginVO, HttpServletRequest request) {
		logger.info("## Logout ##");
		logger.info(loginVO.getMember_token());
		String result = "";
		
		if ( loginVO.getMember_token() == null || loginVO.getMember_token() == "") {
			result = "100";
			return result;
		}
		
		int tokenCount = sqlSession.selectOne("login.getTokenCount", loginVO);
		
		if ( tokenCount > 0 ) {
			loginVO lvo = sqlSession.selectOne("login.getTokenInfo", loginVO);
			
			if (lvo.getMember_token().equals("")) {
				result = "100";
				return result;
			} 
	
			String getToken = lvo.getMember_token();
			if (!loginVO.getMember_token().equals(getToken)) {
				result = "100";
				return result;
			}
			
			loginVO.setMember_num(lvo.getMember_num());
			int TokenReset = sqlSession.update("login.TokenReset", loginVO);
			
			if ( TokenReset > 0 ) {
				result = "0";
			} else {
				result = "1";
			}
		} else {
			result = "100";
		}
		
		return result;
	}
}
