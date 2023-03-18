package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mem.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberDAO memberDAO;
	//MemberDAO 생성
	
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandel(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandel(request, response);
	}
	
	protected void doHandel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		List<MemberVo> memberList = memberDAO.listMember();
		//요청에 대한 회원정보 조회
		request.setAttribute("membersList", memberList);
		//조회한 회원정보를 request에 바인딩
		RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMembers.jsp");
		dispatch.forward(request, response);
		//컨트롤러에 표시하고자 하는 JSP로 포워딩
	}

}
