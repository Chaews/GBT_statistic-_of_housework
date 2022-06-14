package Controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MemberDao;


@WebServlet("/findid")
public class findid extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public findid() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String result = MemberDao.getmemberdao().findid(name, phone);
		response.getWriter().print(result);
		return;
//		request.setAttribute("findid", result);
//		RequestDispatcher rd = request.getRequestDispatcher("member/findid.jsp");
//		rd.forward(request, response);
	}
}
