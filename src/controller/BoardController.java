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
	
		//�߰��ڵ�
	String boardid = "1";
	String pageNum ="1";
		//��𼭵��� �� �� �ֵ��� �ϱ� ���ؼ�
	BoardDBMybatis dbPro = BoardDBMybatis.getInstance();
	
	
		//�߰��ڵ�
		// @ModelAttribute �̸� �ö���ְ� �ϴ� ���̴�.
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
	
		//�Խñ� ����Ʈ (1)  - �����Խ���
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
	
	
		//�߰�
		//�Խñ� ����Ʈ (2) - �����Խ���
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

		// �Խñ� ���� Form (1)  - �����Խ���
	@RequestMapping("/writeFormUpload") //�̰��� �޼ҵ� ���� ��� ����. ��ܰ� �� ������Ѵ�.
			//������ �޾ƾ��ϹǷ�, ����� ��쿡�� ��,�Ƹ���Ÿ, �Ƹ������� �ٹ������ �����ֱ� �����̴�.
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
			//writeFormUpload ��ÿ� boardid�� null�̶� article.getBoardid()�ȵȴ�. 
			//����� �����ϰ� 1�� �Ǵ� ���̴�.
		mv.addObject("pageNum", pageNum);
			//ModelAndView�� �ٲٴ� ����̴�.
			//��ܰ� �� ������Ѵ�.
		mv.setViewName("writeFormUpload");
		return mv;
	}
	
	
		//�߰�
		//�Խñ� ���� Form (2) - �����Խ���
	@RequestMapping("/writeFormUpload2") //�̰��� �޼ҵ� ���� ��� ����. ��ܰ� �� ������Ѵ�.
	//������ �޾ƾ��ϹǷ�, ����� ��쿡�� ��,�Ƹ���Ÿ, �Ƹ������� �ٹ������ �����ֱ� �����̴�.
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
			//writeFormUpload ��ÿ� boardid�� null�̶� article.getBoardid()�ȵȴ�. 
			//����� �����ϰ� 1�� �Ǵ� ���̴�.
		mv.addObject("pageNum", pageNum);
			//ModelAndView�� �ٲٴ� ����̴�.
			//��ܰ� �� ������Ѵ�.
		mv.setViewName("writeFormUpload2");
		return mv;
		}

//=======================================================================================	
	
		// �Խñ� ���� Pro (1) - �����Խ���
	@RequestMapping("/writeProUpload")
	//MultipartRequest ����Ʈ �ߴٰ� ����
	//������ WriteProUploadAction �ڵ尡 ���� �ٸ�, �� ���� ����
	//���帮���̷�Ʈ�� ��Ʈ������ �޾ƾ���???
	public String writeProUpload(MultipartHttpServletRequest request,
								BoardDataBean article, Model model)
								throws Exception {
		//������ ���� �ٸ�
		//MultipartRequest ����Ʈ�� �ٸ� 
		
		ModelAndView mv = new ModelAndView();
		MultipartFile multi = request.getFile("uploadfile");
		String filename = multi.getOriginalFilename();
		System.out.println("filename :[" + filename + "]");
		if (filename != null && !filename.equals("")) {
			String uploadPath = request.getRealPath("/")+"filesave"; // �������ִ°���, ������� �ʾ����� �ϴ� ���̴�. 
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
//�߰�
		model.addAttribute("boardid", boardid);
		return "redirect:list";
	}
	
	
		//�߰�
		//�Խñ� ���� Pro (2) - �����Խ���
	@RequestMapping("/writeProUpload2")
	//MultipartRequest ����Ʈ �ߴٰ� ����
	//������ WriteProUploadAction �ڵ尡 ���� �ٸ�, �� ���� ����
	//���帮���̷�Ʈ�� ��Ʈ������ �޾ƾ���???
	public String writeProUpload2(MultipartHttpServletRequest request,
								BoardDataBean article, Model model)
								throws Exception {
		//������ ���� �ٸ�
		//MultipartRequest ����Ʈ�� �ٸ� 
		
//�߰�
		String boardid = "2";
		ModelAndView mv = new ModelAndView();
		MultipartFile multi = request.getFile("uploadfile");
		String filename = multi.getOriginalFilename();
		System.out.println("filename :[" + filename + "]");
		if (filename != null && !filename.equals("")) {
			String uploadPath = request.getRealPath("/")+"filesave"; // �������ִ°���, ������� �ʾ����� �ϴ� ���̴�. 
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
//�߰�
		model.addAttribute("boardid", boardid);
		return "redirect:list2";
	}
	
//=======================================================================================
	
		//�Խñ� ���뺸�� (1) - �����Խ���
	@RequestMapping("/content")
	public String content(int num, Model model)
						throws Exception {
		
			BoardDataBean article = dbPro.getArticle(num, boardid, "content"); 
			model.addAttribute("article", article);
			model.addAttribute("pageNum", pageNum);	 
//�߰�
			model.addAttribute("boardid", boardid);
		 
		return "content";
	}
	
		//�߰�
		//�Խñ� ���뺸�� (2) - �����Խ���
	@RequestMapping("/content2")
	public String content2(int num, Model model)
						throws Exception {
		
			BoardDataBean article = dbPro.getArticle(num, boardid, "content"); 
			String boardid = "2";
			model.addAttribute("article", article);
			model.addAttribute("pageNum", pageNum);	
//�߰�
			model.addAttribute("boardid", boardid);
		 
		return "content2";
	}
	
//=======================================================================================	
	
		//�Խñ� ���� Form (1) - �����Խ���
	@RequestMapping("/updateForm")
	public String updateForm(int num, Model model)
			throws Exception {
		
	BoardDataBean article =  dbPro.getArticle(num, boardid, "update");
	model.addAttribute("article", article);
//�߰�
	model.addAttribute("boardid", boardid);
		return "updateForm";
	}
	
	
		//�Խñ� ���� Form (2) - �����Խ���
	@RequestMapping("/updateForm2")
	public String updateForm2(int num, Model model)
			throws Exception {
		
	BoardDataBean article =  dbPro.getArticle(num, boardid, "update");
	model.addAttribute("article", article);
		return "updateForm2";
	}
	
	
//=======================================================================================

		//�Խñ� ���� Pro (1) - �����Խ���
	@RequestMapping("/updatePro")
	public String updatePro(BoardDataBean article, Model model)
			throws Exception {
		
	 	int chk= dbPro.updateArticle(article); 
	 	model.addAttribute("chk", chk);
	 	model.addAttribute("pageNum", pageNum);	
//�߰�
	 	model.addAttribute("boardid", boardid);
		return "updatePro";
	}
	
		//�Խñ� ���� Pro (2) - �����Խ���
	@RequestMapping("/updatePro2")
	public String updatePro2(BoardDataBean article, Model model)
			throws Exception {
		
	 	int chk= dbPro.updateArticle(article); 
	 	model.addAttribute("chk", chk);
	 	model.addAttribute("pageNum", pageNum);	 
		return "updatePro2";
	}
	
//=======================================================================================
	
		//�Խñ� ���� Form (1) - �����Խ���
	@RequestMapping(value = "deleteForm")
		//value = "deleteForm" �̷��� �ϸ� � �� �� �� �ִ� ������ �ִ�.
		//������̼��� ������Ʈ�̴�. � �ɷ��ִ� ���̴�.
	public ModelAndView deleteForm(int num)
			throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("num", num);
		mv.addObject("pageNum", pageNum);
//�߰�
		mv.addObject("boardid", boardid);
		mv.setViewName("deleteForm");		
		return mv;
	}
	
	//�Խñ� ���� Form (2) - �����Խ���
	@RequestMapping(value = "deleteForm2")
		//value = "deleteForm" �̷��� �ϸ� � �� �� �� �ִ� ������ �ִ�.
		//������̼��� ������Ʈ�̴�. � �ɷ��ִ� ���̴�.
	public ModelAndView deleteForm2(int num)
			throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("num", num);
		mv.addObject("pageNum", pageNum);
		mv.setViewName("deleteForm2");		
		return mv;
	}
		
//=======================================================================================
	
		//�Խñ� ���� Pro (1) - �����Խ���
	@RequestMapping(value = "deletePro")
	public ModelAndView deletePro(int num, String passwd)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		int check = dbPro.deleteArticle(num, passwd, boardid); 
		mv.addObject("check", check);
		mv.addObject("pageNum", pageNum);
//�߰�
		mv.addObject("boardid", boardid);
		mv.setViewName("deletePro");
		return mv;
	}
	
	//�Խñ� ���� Pro (2) - �����Խ���
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