package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.HomeworkDao;


@WebServlet("/Revisehw")
public class Revisehw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Revisehw() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String type = request.getParameter("type");
			int gnum = Integer.parseInt(request.getParameter("gnum"));
			int hnum = Integer.parseInt(request.getParameter("hnum"));
			if(type.equals("update")) {
				String newname = request.getParameter("newname");
				boolean result = HomeworkDao.gethomeworkdao().updatename(gnum, hnum, newname);
				if(result) {
					response.getWriter().print(1);
				}
				else {
					response.getWriter().print(2);
				}
			}
			else if(type.equals("delete")) {
				boolean result = HomeworkDao.gethomeworkdao().deletehomework(gnum, hnum);
				if(result) {
					response.getWriter().print(1);
				}
				else {
					response.getWriter().print(2);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
