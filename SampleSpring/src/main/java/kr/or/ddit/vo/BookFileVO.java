package kr.or.ddit.vo;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BookFileVO {
	private MultipartFile item;
	private int fileNo;
	private String fileName;
	private long fileSize;
	private String fileFancysize;
	private String fileMime;
	private String fileSavepath;
	private int fileDowncount;
	private int bookId;
	
	public BookFileVO() {}
	
	public BookFileVO(MultipartFile item) {
		this.item = item;
		fileName = item.getOriginalFilename();
		fileSize = item.getSize();
		fileMime = item.getContentType();
		fileFancysize = FileUtils.byteCountToDisplaySize(fileSize);
	}
}
