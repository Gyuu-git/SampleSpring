package kr.or.ddit.book.web;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.book.service.IBookService;

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
	public String bookInsert(@RequestParam Map<String, Object> map, Model model) {
		String goPage = "";
		bookService.insertBook(map);
		
		if(map.get("book_id").toString() != null) {
			goPage = "redirect:/book/detail.do?bookId="+map.get("book_id").toString();
		}else {
			model.addAttribute("data", map);
			goPage = "book/form";
		}
		
		return goPage;
	}
	
	
	
}
