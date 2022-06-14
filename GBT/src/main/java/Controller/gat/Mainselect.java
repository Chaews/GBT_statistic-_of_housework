package Controller.gat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.HomeworkDao;
import Dto.Homework;

@WebServlet("/Mainselect")
public class Mainselect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Mainselect() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");		
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		ArrayList<Homework> list = HomeworkDao.gethomeworkdao().gethomework2(gnum);
		PrintWriter out = response.getWriter();
		String html = "" ;
		if(list==null) {
			html = "등록된 집안일이 없습니다.";
		}
		else {
			html += "<div class=\"row\"><form id=\"sendididform\">";
			for(Homework temp : list) {
				html += "<div class=\"form-check my-3 ms-4\"><input class=\"form-check-input\" type=\"checkbox\" class=\"ididclass\" value=\""+temp.getHnum()+"\" name=\"ididname\" id=\""+temp.getHnum()+"\"><label class=\"form-check-label\" for=\""+temp.getHnum()+"\">"+temp.getHwork()+"</label></div>";
			}
			html += "<div class=\"row my-2\"><div class=\"col-lg-6 offset-lg-6\"><button type=\"button\" class=\"btn btnboxconfirm\" onclick=\"idid()\">내가 했어요!</button></div></div></form></div>";
		}
		out.print(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
