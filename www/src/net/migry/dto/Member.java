package net.migry.dto;

/*** 회원 **/
public class Member {
	
	/*** 아이디 **/
	private String userid;
	
	/*** 이름 **/
	private String username;
	
	/*** 닉네임 **/
	private String nickname;
	
	/*** 비밀번호 **/
	private String password;
	
	/*** 이메일 **/
	private String email;
	
	/*** 전화번호 **/
	private String phone;
	
	/*** 생년월일 **/
	private String birthdate;
	
	/*** 양/음 **/
	private String birthtype;
	
	/*** 성별 **/
	private String sex;
	
	/*** 등록일 **/
	private String regdate;
	
	/*** 접속일 **/
	private String accedate;
	
	/*** 접속시간 **/
	private String accetime;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthtype() {
		return birthtype;
	}

	public void setBirthtype(String birthtype) {
		this.birthtype = birthtype;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getAccedate() {
		return accedate;
	}

	public void setAccedate(String accedate) {
		this.accedate = accedate;
	}

	public String getAccetime() {
		return accetime;
	}

	public void setAccetime(String accetime) {
		this.accetime = accetime;
	}
}
