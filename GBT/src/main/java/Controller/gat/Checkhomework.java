package Controller.gat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.HomeworkDao;


@WebServlet("/Checkhomework")
public class Checkhomework extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Checkhomework() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		String hwork = request.getParameter("hwork");
		boolean result = HomeworkDao.gethomeworkdao().checkhomework(gnum, hwork);
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
