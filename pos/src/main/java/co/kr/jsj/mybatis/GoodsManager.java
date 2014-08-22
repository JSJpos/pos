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
	
	// 해당 매장의 전체 상품 정보 가져오기
	public static List getGoodsList(int Store_no){
		
		List list = null;
		SqlSession session = sqlMapper.openSession();

		list = session.selectList("getGoodsList", Store_no);
		session.close();
		return list;
		
	}
	
	// 상품 등록
	public static void insertGoods(GoodsDto dto){
		SqlSession session = sqlMapper.openSession();
		session.insert("insertGoods", dto);
		session.commit();
		session.close();
	}
	
	// 상품 수정
	public static void updateGoods(GoodsDto dto){
		SqlSession session = sqlMapper.openSession();
		session.update("updateGoods", dto);
		session.commit();
		session.close();
	}
	
	// 상품 삭제
	public static void deleteGoods(int Goods_no){
		SqlSession session = sqlMapper.openSession();
		session.delete("deleteGoods", Goods_no);
		session.commit();
		session.close();
	}
	
	// 상품 카운터
	
}
