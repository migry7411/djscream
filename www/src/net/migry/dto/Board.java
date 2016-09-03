package net.migry.dto;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	/** 아이디 */
	private int id;
	
	/** 이름 */
	private String name;
	
	/** 제목 */
	private String subject;
	
	/** 내용 */
	private String contents;
	
	/** 비밀번호 */
	private String passwd;
	
	/** 파일명 */
	private String filename;
	
	/** 파일 크기 */
	private int filesize;
	
	/** 조회수 */
	private int readcount;
	
	/** 등록일 */
	private String regdate;
	
	/** 코드 */
	private String code;
	
	/** 사용자아이디 */
	private String userid;
	
	/** 첨부파일 */
	private MultipartFile uploadFile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
