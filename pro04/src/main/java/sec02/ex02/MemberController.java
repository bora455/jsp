package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec02.ex02.MemberDAO;
import sec02.ex02.MemberVO;

@WebServlet("/member152/*") 
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
		String action = request.getPathInfo(); 
		System.out.println("action: " + action);
		
		if(action == null || action.equals("/listMembers.do")) {
			List<MemberVO> membersList = memberDAO.listmembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		} else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO  = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			request.setAttribute("msg", "addMember");
			nextPage = "/member152/listMembers.do";
		} else if (action.equals("/memberForm.do")) {
			nextPage = "/test03/memberForm.jsp";
			
		//회원수정창 요청시 ID로 회원정보 조회후 수정창으로 포워딩함
		} else if (action.equals("/modMemberForm.do")) {
			String id = request.getParameter("id");
			MemberVO memInfo = memberDAO.findMember(id);
			//회원 정보 수정창을 요청하면서 전송된 ID를 이용해 수정 전 회원정보를 조회
			request.setAttribute("memInfo", memInfo);
			//request에 바인딩하여 회원정보수정창에 수정하기전 회원정보 전달
			nextPage="/test03/modMemberForm.jsp";
			
		//테이블의 회원 정보 수정
		} else if(action.equals("/modMember.do")) {
			//회원정보수정창에서 전송된 수정된 회원정보를 가져온 후 MemberVO 객체 속성에 설정함
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.modMember(memberVO);
			//회원목록창으로 수정 작업 완료 메시지 전달
			request.setAttribute("msg", "modified");
			nextPage="/member152/listMembers.do";
			
		//회원 ID를 SQL문으로 전달해 테이블의 회원정보 삭제
		} else if(action.equals("/delMember.do")) {
			//삭제할 회원 ID 받아옴
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			//회원목록창으로 삭제 작업 완료 메시지 전달
			request.setAttribute("msg", "deleted");
			nextPage = "/member152/listMembers.do";
		} else {
			List<MemberVO> membersList = memberDAO.listmembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
		
}
