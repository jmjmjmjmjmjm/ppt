package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DB;

public class UserDao {

	public User findByIdUsernameAndPassword(LoginDto dto) {
		String sql = "SELECT id,role,username,password FROM user where username = ? and password =?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			System.out.println(dto.getUsername());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				User user = User.builder().id(rs.getInt("id")).role(rs.getString("role"))
						.username(rs.getString("username")).password("password").build();

				return user;
			}

		} catch (Exception e) {
			System.out.println("로그인실패");
		}
		return null;
	}

	public int save(JoinDto dto) {

		String sql = "INSERT INTO user(username, password, email, role) VALUES(?,?,?,'user')";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			int result = pstmt.executeUpdate();
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	public List<User> findAll() {
		String sql = "SELECT * FROM  user ORDER BY id"; // 0,4 4,4 8,4
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> boards = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// Persistence API
			while (rs.next()) { // 커서를 이동하는 함수
				User user = User.builder().id(rs.getInt("id")).role(rs.getString("role"))
						.username(rs.getString("username")).password("password").email(rs.getString("email")).build();
				boards.add(user);
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int delete(String id) {
		String sql = "delete FROM user where username=?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		
		System.out.println(id);
//		if (id.equals("1")) {
//			try {
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, id);
//				System.out.println("삭제완료");
//			} catch (SQLException e) {
//				System.out.println("디에이오 삭제막힘");
//			}
//		}
		return -1;
	}
}
