package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BookVO {
	// BOOK_ID, TITLE, CATEGORY, PRICE, INSERT_DATE
	private int bookId;
	private String title;
	private String category;
	private int price;
	private String insertDate;
	
	private int[] delBookId;
	private MultipartFile[] bookFile;
	private List<BookFileVO> bookFileList;
	
	public void setBookFile(MultipartFile[] bookFile) {
		this.bookFile = bookFile;
		if(bookFile != null) {
			List<BookFileVO> bookList = new ArrayList<BookFileVO>();
			for(MultipartFile item : bookFile) {
				if(StringUtils.isBlank(item.getOriginalFilename())) {
					continue;
				}
				BookFileVO bookFileVO = new BookFileVO(item);
				bookList.add(bookFileVO);
			}
			this.bookFileList = bookList;
		}
	}
}
