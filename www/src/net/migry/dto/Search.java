package net.migry.dto;

public class Search {
	/** 검색 컬럼명 */
    private String searchColumn;
    
    /** 검색어 */
    private String searchWord;
    
    /** 시작 행번호 */
    private int startRow;
    
    /** 끝 행번호 */
    private int endRow;
    
    /** 코드 */
    private String code;

	/**
     * 기본 생성자 
     */
    public Search() {
        super();
    }

	public Search(String searchColumn, String searchWord, int startRow, int endRow, String code) {
		super();
		this.searchColumn = searchColumn;
		this.searchWord = searchWord;
		this.startRow = startRow;
		this.endRow = endRow;
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

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
