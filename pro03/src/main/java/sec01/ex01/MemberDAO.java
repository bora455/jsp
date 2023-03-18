package sec01.ex01;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class MemberDAO {
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://localhost:3306/servletex";
	private static final String user = "root";
	private static final String pwd = "1234";
	private Connection con;
	private PreparedStatement pstmt;
	
	public List listMembers() {
		List list = new ArrayList();
		try {
			connDB() ;
			String query = "Select * from t_member order by joinDate desc ";
			//회원 정보를 최근 가입일 순으로 조회
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				//조회한 회원 정보를 MemberBean 객체의 속성에 저장 후 다시 ArrayList에 저장
				MemberBean vo = new MemberBean();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//MemberBean 객체에 저장된 회원 정보를 전달
	public void addMember (MemberBean memberBean) {
		try {
			connDB();
			//getter를 이용해 회원 정보 가져옴
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			//회원 정보 추가
			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
