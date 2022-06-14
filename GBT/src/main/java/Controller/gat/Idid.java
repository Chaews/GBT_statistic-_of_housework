package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.HomeworkDao;

@WebServlet("/Idid")
public class Idid extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Idid() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int mnum = (Integer)session.getAttribute("loginmnum");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		String[] checkedstr = request.getParameterValues("checked");
		int[] checked = new int[checkedstr.length];
		for(int i = 0 ; i <checkedstr.length ; i++) {
			checked[i] = Integer.parseInt(checkedstr[i]);
		}
		boolean result = HomeworkDao.gethomeworkdao().saveidid(gnum, mnum, checked);
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
