package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.GroupDao;

@WebServlet("/Makegroup")
public class Makegroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Makegroup() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String gname = request.getParameter("gname");
		int mnum = Integer.parseInt(request.getParameter("mnum"));
		boolean result = GroupDao.getgroupdao().makegroup(gname,mnum);
		if(result) {
			response.getWriter().print(1);
		}
		else {
			response.getWriter().print(2);
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
