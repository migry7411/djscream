package net.migry.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryManager {    
    public static SqlSessionFactory getInstance() {
    	SqlSessionFactory sqlMapper = null;
    	String resource = "net/migry/mybatis/SqlMapConfig.xml";
        Reader reader = null;

        try {
        	reader = Resources.getResourceAsReader(resource);
        	sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        	//System.out.println("DBConnection 연결 성공");
        	reader.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        return sqlMapper;
    }
}
