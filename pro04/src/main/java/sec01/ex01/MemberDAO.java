package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con; //커넥션 참조변수 생성
	private PreparedStatement pstmt; //쿼리문을 매개변수로 받는 참조변수 생성
	private DataSource dataFactory; //커넥션 풀에서 커넥션을 사용할 수 있도록 해주는 데이터소스 참조변수 생성
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			//커넥션 풀에서 데이터소스(커넥션)을 사용하기 위한 JNDI 서버 객체 생성 후 ctx에 전달
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/servletex"); //데이터베이스
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List listMember() {
		List memberList = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member order by joinDate desc";
			// SQL문: t_member에서 생성일자 내림차순으로 정렬해서 호출한 모든 데이터 query에 삽입
			System.out.println(query); // 시험출력
			pstmt = con.prepareStatement(query); // t_member에서 query실행
			ResultSet rs = pstmt.executeQuery(); // 진짜실행
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVo memberVo = new MemberVo(id, pwd, name, email, joinDate);
				//조회한 회원정보를 레코드별로 Vo 객체의 속성에 저장
				memberList.add(memberVo); //memberList에 Vo 객체들을 차례대로 저장
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	public void addMember(MemberVo m) {
		try {
			con = dataFactory.getConnection();
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "INSERT INTO t_member(id,pwd,name,email)" + "VALUES(?,?,?,?)";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			//prepareStatement 객체 생성하면서 SQL문을 인지로 전달
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate(); // SQL문 실행
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
