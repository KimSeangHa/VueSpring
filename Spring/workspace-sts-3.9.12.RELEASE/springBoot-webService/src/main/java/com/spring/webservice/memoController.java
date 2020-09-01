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

import com.spring.webservice.utill.Telegram;
import com.spring.webservice.utill.TelegramSend;
import com.spring.webservice.vo.loginVO;
import com.spring.webservice.vo.memoVO;

@RestController
public class memoController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	// 메모 등록
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/addMemo", method = RequestMethod.POST)
	@ResponseBody
	public String addMemo(Locale locale, Model model, @RequestBody memoVO memoVO, HttpServletRequest request) {
		System.out.println("## AddMemo ##");
		System.out.println(memoVO.getId());
		System.out.println(memoVO.getContent());
		System.out.println(memoVO.getMember_token());
		
		
		loginVO loginVO = new loginVO();
		loginVO.setMember_token(memoVO.getMember_token());
		int tokenCount = sqlSession.selectOne("login.getTokenCount", loginVO);
		String result = "";
		
		// 로그인체크
		if ( tokenCount > 0 ) {
			loginVO lvo = sqlSession.selectOne("login.getTokenInfo", loginVO);
			
			if (lvo.getMember_token().equals("")) {
				result = "100";
			} 
	
			String getToken = lvo.getMember_token();
			if (!loginVO.getMember_token().equals(getToken)) {
				result = "100";
			}
			
			memoVO.setMember_num(lvo.getMember_num());
			memoVO.setContent(memoVO.getContent().replace("\n", "%0A"));
			
			int rst = sqlSession.insert("memo.addMemo", memoVO);
			
			
			if ( rst > 0) {
				result = "0";
				TelegramSend.SendMessage(memoVO.getContent());
			} else {
				result = "1";
			}
		} else {
			result = "100";
		}
		
		return result;
	}
	
	// 메모 삭제
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/deleteMemo", method = RequestMethod.POST)
	@ResponseBody
	public String deleteMemo(Locale locale, Model model, @RequestBody memoVO memoVO) {
		System.out.println("## DeleteMemo ## ");
		System.out.println(memoVO.getId());
			
		int rst = sqlSession.delete("memo.deleteMemo", memoVO);
		String result = "";
		
		if ( rst > 0) {
			result = "0";
		} else {
			result = "1";
		}
		
		return result;
	}
	
	// 메모 수정
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/rewriteMemo", method = RequestMethod.POST)
	@ResponseBody
	public String rewriteMemo(Locale locale, Model model, @RequestBody memoVO memoVO) {
		System.out.println("## RewriteMemo ## ");
		System.out.println(memoVO.getId());
		System.out.println(memoVO.getModifyDate());
		
		memoVO.setContent(memoVO.getContent().replace("\n", "%0A"));
		int rst = sqlSession.update("memo.rewriteMemo", memoVO);
		String result = "";
		
		if ( rst > 0) {
			result = "0";
			TelegramSend.SendMessage(memoVO.getContent());
		} else {
			result = "1";
		}
		
		return result;
	}
	
	// 메모 리스트 조회
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/getMemoList", method = RequestMethod.POST)
	@ResponseBody
	public List<memoVO> getListMemo(Locale locale, Model model) {
		System.out.println("## getListMemo ## ");
		
		List<memoVO> getMemoList = sqlSession.selectList("memo.getMemoList");
		ArrayList<String> errorResult = new ArrayList<String>();
		
		if (getMemoList == null) {
			errorResult.add("ERROR");
		}
		
		return getMemoList;
	}
	
	// 텔레그램 봇 생성
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/Makebot", method = RequestMethod.POST)
	@ResponseBody
	public String Makebot(Locale locale, Model model, @RequestBody memoVO memoVO) {
		System.out.println("## Make Bot ## ");
		System.out.println(memoVO.getMember_token());
		
		loginVO loginVO = new loginVO();
		loginVO.setMember_token(memoVO.getMember_token());
		int tokenCount = sqlSession.selectOne("login.getTokenCount", loginVO);
		String result = "";
		
		// 로그인체크
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
			
			Telegram.makeBot();
			result = "0";
			return "0";
		} else {
			result = "100";
		}
		
		return result;
	}
}
