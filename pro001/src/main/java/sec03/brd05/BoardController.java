package sec03.brd05;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	BoardService boardService;
	ArticleVO articleVO;

	public void init() throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board05/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board05/listArticles.jsp";
			} else if (action.equals("/articleForm.do")) { //글쓰기 창 나타남
				nextPage = "/board05/articleForm.jsp";
			} else if (action.equals("/addArticle.do")) { //새 글 추가
				int articleNO = 0;
				Map<String, String> articleMap = upload(request, response);
				//articleMap에 저장된 글 정보를 다시 가져옴
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				//글쓰기창에서 입력된 정보를 ArticleVO 객체에 설정한 후 addArticle()로 전달
				articleVO.setParentNO(0); //새 글의 부모 글 번호를 0으로 설정
				articleVO.setId("hong"); //새 글의 ID를 hong으로 설정
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNO = boardService.addArticle(articleVO); //테이블에 새 글을 추가함 후 새글에 대한 글번호를 가져옴
				if(imageFileName != null && imageFileName.length() != 0) { //파일을 첨부한 경우에만 수행함
					//temp 폴더에 임시로 업로드된 파일 객체 생성
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + "articleNO");
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true); //temp 폴더의 파일을 글번호를 이름으로 하는 폴더로 이동시킴
					srcFile.delete();
				} //새글 등록 메시지를 나타낸 후 자바스크립트 location 객체의 href속성을 이용해 글목록 요청함
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "alert('새글을 추가했습니다.');" + "location.href=" +
				request.getContextPath() + "/board/listArticle.do';" + "</script>");
				return;
			} else if (action.equals("/viewArticle.do")) { 
				//글 상세창을 요청할 경우 articleNO값을 가져옴
				String articleNO = request.getParameter("articleNo");
				//articleNO에 대한 글 정보를 조회하고 article 속성으로 바인딩함
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
				request.setAttribute("article", articleVO);
				nextPage = "/board05/viewArticle.jsp";
			} else if (action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int articleNo = Integer.parseInt(articleMap.get("articleNO"));
				articleVO.setArticleNO(articleNo);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(0); //새 글의 부모 글 번호를 0으로 설정
				articleVO.setId("hong"); //새 글의 ID를 hong으로 설정
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.modArticle(articleVO); //전송된 글 정보를 이용해 글 수정함
				if (imageFileName != null && imageFileName.length() != 0) {
					String originalFileName = articleMap.get("originalFileName");
					//수정된 이미지 파일을 폴더로 이동함
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + "articleNO");
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					//전송된 originalFileName을 이용해 기존 파일 삭제함
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + "articleNO" + "\\" + originalFileName);
					oldFile.delete();
				}
				PrintWriter pw = response.getWriter();
				//글 수정 후 location 객체의 href 속성을 이용해 글 상세 화면 나타냄
				pw.print("<script>" + " alert('글을 수정했습니다.');" + " location.href='" 
						+ request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" 
						+ "articleNO" + "';" + "</script>");
				return;
			} else {
				nextPage = "/board05/listaArticles.jsp";
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		
		//글 이미지 저장 폴더에 대해 파일 객체 생성
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) { //텍스트 파일
					System.out.println(fileItem.getFieldName() + " = " + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					//파일 업로드로 같이 전송된 새 글 관련 매개변수를 Map에 (key, value)로 저장한 후 반환하고,
					//새 글과 관련된 title, content를 Map에 저장함
				} else {
					System.out.println("파라미터 명 : " + fileItem.getFieldName());
					System.out.println("파일 크기 : " + fileItem.getSize() + "bytes");
					
					//업로드한 파일이 존재할 경우 업로드한 파일의 파일 이름으로 저장소에 업로드함
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						//첨부한 파일을 먼저 temp 폴더에 업로드함
						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일명 : " + fileName);
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return articleMap;
	}

}
