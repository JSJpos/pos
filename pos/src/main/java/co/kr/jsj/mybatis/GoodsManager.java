package co.kr.jsj.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import co.kr.jsj.model.GoodsDto;

public class GoodsManager {
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
	
	// �ش� ������ ��ü ��ǰ ���� ��������
	public static List getGoodsList(int Store_no){
		
		List list = null;
		SqlSession session = sqlMapper.openSession();

		list = session.selectList("getGoodsList", Store_no);
		session.close();
		return list;
		
	}
	
	// ��ǰ ���
	public static void insertGoods(GoodsDto dto){
		SqlSession session = sqlMapper.openSession();
		session.insert("insertGoods", dto);
		session.commit();
		session.close();
	}
	
	// ��ǰ ����
	public static void updateGoods(GoodsDto dto){
		SqlSession session = sqlMapper.openSession();
		session.update("updateGoods", dto);
		session.commit();
		session.close();
	}
	
	// ��ǰ ����
	public static void deleteGoods(int Goods_no){
		SqlSession session = sqlMapper.openSession();
		session.delete("deleteGoods", Goods_no);
		session.commit();
		session.close();
	}
	
	// ��ǰ ī����
	
}
