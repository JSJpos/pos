package co.kr.jsj.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import co.kr.jsj.model.StoreDto;

public class StoreManager {
	private static SqlSessionFactory sqlMapper;

	// 스태틱 변수를 초기화 하기위하여
	static {
		try {
			Reader reader = Resources
					.getResourceAsReader("co/kr/jsj/mybatis/sqlConfig.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException err) {
			throw new RuntimeException("SqlSessionFactory 인스턴스 생성 실패 : " + err);
		}
	}
	
	// 전체 매장 정보 가져오기
	public static List getStoreList(){
		
		List list = null;
		SqlSession session = sqlMapper.openSession();

		list = session.selectList("getStoreList");
		session.close();
		return list;
		
	}
	
	// 해당 매장 정보 가져오기
	public static StoreDto getStoreInfo(int Store_no){
		StoreDto dto = new StoreDto();
		SqlSession session = sqlMapper.openSession();
		dto = session.selectOne("getStoreInfo", Store_no);
		session.close();
		return dto;
	}
	
	// 매장 가입
	public static void insertStore(StoreDto dto){
		SqlSession session = sqlMapper.openSession();
		session.insert("insertStore", dto);
		session.commit();
		session.close();
	}
	
	// 매장 탈퇴
	public static void deleteStore(){
		
	}
	
	// 매장 정보 수정
	public static void updateStore(){
		
	}
}
