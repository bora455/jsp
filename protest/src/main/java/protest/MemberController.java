package protest;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/boad/*")
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
	
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}

	public void destroy() {
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/htmlcharset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action: " + action);
		
		if(action == null || action.equals("/studentlists")) {
			List<MemberVO> membersList = memberDAO.listMember();
			request.setAttribute("membersList", membersList);
			nextPage = "/boad/listMembers.jsp";
		} else if (action.equals("/addstudent")) {
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String univ = request.getParameter("univ");
			String birth = request.getParameter("birth");
			String email = request.getParameter("email");
			MemberVO memberVO  = new MemberVO(id, username, univ, birth, email);
			memberDAO.addMember(memberVO);
			request.setAttribute("msg", "addMember");
			nextPage = "/boad/studentlists";
		} else if (action.equals("/memberForm.do")) {
			nextPage = "/boad/memberForm.jsp";
		} else {
			List<MemberVO> membersList = memberDAO.listMember();
			request.setAttribute("membersList", membersList);
			nextPage = "/boad/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
