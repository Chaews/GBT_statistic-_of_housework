package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.HomeworkDao;

@WebServlet("/Addhomework")
public class Addhomework extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Addhomework() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String[] hworks = request.getParameterValues("hworks");
//		if(hworks==null) {
//			return;
//		}
		String hwork = request.getParameter("hwork");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		boolean result = HomeworkDao.gethomeworkdao().addhomework(gnum, hwork);
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
