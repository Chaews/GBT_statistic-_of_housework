package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import Dao.HomeworkDao;

@WebServlet("/Getratio")
public class Getratio extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Getratio() {
        super();

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		int type = Integer.parseInt(request.getParameter("type"));
		JSONArray ratio = new JSONArray();
		if(type==1) {
			ratio = HomeworkDao.gethomeworkdao().getratio(gnum);
		}
		else if(type==2) {
			ratio = HomeworkDao.gethomeworkdao().getratiow(gnum);
		}
		else if(type==3) {
			ratio = HomeworkDao.gethomeworkdao().getratiom(gnum);
		}
		response.setContentType("application/json");
		response.getWriter().print(ratio);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
