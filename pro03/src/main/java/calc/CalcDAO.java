package calc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class CalcDAO {
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://localhost:3306/calc";
	private static final String user = "root";
	private static final String pwd = "1234";
	private Connection con;
	private PreparedStatement pstmt;
	
	public List listNumbers() {
		List list = new ArrayList();
		try {
			connDB();
			String query = "select * from calc order by result desc";
			System.out.println("prepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int n1 = rs.getInt("n1");
				String op = rs.getString("op");
				int n2 = rs.getInt("n2");
				int result = rs.getInt("result");
				
				CalculatorBean vo = new CalculatorBean();
				vo.setN1(n1);
				vo.setOp(op);
				vo.setN2(n2);
				vo.setResult(result);
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
	public void addNumber (CalculatorBean calculatorBean) {
		try {
			connDB();
			int n1 = calculatorBean.getN1();
			String op = calculatorBean.getOp();
			int n2 = calculatorBean.getN2();
			int result = calculatorBean.getResult();
			String query = "insert into calc";
			query +="(n1,op,n2,result)";
			query += "values(?,?,?,?)";
			System.out.println("preparedStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, n1);
			pstmt.setString(2, op);
			pstmt.setInt(3, n2);
			pstmt.setInt(4, result);
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

