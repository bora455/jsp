package sec03.brd05;

import java.net.URLEncoder;
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
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext =(Context) ctx.lookup("java:/comp/env");
			dataFactory =(DataSource) envContext.lookup("jdbc/servletex");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List selectAllArticles() { //새 글 정보를 인자로 전달하기
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select case when level-1 > 0 then"
					+ " concat(concat(repeat('  ', level-1),'<'), board.title) else board.title end as title, board.articleNO, board.parentNO, result.level, board.content, board.id, board.writeDate"
					+ " from (select function_hierarchical() as articleNO, @level as level"
					+ " from (select @start_with:=0, @articleNo:=@start_with, @level:=0) tbl join t_board) result"
					+ " join t_board board on board.articleNO = result.articleNO;";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
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
	
	private int getNewArticleNO() { //새 글에 대한 글 번호 가져오기
		try {
			conn = dataFactory.getConnection();
			//기본 글 번호 중 가장 큰 번호를 조회함
			String query = "select max(articleNO) as articleNO from t_board;";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) 
				return (rs.getInt("articleNO")+1); //가장 큰 번호에 1을 더한 번호를 반환함
				rs.close();
				pstmt.close();
				conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		} return 25;
	}
	
	
	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
		try {
			conn = dataFactory.getConnection();
			int parentNO = article.getParentNO(); //새 글을 추가하기 전에 새 글에 대한 글 번호 가져옴
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			//insert문을 이용해 글 정보 추가함
			String query = "insert into t_board (articleNO, parentNO, title, content, imageFileName, id)" +"values(?,?,?,?,?,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleNO; //SQL문으로 새글을 추가하고 새글 번호를 반환함
	}
	
	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			//전달받은 글 번호를 이용해 글 정보 조회
			String query = "select articleNO,parentNO,title,content,ifnull(iamgeFileName,'null') as imageFileName,id,writeDate"
							+ " from t_board"
							+ " where articleNO=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _articleNO = rs.getInt("articleNO");
			int parentNO = rs.getInt("parentNO");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "UTF-8");
			if(imageFileName.equals("null")) {
				imageFileName = null;
			}
			
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			article.setArticleNO(_articleNO);
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			conn = dataFactory.getConnection();
			String query = "update t_board set title=?,content=?";
			
			//수정된 이미지 파일이 있을 때만 imageFileName을 SQL문에 추가함
			if (imageFileName != null && imageFileName.length() != 0) {
				query += "iamgeFileName=?";
			}
			
			query += " where articleNO=?";
			
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			
			//이미지 파일을 수정하는 경우와 그렇지 않은 경우를 구분해서 설정함
			if (imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			} else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
