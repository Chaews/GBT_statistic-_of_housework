package Controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.MemberDao;
import Dto.Member;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int mnum = (Integer)session.getAttribute("loginmnum");
		String password = request.getParameter("input");
		Member member = MemberDao.getmemberdao().updateinfo(mnum);
		if(password.equals(member.getMpassword())) {
			session.invalidate();
			boolean result = MemberDao.getmemberdao().delete(mnum);
			if(result){
				response.getWriter().print(1);
			}
			else {
				response.getWriter().print(2);
			}
		}
		else {
			response.getWriter().print(3);
		}
		
		

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
