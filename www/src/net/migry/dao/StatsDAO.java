package net.migry.dao;

import java.util.Map;
import java.util.List;

import net.migry.mybatis.SqlSessionFactoryManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class StatsDAO {
	private SqlSessionFactory sqlMapper;

	public StatsDAO() {
		super();
		sqlMapper = SqlSessionFactoryManager.getInstance();
	}
	
	public List<Map<String, Object>> selectBoardStats() {
		SqlSession ss = sqlMapper.openSession();
		List<Map<String, Object>> list = ss.selectList("mybatis.stats.selectBoardStats");
		ss.close();
		
		return list;
	}
	
}
