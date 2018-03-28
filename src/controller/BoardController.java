package controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.BoardDBMybatis;
import board.BoardDataBean;
/*import handler.board.Exception;*/
/*import handler.board.Exception;*/
/*import handler.board.Exception;*/
/*import jdk.nashorn.internal.ir.RuntimeNode.Request;*/
/*import handler.board.Exception;
import handler.board.Override;*/
/*import handler.board.String;*/

@Controller
@RequestMapping("/board")
public class BoardController {
	
		//추가코드
	String boardid = "1";
	String pageNum ="1";
		//어디서든지 쓸 수 있도록 하기 위해서
	BoardDBMybatis dbPro = BoardDBMybatis.getInstance();
	
	
		//추가코드
		// @ModelAttribute 미리 올라와있게 하는 것이다.
	@ModelAttribute
	public void addAttributes (String boardid, String pageNum) {
		if (boardid != null) this.boardid = boardid;
		if (pageNum != null && pageNum != "") this.pageNum = pageNum;
	}
	

//=======================================================================================	
	
	@RequestMapping("/index")
	public String index() { //Model model
		/*model.addAttribute("message", "/board/index");*/
		return "index";
	}
	
	
//=======================================================================================
	
		//게시글 리스트 (1)  - 자유게시판
	@RequestMapping("/list")
	public String list(Model model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//
		String boardid = "1";
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		count = dbPro.getArticleCount(boardid);  
		if (count > 0) {
				articleList = dbPro.getArticles(startRow, endRow, boardid);	
			}
		number = count - (currentPage - 1) * pageSize;
		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) endPage = pageCount;

		model.addAttribute("boardid", boardid);
		model.addAttribute("count", count);
		model.addAttribute("articleList", articleList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("bottomLine", bottomLine);
		model.addAttribute("endPage", endPage);
		model.addAttribute("number", number);
	    
			return "list";
		}
	
	
		//추가
		//게시글 리스트 (2) - 공지게시판
	@RequestMapping("/list2")
	public String list2(Model model) throws Exception {
		//
		String boardid = "2";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		count = dbPro.getArticleCount(boardid);  
		if (count > 0) {
				articleList = dbPro.getArticles(startRow, endRow, boardid);	
			}
		number = count - (currentPage - 1) * pageSize;
		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) endPage = pageCount;

		model.addAttribute("boardid", boardid);
		model.addAttribute("count", count);
		model.addAttribute("articleList", articleList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("bottomLine", bottomLine);
		model.addAttribute("endPage", endPage);
		model.addAttribute("number", number);
	    
			return "list2";
		}
	
//=======================================================================================	

		// 게시글 쓰기 Form (1)  - 자유게시판
	@RequestMapping("/writeFormUpload") //이것은 메소드 명과 상관 없다. 뷰단과 꼭 맞춰야한다.
			//파일을 받아야하므로, 답글일 경우에는 넘,아리스타, 아리레벨을 겟방식으로 보내주기 떄문이다.
	public ModelAndView writeFormUpload(BoardDataBean article)
			throws Exception {
	
		ModelAndView mv = new ModelAndView();
		//
		String boardid = "1";
		mv.addObject("num", article.getNum());
		mv.addObject("ref", article.getRef());
		mv.addObject("re_step", article.getRe_step());
		mv.addObject("re_level", article.getRe_level());
		mv.addObject("boardid", boardid); 
			//writeFormUpload 당시에 boardid는 null이라서 article.getBoardid()안된다. 
			//저장과 무관하게 1이 되는 것이다.
		mv.addObject("pageNum", pageNum);
			//ModelAndView로 바꾸는 방법이다.
			//뷰단과 꼭 맞춰야한다.
		mv.setViewName("writeFormUpload");
		return mv;
	}
	
	
		//추가
		//게시글 쓰기 Form (2) - 공지게시판
	@RequestMapping("/writeFormUpload2") //이것은 메소드 명과 상관 없다. 뷰단과 꼭 맞춰야한다.
	//파일을 받아야하므로, 답글일 경우에는 넘,아리스타, 아리레벨을 겟방식으로 보내주기 떄문이다.
		public ModelAndView writeFormUpload2(BoardDataBean article)
			throws Exception {
		
		ModelAndView mv = new ModelAndView();
		//
		String boardid = "2";
		mv.addObject("num", article.getNum());
		mv.addObject("ref", article.getRef());
		mv.addObject("re_step", article.getRe_step());
		mv.addObject("re_level", article.getRe_level());
		mv.addObject("boardid", boardid); 
			//writeFormUpload 당시에 boardid는 null이라서 article.getBoardid()안된다. 
			//저장과 무관하게 1이 되는 것이다.
		mv.addObject("pageNum", pageNum);
			//ModelAndView로 바꾸는 방법이다.
			//뷰단과 꼭 맞춰야한다.
		mv.setViewName("writeFormUpload2");
		return mv;
		}

//=======================================================================================	
	
		// 게시글 쓰기 Pro (1) - 자유게시판
	@RequestMapping("/writeProUpload")
	//MultipartRequest 임포트 했다가 지움
	//기존과 WriteProUploadAction 코드가 완전 다름, 손 볼거 많음
	//샌드리다이랙트는 스트링으로 받아야함???
	public String writeProUpload(MultipartHttpServletRequest request,
								BoardDataBean article, Model model)
								throws Exception {
		//기존과 완전 다름
		//MultipartRequest 임포트가 다름 
		
		ModelAndView mv = new ModelAndView();
		MultipartFile multi = request.getFile("uploadfile");
		String filename = multi.getOriginalFilename();
		System.out.println("filename :[" + filename + "]");
		if (filename != null && !filename.equals("")) {
			String uploadPath = request.getRealPath("/")+"filesave"; // 줄쳐저있는것은, 사용하지 않았으면 하는 것이다. 
			System.out.println(uploadPath);
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			article.setFilename(filename);
			article.setFilesize((int) multi.getSize());
		} else {
			article.setFilename("");
			article.setFilesize(0);
		}
		// article.setIp(remoteId);
		article.setIp(request.getRemoteAddr());
		System.out.println(article);
		dbPro.insertArticle(article);
		model.addAttribute("pageNum", pageNum);
//추가
		model.addAttribute("boardid", boardid);
		return "redirect:list";
	}
	
	
		//추가
		//게시글 쓰기 Pro (2) - 공지게시판
	@RequestMapping("/writeProUpload2")
	//MultipartRequest 임포트 했다가 지움
	//기존과 WriteProUploadAction 코드가 완전 다름, 손 볼거 많음
	//샌드리다이랙트는 스트링으로 받아야함???
	public String writeProUpload2(MultipartHttpServletRequest request,
								BoardDataBean article, Model model)
								throws Exception {
		//기존과 완전 다름
		//MultipartRequest 임포트가 다름 
		
//추가
		String boardid = "2";
		ModelAndView mv = new ModelAndView();
		MultipartFile multi = request.getFile("uploadfile");
		String filename = multi.getOriginalFilename();
		System.out.println("filename :[" + filename + "]");
		if (filename != null && !filename.equals("")) {
			String uploadPath = request.getRealPath("/")+"filesave"; // 줄쳐저있는것은, 사용하지 않았으면 하는 것이다. 
			System.out.println(uploadPath);
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			article.setFilename(filename);
			article.setFilesize((int) multi.getSize());
		} else {
			article.setFilename("");
			article.setFilesize(0);
		}
		// article.setIp(remoteId);
		article.setIp(request.getRemoteAddr());
		System.out.println(article);
		dbPro.insertArticle(article);
		model.addAttribute("pageNum", pageNum);
//추가
		model.addAttribute("boardid", boardid);
		return "redirect:list2";
	}
	
//=======================================================================================
	
		//게시글 내용보기 (1) - 자유게시판
	@RequestMapping("/content")
	public String content(int num, Model model)
						throws Exception {
		
			BoardDataBean article = dbPro.getArticle(num, boardid, "content"); 
			model.addAttribute("article", article);
			model.addAttribute("pageNum", pageNum);	 
//추가
			model.addAttribute("boardid", boardid);
		 
		return "content";
	}
	
		//추가
		//게시글 내용보기 (2) - 공지게시판
	@RequestMapping("/content2")
	public String content2(int num, Model model)
						throws Exception {
		
			BoardDataBean article = dbPro.getArticle(num, boardid, "content"); 
			String boardid = "2";
			model.addAttribute("article", article);
			model.addAttribute("pageNum", pageNum);	
//추가
			model.addAttribute("boardid", boardid);
		 
		return "content2";
	}
	
//=======================================================================================	
	
		//게시글 수정 Form (1) - 자유게시판
	@RequestMapping("/updateForm")
	public String updateForm(int num, Model model)
			throws Exception {
		
	BoardDataBean article =  dbPro.getArticle(num, boardid, "update");
	model.addAttribute("article", article);
//추가
	model.addAttribute("boardid", boardid);
		return "updateForm";
	}
	
	
		//게시글 수정 Form (2) - 공지게시판
	@RequestMapping("/updateForm2")
	public String updateForm2(int num, Model model)
			throws Exception {
		
	BoardDataBean article =  dbPro.getArticle(num, boardid, "update");
	model.addAttribute("article", article);
		return "updateForm2";
	}
	
	
//=======================================================================================

		//게시글 수정 Pro (1) - 자유게시판
	@RequestMapping("/updatePro")
	public String updatePro(BoardDataBean article, Model model)
			throws Exception {
		
	 	int chk= dbPro.updateArticle(article); 
	 	model.addAttribute("chk", chk);
	 	model.addAttribute("pageNum", pageNum);	
//추가
	 	model.addAttribute("boardid", boardid);
		return "updatePro";
	}
	
		//게시글 수정 Pro (2) - 공지게시판
	@RequestMapping("/updatePro2")
	public String updatePro2(BoardDataBean article, Model model)
			throws Exception {
		
	 	int chk= dbPro.updateArticle(article); 
	 	model.addAttribute("chk", chk);
	 	model.addAttribute("pageNum", pageNum);	 
		return "updatePro2";
	}
	
//=======================================================================================
	
		//게시글 삭제 Form (1) - 자유게시판
	@RequestMapping(value = "deleteForm")
		//value = "deleteForm" 이렇게 하면 몇개 더 할 수 있는 장점이 있다.
		//어노테이션은 오브젝트이다. 몇개 걸려있는 것이다.
	public ModelAndView deleteForm(int num)
			throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("num", num);
		mv.addObject("pageNum", pageNum);
//추가
		mv.addObject("boardid", boardid);
		mv.setViewName("deleteForm");		
		return mv;
	}
	
	//게시글 삭제 Form (2) - 공지게시판
	@RequestMapping(value = "deleteForm2")
		//value = "deleteForm" 이렇게 하면 몇개 더 할 수 있는 장점이 있다.
		//어노테이션은 오브젝트이다. 몇개 걸려있는 것이다.
	public ModelAndView deleteForm2(int num)
			throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("num", num);
		mv.addObject("pageNum", pageNum);
		mv.setViewName("deleteForm2");		
		return mv;
	}
		
//=======================================================================================
	
		//게시글 삭제 Pro (1) - 자유게시판
	@RequestMapping(value = "deletePro")
	public ModelAndView deletePro(int num, String passwd)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		int check = dbPro.deleteArticle(num, passwd, boardid); 
		mv.addObject("check", check);
		mv.addObject("pageNum", pageNum);
//추가
		mv.addObject("boardid", boardid);
		mv.setViewName("deletePro");
		return mv;
	}
	
	//게시글 삭제 Pro (2) - 공지게시판
		@RequestMapping(value = "deletePro2")
		public ModelAndView deletePro2(int num, String passwd)
				throws Exception {
			ModelAndView mv = new ModelAndView();
			int check = dbPro.deleteArticle(num, passwd, boardid); 
			mv.addObject("check", check);
			mv.addObject("pageNum", pageNum);
			mv.setViewName("deletePro2");
			return mv;
		}
	
}
