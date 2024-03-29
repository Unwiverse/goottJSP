package com.reply.controller;

import java.io.FileInputStream;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.action.Action;
import com.reply.action.ActionForward;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID =1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//getRequestURI(): "/프로젝트명/파일명(*.go)"라는 문자열을 반환해주는 메서드
		System.out.println("========================");
		String uri = request.getRequestURI();
		System.out.println("1: "+uri);
		
		//현재 프로젝트명을 문자열로 반환해주는 메서드
		String path = request.getContextPath();
		System.out.println("2: "+path);
		
		String command = uri.substring(path.length()+1);
		System.out.println("3: "+command);
		
		Action action = null;
		ActionForward forward = null;
		
		Properties prop = new Properties();
	
		/*
		 * properties 클래스
		 * - java.util 패키지에 있는 클래스
		 * 
		 * - properties 클래스는 Hashtable의 하위(자식) 클래스임.
		 *   보통은 환경변수 및 속성 값을 Properties 파일에 저장해
		 *   쉽게 접근할 수 있는 장점이 있음.
		 *   Properties 파일은 일련의 키(key) - 값(value)의
		 *   한 쌍으로 이뤄져 있음.
		 * - 보통은 파일에 저장하며 씀. 파일 이름을 *.properties라는 이름으로 끝나게함.
		 * 
		 * - FileInputStream 클래스에 properties 파일을 
		 *   인자로 넣어서 그 스트림으로부터 파일을 가져올 때 많이 씀. 
		 *   인자로 들어온 properties 파일을 읽게됨
		 * - 읽어들일 때 쓰는 메서드는 load()라는 메서드를 써서 파일을 읽어들이게됨.
		 * 
		 */
		
		 FileInputStream fis = new FileInputStream("C:\\Users\\goott3\\git\\goottJSP\\11_Board_Reply\\src\\main\\java\\com\\reply\\controller\\mapping.properties");
		 prop.load(fis);
		 String value = prop.getProperty(command);
		 System.out.println("value: "+value);
		 
		 if(value.substring(0, 7).equals("execute")) {
			 StringTokenizer st = new StringTokenizer(value, "|");
			 
			 String url_1 = st.nextToken(); // "excute" 문자열
			 System.out.println("4: "+url_1);
			 String url_2 = st.nextToken(); // "패키지명.클래스" 문자열
			 System.out.println("5: "+url_2);
			
			 try {
				Class<?> url = Class.forName(url_2);
				// url.newInstance();
				/*
				 * 동적 객체 생성 : newInstance()
				 * - Class 객체를 이용하면 new 연산자의 사용 없이 동적으로
				 *   객체 생성이 가능함.
				 * - 코드 작성 시에 클래스의 이름을 결정할 수 없고, 런타임(실행)
				 *   시에 클래스의 이름이 결정되는 경우에 유용하게 사용이 됨.
				 * 
				 * - newInstance() 메서드는 기본생성자를 통하여 객체를 생성하기
				 *   때문에 반드시 클래스에 기본생성자가 존재하여야 함.
				 *   예외가 발생할 수 있는데 해당 클래스가 추상클래스이거나 인터페이스일
				 *   경우 발생하고, 또 하나의 예외는 클래스의 생성자가 접근제한자로 인해
				 *   접근할 수 없는 경우에 발생을 함. 따라서 예외 처리를 해 주어야 함.
				 *   
				 * - 반환 타입은 Object 타입이므로 맞게 형변환을 해 주면 되지만, 
				 *   클래스의 타입을 모르는 상태이므로 형변환을 해 줄 수가 없음.
				 *   이러한 문제를 해결하기 위해서 인터페이스를 사용하는 것임.
				 *   
				 * - Class.forName(class 이름)은 파라미터로 받은 class 이름에
				 *   해당하는 클래스를 로딩한 후에 그 클래스에 해당하는 인스턴스를
				 *   리턴을 해 줌.
				 *   newInstance() 메서드는 로딩한 클래스의 객체를 생성하는
				 *   메서드이고, 이렇게 생성된 객체를 동적 객체 생성이라고 함.
				 */
				
				 // 동적으로 로딩된 클래스(객체)의 생성자 (기본생성자)를 가져오는 메서드
				 Constructor<?> constructor = url.getConstructor();
				 
				 //가져온 기본생성자를 써서 newInstance() 메서드 호출해서 객체를 생성하는 메서드
				 action = (Action)constructor.newInstance(); 
				 //newInstance 메서드 사용을 위해 Action 인터페이스로 형변환
				 //Constructor 클래스 제네릭 타입이 ?이기 때문에
				 
				 //비지니스 로직을 실행하는 메서드 호출
				 forward = action.execute(request, response);
				 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} else {
					 //value에 들어온 값이 "excute|...."가 아닐 때
					 forward = new ActionForward();
					 forward.setRedirect(false);
					 forward.setPath(value);
				} 
		 
		 	 if(forward != null) {
				 if(forward.isRedirect()) { //true인 경우
					 response.sendRedirect(forward.getPath());
				 } else { //false인 경우
					 //view 페이지로 이동
					 request.getRequestDispatcher(forward.getPath())
					 	.forward(request, response);
				 }
			 }
			
		}

}
