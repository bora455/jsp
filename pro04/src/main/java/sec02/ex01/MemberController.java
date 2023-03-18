package sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member151/*") //브라우저에서 요청시 두단계로 요청이 이루어짐
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberDAO memberDAO;
	
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
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/htmlcharset=utf-8");
		String action = request.getPathInfo(); // URL에서 요청명을 가져옴
		System.out.println("action: " + action);
		
		if(action == null || action.equals("/listMembers.do")) {
		// 최초 요청이거나 action 값이 /memberList.do면 회원목록 출력
			List<MemberVo> memberList = memberDAO.listMember();
			request.setAttribute("membersList", memberList);
			nextPage = "/test02/listMembers.jsp";
			// test02 폴더의 listMember.jsp로 포워딩함
		} else if (action.equals("/addMember.do")) {
		// action 값이 /addMember.do면 전송된 회원정보를 가져와서 테이블에 추가
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVo memberVO  = new MemberVo(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			nextPage = "/member151/listMembers.do";
			//회원 등록 후 다시 회원 목록 출력
		} else if (action.equals("/memberForm.do")) {
		// action 값이 /memberForm.do면 회원가입창 출력
			nextPage = "/test02/memberForm.jsp";
			// test02 폴더의 memberForm.jsp로 포워딩함
		} else { //그 외 다른 action 값은 회원목록 출력
			List<MemberVo> memberList = memberDAO.listMember();
			request.setAttribute("membersList", memberList);
			nextPage = "/test02/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		//nextPage에 지정한 요청명으로 다시 서블릿에 요청
		dispatch.forward(request, response);
	}

}
