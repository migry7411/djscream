package net.migry.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.migry.common.Encrypt;
import net.migry.dto.Member;
import net.migry.dto.Search;
import net.migry.mybatis.SqlSessionFactoryManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDAO {
	private SqlSessionFactory sqlMapper;
	
	public MemberDAO() {
		super();
		sqlMapper = SqlSessionFactoryManager.getInstance();
	}
	
    public List<Member> selectMember(Search search) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
		List<Member> list = ss.selectList("mybatis.member.selectMember", search);
		ss.close();
		
		for(Member m : list) {
			String pwd = m.getPassword();
			String email = m.getEmail();
			
			m.setPassword(Encrypt.decrypt(pwd));
			m.setEmail(Encrypt.decrypt(email));
		}
		
		return list;
    }
    
    public Member selectMemberOne(String userid) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userid", userid);
    	
    	SqlSession ss = sqlMapper.openSession();
		Member member = ss.selectOne("mybatis.member.selectMemberOne", map);
		ss.close();
		
		if(member != null) {
			String pwd = member.getPassword();
			String email = member.getEmail();
			
			member.setPassword(Encrypt.decrypt(pwd));
			member.setEmail(Encrypt.decrypt(email));
		}
		
		return member;
    }
    
    public void insertMember(Member dto) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	String pwd = dto.getPassword();
		String email = dto.getEmail();
		
		dto.setPassword(Encrypt.encrypt(pwd));
		dto.setEmail(Encrypt.encrypt(email));
    	
    	int seq = ss.insert("mybatis.member.insertMember", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public void updateMember(Member dto) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	String pwd = dto.getPassword();
		String email = dto.getEmail();
		
		dto.setPassword(Encrypt.encrypt(pwd));
		dto.setEmail(Encrypt.encrypt(email));
    	
    	int seq = ss.update("mybatis.member.updateMember", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public void deleteMember(String userid) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userid", userid);
    	
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.update("mybatis.member.deleteMember", map);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public void updateMemberAccess(String userid) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userid", userid);
    	
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.update("mybatis.member.updateMemberAccess", map);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public int countMember(Search search) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
		int count = ss.selectOne("mybatis.member.countMember", search);
		ss.close();
		
		return count;
    }
    
    public String findMember(String column, String email, String userid, String username) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userid", userid);
    	map.put("username", username);
    	map.put("email", Encrypt.encrypt(email));
    	map.put("column", column);
    	
    	SqlSession ss = sqlMapper.openSession();
		String value = ss.selectOne("mybatis.member.findMember", map);
		ss.close();
		
		if(column == "PASSWORD" && value != null) {
			value = Encrypt.decrypt(value);
		}
		
		return value;
    }
    
    public String checkMember(String column, String value) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("column", column);
    	
    	if(column == "EMAIL") {
    		map.put("value",  Encrypt.encrypt(value));
    	} else {
    		map.put("value", value);
    	}
    	
    	SqlSession ss = sqlMapper.openSession();
		String id = ss.selectOne("mybatis.member.checkMember", map);
		ss.close();
		
		return id;
    }
}
