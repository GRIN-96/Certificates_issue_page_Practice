package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import DAO.CompleteDAO;
import DTO.CertificatesDTO;
import DTO.CompleteDTO;

public class CompleteService {

	// 서비스를 외부에서도 사용할 수 있도록 오브젝트를 생성해줍니다.
	public static CompleteService instance = new CompleteService();
	CompleteDAO completeDAO = new CompleteDAO();  // 객체 생성
	
	private CompleteService() {}
	
	// 해당클래스의 오브젝트를 불러와 사용하기 위해 쓰이는 함수입니다.
	public static CompleteService getInstance() {
		return instance;
	}
	
	// insert complete table
	public boolean insertComplete(ArrayList<CompleteDTO> lists) {
		
		try {
			if (completeDAO.insertComplete(lists)) {
				return true;
			}else {
			return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 이수자 정보 삭제하기
	public boolean deleteCom(int complete_id) {
		
		try {
			if (completeDAO.deleteCom(complete_id)) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// 나의 발급가능한 이수증 리스트 불러오기
	public ArrayList<CertificatesDTO> allMyList(String user_id) {
		
		ArrayList<CertificatesDTO> lists = new ArrayList<CertificatesDTO>();
		
		try {
			lists = completeDAO.allMyList(user_id);
			return lists;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
				
		return lists;
	}
	
	// pdf 생성을 위한 이수증 가져오기
	public CertificatesDTO certificatesInfo(String user_id, String board_id) {
		
		CertificatesDTO certificatesDTO = new CertificatesDTO();
		
		try {
			certificatesDTO = completeDAO.certificatesInfo(user_id, board_id);
			return certificatesDTO;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return certificatesDTO;
		
	}
	
	public ArrayList<CertificatesDTO> certificatesAll(int board_id) throws ClassNotFoundException, SQLException {
		
		ArrayList<CertificatesDTO> lists = new ArrayList<CertificatesDTO>();
		lists = completeDAO.certificatesAll(board_id);
		
		return lists;
	}
	
}
