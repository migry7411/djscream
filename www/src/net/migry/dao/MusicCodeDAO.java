package net.migry.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.migry.dto.MusicCode;
import net.migry.mybatis.SqlSessionFactoryManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MusicCodeDAO {
	private SqlSessionFactory sqlMapper;
	
	public MusicCodeDAO() {
		super();
		sqlMapper = SqlSessionFactoryManager.getInstance();
	}
	
	public List<MusicCode> selectMusicCode(String mode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("mode", mode);
    	
    	SqlSession ss = sqlMapper.openSession();
		List<MusicCode> list = ss.selectList("mybatis.musiccode.selectMusicCode", map);
		ss.close();
		
		return list;
	}
	
	public MusicCode selectMusicCodeOne(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	
    	SqlSession ss = sqlMapper.openSession();
    	MusicCode musicCode = ss.selectOne("mybatis.musiccode.selectMusicCodeOne", map);
		ss.close();
		
		return musicCode;
	}
	
	public void insertMusicCode(MusicCode dto) throws Exception {
		SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.insert("mybatis.musiccode.insertMusicCode", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
	}
	
	public void updateMusicCode(MusicCode dto) throws Exception {
		SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.insert("mybatis.musiccode.updateMusicCode", dto);
    	
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
		String maxID = ss.selectOne("mybatis.musiccode.getMaxMusicCodeID");
		
		if(maxID == null) {
			newID = "M0000";
		} else {
			int cnt = Integer.parseInt(maxID.substring(1));
			cnt++;
			newID = "M" + String.format("%04d", cnt);
		}
		
		return newID;
	}
}
