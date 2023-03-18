package sec03.brd02;

import java.util.List;

public class BoardService1 {
	BoardDAO1 boardDAO;
	public BoardService1() {
		boardDAO = new BoardDAO1();
	}
	
	public List<ArticleVO1> listArticles() {
		List<ArticleVO1> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	
	public void addArticle(ArticleVO1 article) {
		boardDAO.insertNewArticle(article);
	}

}
