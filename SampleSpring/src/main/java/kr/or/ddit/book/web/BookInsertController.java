package kr.or.ddit.book.web;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.book.ServiceResult;
import kr.or.ddit.book.service.IBookService;
import kr.or.ddit.vo.BookVO;

@Controller
@RequestMapping("/book")
public class BookInsertController {
	
	@Inject
	IBookService bookService;
	
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String bookForm() {
		return "book/form";
	}
	
	@RequestMapping(value="/form.do", method = RequestMethod.POST)
	public String bookInsert(@RequestParam Map<String, Object> map, 
			HttpServletRequest request,  BookVO bookVO, Model model) {
		String goPage = "";
		// 파일 데이터 없는 insert     ----------------------------------
//		bookService.insertBook(map);
//		
//		if(map.get("book_id").toString() != null) {
//			goPage = "redirect:/book/detail.do?bookId="+map.get("book_id").toString();
//		}else {
//			model.addAttribute("data", map);
//			goPage = "book/form";
//		}
		// 파일 데이터 없는 insert End ----------------------------------
		
		// 파일데이터 사용하는 insert     ----------------------------------
		ServiceResult result = bookService.insertBookByFile(bookVO, request);    
		// 파일데이터 사용하는 insert End ----------------------------------
		
		return goPage;
	}
	
	
	
}
