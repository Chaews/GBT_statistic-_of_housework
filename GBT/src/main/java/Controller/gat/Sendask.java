package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.GroupDao;

@WebServlet("/Sendask")
public class Sendask extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Sendask() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		HttpSession session = request.getSession();
		int loginmnum = (Integer)session.getAttribute("loginmnum");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		int tomnum = Integer.parseInt(request.getParameter("tomnum"));

		boolean check = GroupDao.getgroupdao().checkask(tomnum, gnum);
		if(check) {
			response.getWriter().print(3); return;
		}
		// 유효성검사 하고 print 3
		boolean result = GroupDao.getgroupdao().askgroup(loginmnum, tomnum, gnum);
		if(result) {
			response.getWriter().print(1);
		}
		else {
			response.getWriter().print(2);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
