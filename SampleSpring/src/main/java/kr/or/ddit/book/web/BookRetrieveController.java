package kr.or.ddit.book.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.book.service.IBookService;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/book")
public class BookRetrieveController {

	@Autowired
	IBookService bookService;
	
	@RequestMapping(value="/list.do")
	public String list(@RequestParam Map<String, Object> map, Model model) {
		List<Map<String, Object>> list = bookService.selectBookList(map);
		model.addAttribute("list", list);
		return "book/list2";
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String detail(@RequestParam Map<String, Object> map, Model model) {
		Map<String, Object> detailMap = bookService.selectBook(map);
		model.addAttribute("book", detailMap);
		return "book/detail";
	}
	
	@RequestMapping(value="/list2.do")
	public String noticeListView(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false, defaultValue="title") String searchType,
			@RequestParam(required=false) String searchWord,
			HttpServletRequest req,
			Model model){
		PaginationInfoVO<BookVO> pagingVO = new PaginationInfoVO<BookVO>();
		
		// 검색 기능 추가시 활용
		if(StringUtils.isNotBlank(searchWord)){
			if("title".equals(searchType)){
				pagingVO.setSearchType("title");
			}else {
				pagingVO.setSearchType("category");
			}
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = bookService.selectBookCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BookVO> dataList = bookService.selectBookList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		return "book/list2";
	}	
	
	@RequestMapping(value="/detail2.do", method = RequestMethod.GET)
	public String detail2(@RequestParam int bookId, Model model) {
		BookVO bookVO = bookService.selectBook2(bookId);
		model.addAttribute("book", bookVO);
		System.out.println(bookVO.toString());
		return "book/detail2";
	}
}
