package protest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/protest");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List listMember() {
		List<MemberVO> membersList = new ArrayList();
		try { 
			con = dataFactory.getConnection();
			String query = "SELECT * from studentlists order by id desc";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String username = rs.getString("username");
				String univ = rs.getString("univ");
				String birth = rs.getString("birth");
				String email = rs.getString("email");
				MemberVO memberVo = new MemberVO(id, username, univ, birth, email);
				membersList.add(memberVo); 
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membersList;
	}
	
	public void addMember(MemberVO s) {
		try {
			con = dataFactory.getConnection();
			String id = s.getId();
			String username = s.getUsername();
			String univ = s.getUniv();
			String birth = s.getBirth();
			String email = s.getEmail();
			String query = "INSERT INTO protest(id, username, univ, email" + "VALUES(?,?,?,?,?)";
			System.out.println(query);
			pstmt.setString(1, id);
			pstmt.setString(2, univ);
			pstmt.setString(3, username);
			pstmt.setString(4, birth);
			pstmt.setString(5, email);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
