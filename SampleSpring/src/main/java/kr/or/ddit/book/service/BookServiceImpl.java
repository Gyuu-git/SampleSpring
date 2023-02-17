package kr.or.ddit.book.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.book.ServiceResult;
import kr.or.ddit.book.dao.IBookDAO;
import kr.or.ddit.vo.BookFileVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BookServiceImpl implements IBookService {

	@Inject
	IBookDAO bookDAO;
	
	@Override
	public List<Map<String, Object>> selectBookList(Map<String, Object> map) {
		return bookDAO.selectBookList(map);
	}

	@Override
	public Map<String, Object> selectBook(Map<String, Object> map) {
		return bookDAO.selectBook(map);
	}

	@Override
	public void insertBook(Map<String, Object> map) {
		bookDAO.insertBook(map);
	}

	@Override
	public int deleteBook(Map<String, Object> map) {
		return bookDAO.deleteBook(map);
	}

	@Override
	public int updateBook(Map<String, Object> map) {
		return bookDAO.updateBook(map);
	}

	@Override
	public int selectBookCount(PaginationInfoVO<BookVO> pagingVO) {
		return bookDAO.selectBookCount(pagingVO);
	}

	@Override
	public List<BookVO> selectBookList(PaginationInfoVO<BookVO> pagingVO) {
		return bookDAO.selectBookList(pagingVO);
	}

	@Override 
	public ServiceResult insertBookByFile(BookVO bookVO, HttpServletRequest request) {
		ServiceResult result = null;
		
		int status = bookDAO.insertBookByFile(bookVO);
		if(status > 0) {
			List<BookFileVO> bookFileList = bookVO.getBookFileList();
			try {
				processBookFile(bookFileList, bookVO.getBookId(), request);
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	private void processBookFile(List<BookFileVO> bookFileList, int bookId, HttpServletRequest request) throws IOException {
		if(bookFileList != null && bookFileList.size() > 0) {
			for(BookFileVO bookFileVO : bookFileList) {
				String saveName = UUID.randomUUID().toString();
				// 확장자(.png, .jpg ...)
				String endFilename = bookFileVO.getFileName().split("\\.")[1];
				String saveLocate = request.getRealPath("/resources/upload");
				
				File file = new File(saveLocate);
				if(!file.exists()) {
					file.mkdirs();
				}
				
				saveLocate = saveLocate + "/" + bookId + "/" + saveName + "." + endFilename;
				File saveFile = new File(saveLocate);
				bookFileVO.setBookId(bookId);
				bookFileVO.setFileSavepath(saveLocate);
				bookDAO.insertBookFile(bookFileVO);
				InputStream is = bookFileVO.getItem().getInputStream();
				FileUtils.copyInputStreamToFile(is, saveFile);
				is.close();
			}
		}
	}

	@Override
	public BookVO selectBook2(int bookId) {
		return bookDAO.selectBook2(bookId);
	}

}
