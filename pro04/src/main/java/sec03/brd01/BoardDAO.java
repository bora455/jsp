package sec03.brd01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private Connection conn; 
	private PreparedStatement pstmt; 
	private DataSource dataFactory; 
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/servletex");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			//오라클의 계층형 SQL문 실행
			String query = ("SELECT CASE WHEN LEVEL-1 > 0 then CONCAT(CONCAT(REPEAT('', level-1),'<'), board.title)"
					+ " ELSE board.title"
					+ " END AS title, board.articleNO, board.parentNO, result.level, board.content, board.id, board.writeDate"
					+ " FROM "
					+ " (SELECT function_hierarchical() AS articleNO, @level AS LEVEL"
					+ " FROM (SELECT @start_with := 0, @articleNO := @start_with, @LEVEL := 0) tbl JOIN t_board) result"
					+ " JOIN t_board board ON board.articleNO = result.articleNO;");
			pstmt = conn.prepareStatement(query); 
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) { 
				int level = rs.getInt("level"); //각 글의 깊이(계층)를 level 속성에 저장
				int articleNO = rs.getInt("articleNO"); //글 번호는 숫자형
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				//글 정보를 ArticleVO 객체의 속성에 설정
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
		
}
