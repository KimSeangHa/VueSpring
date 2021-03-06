package com.spring.webservice;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.webservice.vo.memoVO;
import com.spring.webservice.vo.testVO;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/dbtest", method = RequestMethod.GET)
	@ResponseBody
	public List<memoVO> dbtest(Locale locale, Model model) {
		List<memoVO> testSelect = sqlSession.selectList("test.selectTest");
			
		return testSelect;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/InsertMemo", method = RequestMethod.GET)
	@ResponseBody
	public String InsertMemo(@RequestParam String title, @RequestParam String content ) {
		System.out.println(title);
		System.out.println(content);
		System.out.println("#### DB GOGO");
		
		testVO tvo = new testVO();
		
		tvo.setTitle(title);
		tvo.setContent(content);
		
		int result = sqlSession.insert("test.insertMemo", tvo);
		
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}
}
