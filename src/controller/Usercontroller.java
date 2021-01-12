package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.JoinDto;
import domain.LoginDto;
import domain.User;
import domain.UserDao;
import domain.UserService;

@WebServlet("/user")
public class Usercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Usercontroller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();

		if (cmd.equals("joinForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("form/joinForm.jsp");
			dis.forward(request, response);

		} else if (cmd.equals("join")) {
			// 서비스 호출
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			JoinDto dto = new JoinDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setEmail(email);
			int result = userService.회원가입(dto);
			if (result == 1) {
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);
			} else {
				System.out.println("회원가입실패");
			}
		} else if (cmd.equals("login")) {

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginDto dto = new LoginDto();
			dto.setUsername(username);
			dto.setPassword(password);
			User userEntity = userService.로그인(dto);

			if (userEntity != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity); // 인증주체
				RequestDispatcher dis = request.getRequestDispatcher("form/sucess.jsp");
				dis.forward(request, response);

			} else {
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);

			}
		} else if (cmd.equals("userList")) {
			System.out.println("실행됨");
			List<User> users = userService.목록();

			System.out.println(users.toString());
			request.setAttribute("users", users);
			RequestDispatcher dis = request.getRequestDispatcher("form/userList.jsp");
			dis.forward(request, response);

		} else if (cmd.equals("delete")) {
			System.out.println();
			
			int result =userService.삭제(request.getParameter("id"));
			
			if (result == 1) {
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);
			} else {
				System.out.println("컨트롤러삭제막힘");
			}
		}
	}

}
