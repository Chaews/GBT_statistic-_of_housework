package Controller.gat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import Dao.GroupDao;
import Dto.Member;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Search() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int loginmnum = (Integer)session.getAttribute("loginmnum");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		String search = request.getParameter("searchid");
		JSONArray memberlist = GroupDao.getgroupdao().findgroup(search, loginmnum, gnum);
		response.getWriter().print(memberlist);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
