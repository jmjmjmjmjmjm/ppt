package domain;

import java.util.List;

public class UserService {
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	public User 로그인(LoginDto dto) {
		return userDao.findByIdUsernameAndPassword(dto);
	}
	public int 회원가입(JoinDto dto) {
		int result=userDao.save(dto);
		return result;
	}
	public List<User> 목록(){
		return userDao.findAll();
	}
	public int 삭제(String id) {
		int result= userDao.delete(id);
		return result;
	}
}
