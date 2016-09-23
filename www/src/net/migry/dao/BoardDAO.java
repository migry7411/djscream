package net.migry.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.migry.common.Encrypt;
import net.migry.dto.Board;
import net.migry.dto.BoardReply;
import net.migry.dto.Search;
import net.migry.mybatis.SqlSessionFactoryManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BoardDAO {
	private SqlSessionFactory sqlMapper;
	
	public BoardDAO() {
		super();
		sqlMapper = SqlSessionFactoryManager.getInstance();
	}
	
	public List<Board> selectBoard(Search search) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
		List<Board> list = ss.selectList("mybatis.board.selectBoard", search);
		ss.close();
		
		return list;
    }
	
	public int countBoard(Search search) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
		int count = ss.selectOne("mybatis.board.countBoard", search);
		ss.close();
		
		return count;
    }
	
	public Board selectBoardOne(int id) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	
    	SqlSession ss = sqlMapper.openSession();
		Board board = ss.selectOne("mybatis.board.selectBoardOne", map);
		ss.close();
		
		if(board != null) {
			String pwd = board.getPasswd();
			
			board.setPasswd(Encrypt.decrypt(pwd));
		}
		
		return board;
    }
    
    public void insertBoard(Board dto) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	String pwd = dto.getPasswd();
		dto.setPasswd(Encrypt.encrypt(pwd));
		
    	int seq = ss.insert("mybatis.board.insertBoard", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public void updateBoard(Board dto) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	String pwd = dto.getPasswd();		
		dto.setPasswd(Encrypt.encrypt(pwd));
    	
    	int seq = ss.update("mybatis.board.updateBoard", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public void deleteBoard(int id) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.update("mybatis.board.deleteBoard", map);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public int getNewBoardID() throws Exception {
		int id = 0;
		
    	SqlSession ss = sqlMapper.openSession();
		Object temp = ss.selectOne("mybatis.board.getMaxBoardID");
		ss.close();
		
		if(temp == null) {
			id = 1;
		} else {
			id = (Integer)temp;
			id++;
		}
		
		return id;
    }
    
    public void upReadCount(int id) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.update("mybatis.board.upReadCount", map);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public List<BoardReply> selectBoardReply(Search search) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
		List<BoardReply> list = ss.selectList("mybatis.board.selectBoardReply", search);
		ss.close();
		
		return list;
    }
    
    public void insertBoardReply(BoardReply dto) throws Exception {
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
		
    	int seq = ss.insert("mybatis.board.insertBoardReply", dto);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public void deleteBoardReply(int id) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	
    	SqlSession ss = sqlMapper.openSession();
    	ss.commit(false);
    	
    	int seq = ss.delete("mybatis.board.deleteBoardReply", map);
    	
    	if(seq > 0) {
    		ss.commit();
    	} else {
    		ss.rollback();
    	}
    	
    	ss.close();
    }
    
    public List<Board> selectLatestBoard() throws Exception {
    	SqlSession ss = sqlMapper.openSession();
		List<Board> list = ss.selectList("mybatis.board.selectLatestBoard");
		ss.close();
		
		return list;
    }
    
    public int countBoardReply(String boardid) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("boardid", boardid);
    	
    	SqlSession ss = sqlMapper.openSession();
		int count = ss.selectOne("mybatis.board.countBoardReply", map);
		ss.close();
		
		return count;
    }
    
    public int getNewBoardReplyID() throws Exception {
		int id = 0;
		
    	SqlSession ss = sqlMapper.openSession();
		Object temp = ss.selectOne("mybatis.board.getMaxBoardReplyID");
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
