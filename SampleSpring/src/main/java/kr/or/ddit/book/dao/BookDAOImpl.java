package kr.or.ddit.book.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class BookDAOImpl implements IBookDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Map<String, Object>> selectBookList(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("Book.selectBookList", map);
	}

	@Override
	public Map<String, Object> selectBook(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("Book.selectBook", map);
	}

	@Override
	public void insertBook(Map<String, Object> map) {
		sqlSessionTemplate.insert("Book.insertBook", map);
	}

	@Override
	public int deleteBook(Map<String, Object> map) {
		return sqlSessionTemplate.delete("Book.deleteBook", map);
	}
	
	@Override
	public int updateBook(Map<String, Object> map) {
		return sqlSessionTemplate.update("Book.updateBook", map);
	}

	@Override
	public int selectBookCount(PaginationInfoVO<BookVO> pagingVO) {
		return sqlSessionTemplate.selectOne("Book.selectBookCount", pagingVO);
	}

	@Override
	public List<BookVO> selectBookList(PaginationInfoVO<BookVO> pagingVO) {
		return sqlSessionTemplate.selectList("Book.selectBookList2", pagingVO);
	}

}
