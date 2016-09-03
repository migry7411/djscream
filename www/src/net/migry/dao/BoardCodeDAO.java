package net.migry.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.migry.dto.BoardCode;
import net.migry.mybatis.SqlSessionFactoryManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BoardCodeDAO {
	private SqlSessionFactory sqlMapper;
	
	public BoardCodeDAO() {
		super();
		sqlMapper = SqlSessionFactoryManager.getInstance();
	}
	
	public List<BoardCode> selectBoardCode(String mode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("mode", mode);
    	
    	SqlSession ss = sqlMapper.openSession();
		List<BoardCode> list = ss.selectList("mybatis.boardcode.selectBoardCode", map);
		ss.close();
		
		return list;
	}
	
	public BoardCode selectBoardCodeOne(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	
    	SqlSession ss = sqlMapper.openSession();
		BoardCode boardCode = ss.selectOne("mybatis.boardcode.selectBoardCodeOne", map);
		ss.close();
		
		return boardCode;
	}
	
	public void insertBoardCode(BoardCode dto) throws Exception {
		SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.insert("mybatis.boardcode.insertBoardCode", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
	}
	
	public void updateBoardCode(BoardCode dto) throws Exception {
		SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.insert("mybatis.boardcode.updateBoardCode", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
	}
	
	public String getNewID() throws Exception {
		String newID = "";
		
		SqlSession ss = sqlMapper.openSession();
		String maxID = ss.selectOne("mybatis.boardcode.getMaxBoardCodeID");
		
		if(maxID == null) {
			newID = "B0000";
		} else {
			int cnt = Integer.parseInt(maxID.substring(1));
			cnt++;
			newID = "B" + String.format("%04d", cnt);
		}
		
		return newID;
	}
}
