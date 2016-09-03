package net.migry.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.migry.common.Utility;
import net.migry.dto.CoverStory;
import net.migry.dto.Search;
import net.migry.mybatis.SqlSessionFactoryManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class CoverStoryDAO {
	private SqlSessionFactory sqlMapper;
	
	public CoverStoryDAO() {
		super();
		sqlMapper = SqlSessionFactoryManager.getInstance();
	}
	
	public List<CoverStory> selectCover(Search search) {
		SqlSession ss = sqlMapper.openSession();
		List<CoverStory> list = ss.selectList("mybatis.cover.selectCover", search);
		ss.close();
		
		for(CoverStory cs : list) {
			String contents = cs.getContents();
			cs.setContents(Utility.getContent(contents));
		}
		
		return list;
	}
	
	public int countCover() throws Exception {
    	SqlSession ss = sqlMapper.openSession();
		int count = ss.selectOne("mybatis.cover.countCover");
		ss.close();
		
		return count;
    }
	
	public CoverStory selectCoverLatest() {
		SqlSession ss = sqlMapper.openSession();
		CoverStory cover = ss.selectOne("mybatis.cover.selectCoverLatest");
		ss.close();
		
		if(cover != null) {
			String contents = cover.getContents();
			cover.setContents(Utility.getContent(contents));
		}
		
		return cover;
	}
	
	public void insertCover(CoverStory dto) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.insert("mybatis.cover.insertCover", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
	
	public void deleteCover(int id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.delete("mybatis.cover.deleteCover", map);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
	
	public int getNewID() throws Exception {
		int id = 0;
		
    	SqlSession ss = sqlMapper.openSession();
		Object temp = ss.selectOne("mybatis.cover.getMaxCoverID");
		ss.close();
		
		if(temp == null) {
			id = 1;
		} else {
			id = (Integer)temp;
			id++;
		}
		
		return id;
    }
}
