package goott;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * - 서블릿은 자바로 만들어 졌으므로 당연히 클래스들 간의 계층 구조를 
 *   가짐.
 * 
 *      Servlet              ServletConfig
 *         ↑                      ↑
 *         |                      |
 *         --- GenericServlet  --- 
 *                   ↑
 *                   |
 *                HttpServlet
 *                
 * - 서블릿 API는 Servlet과 ServletConfig 인터페이스를 구현해 
 *   제공함. GenericServlet 추상 클래스가 이 두 인터페이스의 
 *   추상 메서드를 구현.
 *   그리고 이 GenericServlet을 다시 HttpServlet 클래스가 
 *   상속을 받음.
 *   
 * - 서블릿 API 를 구성하는 각 객체들의 특징.
 *   1. Servlet 인터페이스
 *      - javax.servlet 패키지에 선언이 되어 있음.
 *      - Servlet 관련 추상 메서드를 제공함.
 *      - init(), service(), destroy(), 
 *        getServletInfo(), getServletConfig() 
 *        추상 메서드가 선언되어 있음.
 *   
 *   2. ServletConfig 인터페이스
 *      - javax.servlet 패키지에 선언이 되어 있음.
 *      - Servlet 관련 추상 메서드를 제공함.
 *      - getInitParameter(), getInitParameterNames(),
 *        getServletContext(), getServletName() 
 *        추상 메서드가 선언되어 있음.
 *        
 *   3. GenericServlet 추상 클래스.
 *      - javax.servlet 패키지에 선언이 되어 있음.
 *      - 상위 두 인터페이스를 구현하여 일반적인 서블릿 기능을 구현한 
 *        클래스.
 *      - GenericServlet을 상속을 받아 구현한 사용자 서블릿은 
 *        사용되는 프로토콜에 따라 service() 메서드를 오버라이딩 
 *        하여 구현함.
 *        
 *   4. HttpServlet 클래스
 *      - javax.servlet 패키지에 선언이 되어 있음.
 *      - GenericServlet 을 상속받아 HTTP 프로토콜을 사용하는 
 *        웹 브라우저에서 서블릿 기능을 수행함.
 *      - 웹 브라우저 기반 서비스를 제공하는 서블릿을 만들 때 상속받아 
 *        사용함.
 *      - 요청 시 service() 가 호출되면 요청 방식에 따라 
 *        doGet()나 doPost() 메서드가 차례로 호출이 됨. 
 */

/*
 * 서블릿 구동 절차
 * 1. 클라이언트의 요청이 들어오면 서블릿 컨테이너는 해당 서블릿을 찾음.
 * 
 * 2. 만약 서블릿이 없다면, 서블릿 클래스를 로딩하고, 인스터스를 준비 후에
 *    해당 서블릿의 생성자를 호출하여 인스턴스를 생성하고, 서블릿 초기화
 *    메서드인 init() 메서드를 호출함.
 *    
 * 3. 그 후에 클라이언트 요청을 처리하는 service() 메서드를 호출함.
 *    메서드의 이름을 보면 용도를 알 수 있듯이 클라이언트의 요청에 대해
 *    서비스를 제공하겠다는 의미를 가지고 있음.
 *    
 * 4. service() 메서드에서 만든 결과를 HTTP 프로토콜에 맞추어 클라이언트에
 *    응답하는 것으로 요청 처리를 완료함.
 *    
 * 5. 만약 시스템 운영자가 서블릿 컨테이너를 종료하거나, 웹 애플리케이션을
 *    종료한다면
 *    
 * 6. 서블릿 컨테이너는 종료되기 전에 마무리 작업을 할 수 있도록
 *    생성된 모든 서블릿에 대해 destroy() 메서드를 호출함.
 *    
 * 서블릿의 생명주기(Life Cycle)
 * 1. init()
 *    - 서블릿 컨테이너가 서블릿을 생성한 후 초기화 작업을 수행하기 위해서
 *      호출되는 메서드임. 서블릿이 클라이언트의 요청을 처리하기 전에 준비할
 *      작업이 있다면 이 메서드에 작성을 하면 됨.
 *      
 * 2. service()
 *    - 클라이언트가 요청할 때 마다 호출되는 메서드임. 실질적으로 서비스 작업을
 *      수행하는 메서드임. 바로 이 메서드에 해야 할 일을 작성하면 됨.
 *      요청에서 method 방식에 따라서 doGet() 메서드나 doPost() 메서드를
 *      호출하여 비지니스 로직을 수행함.
 *      
 * 4. destroy()
 *    - 서블릿 컨테이너가 종료되거나 웹 애플리케이션이 종료될 때 호출이 되는
 *      메서드임. 따라서 이 메서드에는 서비스 수행을 위해 확보되었던 자원을 
 *      해제한다거나 데이터를 저장하는 등의 마무리 작업을 진행함.
 *  * - 서버(서블릿)에서 웹 브라우저로 데이터를 전송할 때에는 어떤 
 *   종류의 데이터를 전송하는지 웹 브라우저에게 알려 주어야 함.
 *   => 이유 : 웹 브라우저가 전송 받을 데이터의 종류를 미리 알고
 *            있으면 더 빠르게 처리할 수 있기 때문임.
 * - 데이터 종류(MIME-TYPE) : 톰캣 컨테이너에서 미리 제공하는 
 *                         여러 가지 전송 데이터 종류를 
 *                         하나 지정하여 웹 브라우저로 
 *                         전송.
 *                         이처럼 톰캣 컨테이네에서 미리 
 *                         설정해 놓은 데이터의 종류들을 
 *                         말함. 웹 브라우저는 기본적으로 
 *                         HTML만 인식하므로 서블릿에서 
 *                         전송하는 대부분의 데이터는
 *                         MIME-TYPE을 "text/html"
 *                         로 설정을 함.
 * - 서블릿이 클라이언트(웹 브라우저)에 응답하는 과정.
 *   * setContentType()을 이용해서 MIME-TYPE을 지정함.
 *   * 데이터를 출력할 PrintWriter 객체를 생성함.
 *   * 출력 데이터를 HTML 형식으로 만듬.
 *   * PrintWriter 객체의 print()나 println() 메서드를 
 *     이용해 데이터를 출력함.
 * 
 * 
 * 웹 브라우저에서 서블릿으로 데이터를 전송하는 전송 방식(2가지)
 * 1. get 방식
 *    - 서블릿에 데이터를 전송할 때는 데이터가 url 뒤에 
 *      name=value 형태로 전송이 됨.
 *    - 여러 개의 데이터를 전송할 때는 '&'로 구분하여 전송이 됨.
 *    - 보안이 취약함.
 *    - 전송할 수 있는 데이터는 최대 255자.
 *    - 기본 전송 방식이고 사용이 쉬움.
 *    - 웹 브라우저에 직접 입력해서 전송할 수도 있음.
 *    - 서블릿에서는 doGet() 메서드에서 전송된 데이터를 처리함.
 *    - get 방식의 요청은 자료를 검색한다거나, 게시글의 상세정보를
 *      본다거나, 특정 상품의 정보를 조회하는 것과 같이 데이터를
 *      조회하는 경우에 적합함.
 *     
 * 2. post 방식
 *    - 서블릿에 데이터를 전송할 때는 TCP/IP 프로토콜 데이터의 
 *      head 영역에 숨겨진 채 전송이 됨.
 *    - 보안에 유리함.
 *    - 전송 데이터의 용량이 무제한임.
 *    - 처리 속도가 get 방식보다 느림.
 *    - 서블릿에서는 doPost() 메서드에서 전송된 데이터를 처리함.
 */


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// form 태그에 method="get"이라고 적혀있는 경우나
		// method 속성이 안적힌 때는 doGet()메서드에서 처리.
		// 로그인 홈페이지에서 넘어온 정보를 받아주면 됨.
		/*
		 	request 매개변수 
		 	- 사용자(클라이언트)의 요청에 대한 정보를 처리.
		 	- 클라이언트로부터 Servlet으로 요청이 들어오면 
		 	  요청 파라미터라는게 같이 오게 되는데 이거에 대한
		 	  분석은 request.getParameter()라는 메서드를
		 	  이용해 파악하게됨.
		 	  
		 	response 매개변수
		 * - 사용자의 요청 정보에 대한 처리 결과를 클라이언트에
		 *   응답하여 처리.
		 * - 요청을 파악했다면 클라이언트로 내보낼 응답을 작성해야 함.
		 *   대부분의 웹 프로그래밍은 응답을 텍스트로 작성하며, 이 텍스트는
		 *   대부분은 HTML 페이지의 모양을 하고 있게 됨.
		 * - 여기서의 응답은 텍스트를 기록해야 하는데, 이 때 스트림이라는
		 *   개념을 이용하여 기록을 하게 됨. 말 그대로 데이터의 흐름이라고
		 *   생각하면 됨.
		 *   Servlet에서는 클라이언트 쪽으로 보내는 데이터의 흐름을
		 *   건드려야 할 필요가 있게 됨.
		 * - response 인자를 활용하면 응답과 관련된 많은 작업들을
		 *   할 수 있음. 응답 스트림에 텍스트를 기록하는 것도 가능함.
		 *   이 작업을 하기 위해서는 response.getWriter() 라는
		 *   메서드를 사용해야 함. 해당 메서들 이용하여 응답으로 내보낼
		 *   출력 스트림을 얻어낸 후에 출력 스트림에 텍스트를 기록하면 됨. 
		 */
		 String userId = request.getParameter("id");
		 String userPwd = request.getParameter("pwd");
		 System.out.println("입력받은 아이디: "+userId);
		 System.out.println("입력받은 비번: "+userPwd);
		 
		 //응답은 response 매개변수를 쓰면 됨.
		 //클라이언트 응답 시 한글을 작성하면 깨짐(쓰면안됨)
		 // 이를 해결해야됨.
		 response.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 
		 out.println("<html");
		 out.println("<head></head>");
		 out.println("<body>");
		 out.println("<h2>");
		 
		out.println("입력한 아이디: "+userId+"<br>");
		out.println("입력한 비번: "+userId+"<br>");
		
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
