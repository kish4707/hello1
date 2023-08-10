package com.hist.controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hist.vo.HistTestAnswer;
import com.hist.vo.HistStudyText;
import com.hist.vo.HistTestQuestion;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/hist")
public class HistController {

	@RequestMapping("")
	public String histStart(HttpServletRequest request, HttpServletResponse response) {
		
		// 변수 초기화
		String beginNum = "1";
		String studyNum = "0";
		String endNum = "33";
		
		// 자료 발신
		HttpSession session = request.getSession();
		session.setAttribute("beginNum", beginNum);
		session.setAttribute("studyNum", studyNum);
		session.setAttribute("endNum", endNum);
		
		return "histStart";
	}	

	// 선택
	@RequestMapping("/choice")
	public String histChoice(HttpServletRequest request) {
		
		String study = request.getParameter("study");
		String test = request.getParameter("test");
		
		if (study!=null) {
			
			// 변수 초기화
			String beginNum = "1";
			String studyNum = "0";
			String endNum = "33";
			return "redirect:study";
		}
		
		if (test!=null) {
			
			// 변수 초기화
			String beginNum = "1";
			String studyNum = "0";
			String endNum = "63";
			HttpSession session = request.getSession();
			session.setAttribute("endNum", endNum);
			return "histTestSet";
		}
		
		return "histStart";
	}

	// 학습
	@RequestMapping("/study")
	public String histStudy(HttpServletRequest request, HttpServletResponse response) {
		
		// 배열 호출
		HistStudyText hst = new HistStudyText();
		String[] histStudyText = hst.histStudyText();
		
		// 자료 수신
		HttpSession session = request.getSession();
		String beginNum1 = (String) session.getAttribute("beginNum");
		String studyNum1 = (String) session.getAttribute("studyNum");
		String endNum1 = (String) session.getAttribute("endNum");
		
		// 문자 -> 숫자
		int beginNum = Integer.parseInt(beginNum1);
		int studyNum = Integer.parseInt(studyNum1);
		int endNum = Integer.parseInt(endNum1);
		
		// 버튼 선택값 처리
		String begin = request.getParameter("begin");
		String back = request.getParameter("back");
		String go = request.getParameter("go");
		String end = request.getParameter("end");
		String main = request.getParameter("main");
		if (begin!=null) {studyNum=beginNum;}
		if (back!=null & studyNum>beginNum) {studyNum=studyNum-1;}
		if (go!=null & studyNum<endNum) {studyNum=studyNum+1;}
		if (end!=null) {studyNum=endNum;}
		if (main!=null) {
			session.setAttribute("beginNum", "1");
			session.setAttribute("studyNum", "0");
			session.setAttribute("endNum", "33");
			return "histStart";
		}		
		
		// 숫자 -> 문자
		studyNum1 = Integer.toString(studyNum);
		
		// 텍스트 처리
		String histStudyText1 = histStudyText[studyNum];
		
		// 자료 발신
		session.setAttribute("studyNum", studyNum1);
		session.setAttribute("histStudyText1", histStudyText1);
		
		return "histStudy";
	}	
	
	// 시험 설정
	@RequestMapping("/testSet")
	public String testSet(HttpServletRequest request, HttpServletResponse response) {

		// 자료 수신
		String setNum1 = request.getParameter("setNum");
		int setNum = Integer.parseInt(setNum1);
		String step = "go";
		int testNum = 63;
		
		// ArrayList 생성
		List<Integer> randomGroup = new ArrayList<>();
		randomGroup.clear();
		randomGroup.add(0);
		
		for (int i=1; i<=setNum; i++) {
			step = "go";
			int rd = (int) (Math.random()*testNum+1);
			
			// 난수와 배열값의 중복체크
			for (int j=1; j<i; j++) {
				if (rd==randomGroup.get(j)) {
					step = "back";
					i--;
				}
			}
			
			// 난수와 배열값의 중복없을 때 난수저장
			if (step=="go") {
				randomGroup.add(rd);
			}
			
		}		
		
		// 숫자 -> 문자
		String setNum2 = Integer.toString(setNum);
		
		// 자료 설정
		HttpSession session = request.getSession();
		session.setAttribute("count", "0");
		session.setAttribute("setNum", setNum2);
		session.setAttribute("randomGroup", randomGroup);
		session.setAttribute("randomNum", "0");
		session.setAttribute("text", null);
		session.setAttribute("score", "0");
		
		return "redirect:testAct";
		
	}

	// 시험 실시
	@RequestMapping("/testAct")
	public String histTest(HttpServletRequest request, HttpServletResponse response) {
		
		// 자료 수신
		HttpSession session = request.getSession();
		String count1 = (String) session.getAttribute("count");		
		String setNum1 = (String) session.getAttribute("setNum");
		List<Integer> randomGroup = (List<Integer>) session.getAttribute("randomGroup");				
		String randomNum1 = (String) session.getAttribute("randomNum");				
		String text1 = request.getParameter("text1");
		String score1 = (String) session.getAttribute("score");		
		
		// 문자 -> 숫자
		int count2 = Integer.parseInt(count1);
		int setNum = Integer.parseInt(setNum1);		
		int randomNum2 = Integer.parseInt(randomNum1);
		int score = Integer.parseInt(score1);
		
		// 정답 처리
		HistTestAnswer hta = new HistTestAnswer();
		String[] histTestAnswer = hta.histTestAnswer();
		
		if (histTestAnswer[randomNum2].equals(text1)) {
			score ++;
		}
		
		// 문항수 체크
		count2 ++;
		if (count2>setNum) {
			String score2 = Integer.toString(score);
			session.setAttribute("score", score2);
			return "redirect:testResult";
		}				
		
		// 배열 호출
		HistTestQuestion htq = new HistTestQuestion();
		String[] histTestQuestion = htq.histTestQuestion();
		
		// 텍스트 처리
		int randomNum3 = randomGroup.get(count2);
		String histTestQuestion1 = histTestQuestion[randomNum3];
		
		// 숫자 -> 문자
		String count = Integer.toString(count2);
		String randomNum = Integer.toString(randomNum3);
		String score2 = Integer.toString(score);
		
		// 자료 설정
		session.setAttribute("count", count);
		session.setAttribute("randomNum", randomNum);		
		session.setAttribute("histTestQuestion1", histTestQuestion1);	
		session.setAttribute("score", score2);		
		
		return "histTestAct";
		
	}		

	// 시험 결과
	@RequestMapping("/testResult")
	public String testResult(HttpServletRequest request, HttpServletResponse response) {
		
		// 자료 수신
		HttpSession session = request.getSession();
		String setNum1 = (String) session.getAttribute("setNum");
		String score1 = (String) session.getAttribute("score");
		double setNum2 = Integer.parseInt(setNum1);
		double score2 = Integer.parseInt(score1);
		
		// 점수 처리
		double result1 = (score2/setNum2)*100;
		int result2 = (int) result1;
		
		String result = Integer.toString(result2);
		session.setAttribute("result", result);
		
		return "histResult";
	}
	
}
