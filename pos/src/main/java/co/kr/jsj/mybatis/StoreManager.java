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

	// ����ƽ ������ �ʱ�ȭ �ϱ����Ͽ�
	static {
		try {
			Reader reader = Resources
					.getResourceAsReader("co/kr/jsj/mybatis/sqlConfig.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException err) {
			throw new RuntimeException("SqlSessionFactory �ν��Ͻ� ���� ���� : " + err);
		}
	}
	
	// ��ü ���� ���� ��������
	public static List getStoreList(){
		
		List list = null;
		SqlSession session = sqlMapper.openSession();

		list = session.selectList("getStoreList");
		session.close();
		return list;
		
	}
	
	// �ش� ���� ���� ��������
	public static StoreDto getStoreInfo(int Store_no){
		StoreDto dto = new StoreDto();
		SqlSession session = sqlMapper.openSession();
		dto = session.selectOne("getStoreInfo", Store_no);
		session.close();
		return dto;
	}
	
	// ���� ����
	public static void insertStore(StoreDto dto){
		SqlSession session = sqlMapper.openSession();
		session.insert("insertStore", dto);
		session.commit();
		session.close();
	}
	
	// ���� Ż��
	public static void deleteStore(){
		
	}
	
	// ���� ���� ����
	public static void updateStore(){
		
	}
}
