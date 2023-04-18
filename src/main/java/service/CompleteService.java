package service;

import DAO.CompleteDAO;

public class CompleteService {

	// 서비스를 외부에서도 사용할 수 있도록 오브젝트를 생성해줍니다.
	public static CompleteService instance = new CompleteService();
	CompleteDAO completeDAO = new CompleteDAO();  // 객체 생성
	
	private CompleteService() {}
	
	// 해당클래스의 오브젝트를 불러와 사용하기 위해 쓰이는 함수입니다.
	public static CompleteService getInstance() {
		return instance;
	}
	
	//
	
}
