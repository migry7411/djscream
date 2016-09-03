package net.migry.dto;

public class BoardCode {
	
	/** 개시판 ID */
	private String id;
	
	/** 개시판 이름 */
	private String name;
	
	/** 개시판 사용 여부 */
	private String use_board;
	
	/** HTML 태크 사용 여부 */
	private String use_html_tag;
	
	/** 쓰기 권한 */
	private String write_auth;
	
	/** 댓글 사용 여부 */
	private String use_reply;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getUse_board() {
		return use_board;
	}

	public void setUse_board(String use_board) {
		this.use_board = use_board;
	}

	public String getUse_html_tag() {
		return use_html_tag;
	}

	public void setUse_html_tag(String use_html_tag) {
		this.use_html_tag = use_html_tag;
	}

	public String getWrite_auth() {
		return write_auth;
	}

	public void setWrite_auth(String write_auth) {
		this.write_auth = write_auth;
	}

	public String getUse_reply() {
		return use_reply;
	}

	public void setUse_reply(String use_reply) {
		this.use_reply = use_reply;
	}
}
