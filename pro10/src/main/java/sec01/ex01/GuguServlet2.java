package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugu2")
public class GuguServlet2 extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String dan = request.getParameter("gugudan");
		// dan에 gugu.html에서 입력한 숫자 불러오기

		if(dan!= "0" && (dan.length()!=0)) {
			// 입력한 숫자가 빈칸이 아니거나 길이가 0이 아닐경우 true
			out.print("<html>");
			out.print("<body>");
			out.print(dan + "단 출력");
			out.print("<br>");
			
			for (int i=1; i<10; i++) {
				// i는 1부터 9까지 증가
				int result = Integer.parseInt(dan) * i;
				// result = 입력한 숫자 * i
				out.print(dan + " x " + i + " = " + result);
				// 입력한 숫자 x i = result
				out.print("<br>");
			}
			out.print("</body>");
			out.print("</html>");
		} else {
			// 입력한 숫자가 빈칸이거나 길이가 0일 경우 false
			out.print("<html>");
			out.print("<body>");
			out.print("단을 입력하세요!!!");
			out.print("<br>");
			out.print("<a href='http://localhost:8080/pro01/test01/gugu.html'> 처음으로 </a>");
			// 
			out.print("</body>");
			out.print("</html>");
		}
	}
	
	
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}


}
