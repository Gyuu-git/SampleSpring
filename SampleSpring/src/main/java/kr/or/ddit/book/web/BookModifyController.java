package kr.or.ddit.book.web;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.book.service.IBookService;

@Controller
@RequestMapping("/book")
public class BookModifyController {

	@Autowired
	private IBookService bookService;
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String deleteBook(@RequestParam Map<String, Object> map) {
		String goPage = "";
		
		int status_code = bookService.deleteBook(map);
		if(status_code > 0) {		// 삭제 성공
			goPage = "redirect:/book/list.do";
		}else {						// 삭제 실패
			goPage = "redirect:/book/detail.do?bookId="+map.get("bookId").toString();
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String modifyBook(@RequestParam Map<String, Object> map, Model model) {
		Map<String, Object> book = bookService.selectBook(map);
		model.addAttribute("book", book);
		return "book/update";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String updateBook(@RequestParam Map<String, Object> map, Model model) {
		String goPage = "";
		int status_code = bookService.updateBook(map);
		if(status_code > 0) {		// 수정 성공
			goPage = "redirect:/book/detail.do?bookId="+map.get("bookId").toString();
		}else {						// 수정 실패
			model.addAttribute("book", map);
			goPage = "book/update";
		}
		return goPage;
	}
	
}














