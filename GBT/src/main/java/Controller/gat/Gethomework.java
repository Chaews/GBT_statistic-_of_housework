package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import Dao.HomeworkDao;

@WebServlet("/Gethomework")
public class Gethomework extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Gethomework() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		JSONArray homeworklist = HomeworkDao.gethomeworkdao().gethomework(gnum);
		response.getWriter().print(homeworklist);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
