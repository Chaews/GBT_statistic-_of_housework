package Controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.MemberDao;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Update() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String mpassword = request.getParameter("mpassword");
		String mname = request.getParameter("mname");
		String mphone = request.getParameter("mphone");
		int mnum = (Integer)session.getAttribute("loginmnum");
		boolean result = MemberDao.getmemberdao().updateinfo2(mname, mpassword, mphone, mnum);
		if(result) {
			response.getWriter().print(1);
		}
		else {
			response.getWriter().print(2);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
