package Controller.gat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.GroupDao;
import Dto.Groupmember;

@WebServlet("/Gmlist")
public class Gmlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Gmlist() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		ArrayList<Groupmember> gmlist = GroupDao.getgroupdao().gmlist(gnum);
		String gmliststr = "";
		if(!gmlist.isEmpty()) {
			for(Groupmember temp : gmlist) {
				gmliststr += temp.getMname()+",";
			}
			response.getWriter().print(gmliststr);
		}
		else {
			response.getWriter().print(gmliststr);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
