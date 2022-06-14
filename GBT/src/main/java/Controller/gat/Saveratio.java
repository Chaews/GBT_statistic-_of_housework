package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.HomeworkDao;


@WebServlet("/Saveratio")
public class Saveratio extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Saveratio() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String[] strratioarray = request.getParameterValues("ratioarray");
		String[] strhnumarray = request.getParameterValues("hnumarray");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		int[] ratioarray = new int[strratioarray.length] ;
		int[] hnumarray = new int[strhnumarray.length];
		for(int i = 0 ; i <strratioarray.length ; i++ ) {
			ratioarray[i] = Integer.parseInt(strratioarray[i]);
		}
		for(int i = 0 ; i <strhnumarray.length ; i++ ) {
			hnumarray[i] = Integer.parseInt(strhnumarray[i]);
		}
		boolean result = HomeworkDao.gethomeworkdao().setratio(gnum, ratioarray, hnumarray);
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
