package Controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MemberDao;

@WebServlet("/findpw")
public class findpw extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public findpw() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String mid = request.getParameter("mid");
		String result = MemberDao.getmemberdao().findpw(name, phone, mid);
		response.getWriter().print(result);
		return;
		
	}

}
