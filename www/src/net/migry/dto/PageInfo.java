package net.migry.dto;

public class PageInfo {
	/** 현재 페이지 정보 */
	private int nowPage =0;
	/** 현재 블럭 정보 */
	private int nowBlock =0;
	/** 검색컬럼 */
	private String searchColumn="";
	/** 검색어 */
	private String searchWord="";
	/** 코드 */
	private String code = "";
	
	public PageInfo() {
		super();
	}

	public PageInfo(int nowPage, int nowBlock, String searchColumn, String searchWord, String code) {
		super();
		this.nowPage = nowPage;
		this.nowBlock = nowBlock;
		this.searchColumn = searchColumn;
		this.searchWord = searchWord;
		this.code = code;
	}
	
	public int getNowPage() {
		return nowPage;
	}
	
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	public int getNowBlock() {
		return nowBlock;
	}
	
	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getSearchColumn() {
		return searchColumn;
	}

	public void setSearchColumn(String searchColumn) {
		this.searchColumn = searchColumn;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
}
