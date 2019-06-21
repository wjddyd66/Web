package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.mybatis.SqlMapConfig;

public class ProcessDao {
	private static ProcessDao dao = new ProcessDao();
	public static ProcessDao getInstance() {
		return dao;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public List<DataDto> selectDataAll() throws SQLException{
		SqlSession sqlSession = factory.openSession(); 
		List list = sqlSession.selectList("selectDataAll");
		sqlSession.close();
		return list;
	}
	
	public DataDto selectDataPart(String code) throws Exception{
		SqlSession sqlSession = factory.openSession();
		DataDto dto = sqlSession.selectOne("selectDataById", code);
		sqlSession.close();
		return dto;
	}
	
	public void insData(DataDto dto) throws Exception{ 
//		SqlSession sqlSession = factory.openSession(); //수동
//		sqlSession.insert("insertData", dto);
//		sqlSession.commit();
//		sqlSession.close();
		
		SqlSession sqlSession = factory.openSession(true); //자동
		sqlSession.insert("insertData", dto);		
		sqlSession.close();
		
	}
	
	public void upData(DataDto dto) throws Exception{ 
		SqlSession sqlSession = factory.openSession(true);
		sqlSession.update("updateData", dto);		
		sqlSession.close();	
		
	}
	
	public boolean delData(int arg) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();		
		
		try {
			int count = sqlSession.delete("deleteData", arg);
			if(count > 0) b = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("del err : " + e);
			sqlSession.rollback();
		}finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
	
	
}
