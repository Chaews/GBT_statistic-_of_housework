package Dao;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import Dto.Homework;
import Dto.Member;

public class HomeworkDao extends Dao {
	
	public static HomeworkDao homeworkDao = new HomeworkDao();
	public static HomeworkDao gethomeworkdao() {return homeworkDao;}
	
	public HomeworkDao() {
		super();
	}

	public boolean addhomework(int gnum , String hwork) { // 집안일 추가
		try {
			String sql = "insert into homework (gnum,hwork) values ("+gnum+", '"+hwork+"');";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/* 스크립트에서 사용할 전체 집안일리스트 (json) */
	public JSONArray gethomework(int gnum) {
		JSONArray homeworklist = new JSONArray();
		try {
			String sql = "select * from homework where gnum = "+gnum;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject homework = new JSONObject();
				homework.put("category", rs.getString(3));
				homework.put("value", rs.getInt(4));
				homework.put("hnum", rs.getInt(1));
				homeworklist.put(homework);
			}
			return homeworklist;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONArray getratio(int gnum) {
		try {	
			ArrayList<Member> countmember = GroupDao.getgroupdao().countmember(gnum);
			JSONArray ratiolist = new JSONArray();	
			
			// 일주일 7일 계산해야함
			for(int w = 0 ; w <=6 ; w++) {
				for(int i = 0 ; i < countmember.size() ; i++) {
					JSONObject ratio = new JSONObject(); // 하루 1사람 일한 내역 객체 반복문 안에 들어가야함
					String sql = "SELECT a.*, b.hwork, b.hratio, c.mname, date_format(a.date,'%m-%d')FROM idid a join homework b on a.hnum=b.hnum join gbt.member c on a.mnum = c.mnum where a.gnum = ? and a.mnum = ? and a.date = date_sub(curdate(),interval ? day)";
					ps = con.prepareStatement(sql);
					ps.setInt(1, gnum);
					ps.setInt(2, countmember.get(i).getMnum()); // arraylist mnum 넣어서 반복문 돌려야함
					ps.setInt(3, (6-w));
					rs = ps.executeQuery();
					if(rs.next()) {
						ratio.put("Day", rs.getString(9)+"\n"+rs.getString(8));
						ratio.put(rs.getString(6), rs.getInt(7));
						while(rs.next()) {
							if(ratio.has(rs.getString(6))) {
								ratio.put(rs.getString(6), (int)ratio.get(rs.getString(6))+ rs.getInt(7));
							}
							else {
								ratio.put(rs.getString(6), rs.getInt(7));
							}
						}
					}
					else {
						sql = "SELECT date_format(date_sub(curdate(),interval ? day),'%m-%d')";
						// 날짜만 빼와서 DAO에 메소드 작성해야함
						ps = con.prepareStatement(sql);
						ps.setInt(1, (6-w));
						rs = ps.executeQuery();
						if(rs.next()) {
							ratio.put("Day", rs.getString(1)+"\n"+countmember.get(i).getMname());
						}
						// ratio.put("Day", rs.getString(9)+" "+rs.getString(8));
						// 정렬 object도 넣어야함
					}
					ratiolist.put(ratio);
				}
			}
			return ratiolist;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONArray getratiow(int gnum) {
		try {
			// 그룹내 mnum 빼오기		
			ArrayList<Member> countmember = GroupDao.getgroupdao().countmember(gnum);
			JSONArray ratiolist = new JSONArray();	
			
			for(int w = 0 ; w <=3 ; w++) { // 날짜 표시 마무리, 일 안한날도 날짜표시 마무리해야함
				for(int i = 0 ; i < countmember.size() ; i++) {
					JSONObject ratio = new JSONObject(); // 하루 1사람 일한 내역 객체 반복문 안에 들어가야함
					//String sql = "SELECT a.*, b.hwork, b.hratio, c.mname, date_format(date_sub(curdate(),interval ? week),'%m-%d'), date_format(date_sub(curdate(),interval ? week),'%m-%d') FROM idid a join homework b on a.hnum=b.hnum join gbt.member c on a.mnum = c.mnum where a.gnum = ? and a.mnum = ? and a.date between date_sub(curdate(),interval ? week) and date_sub(curdate(),interval ? week) order by a.date asc, a.mnum asc";
					String sql = "SELECT a.*, b.hwork, b.hratio, c.mname FROM idid a join homework b on a.hnum=b.hnum join gbt.member c on a.mnum = c.mnum where a.gnum = ? and a.mnum = ? and week(a.date)=week(date_sub(curdate(),interval ? week)) order by a.date asc, a.mnum asc";
					ps = con.prepareStatement(sql);
					ps.setInt(1, gnum);
					ps.setInt(2, countmember.get(i).getMnum()); // arraylist mnum 넣어서 반복문 돌려야함
					ps.setInt(3, (3-w));
					rs = ps.executeQuery();
					if(rs.next()) {
						if(w==0) {
							ratio.put("Day","3주전\n"+rs.getString(8));
						}
						else if(w==1) {
							ratio.put("Day","2주전\n"+rs.getString(8));
						}
						else if(w==2) {
							ratio.put("Day","지난주\n"+rs.getString(8));
						}
						else if(w==3) {
							ratio.put("Day","이번주\n"+rs.getString(8));
						}
						ratio.put(rs.getString(6), rs.getInt(7));
						while(rs.next()) {
							if(ratio.has(rs.getString(6))) {
								ratio.put(rs.getString(6), (int)ratio.get(rs.getString(6))+ rs.getInt(7));
							}
							else {
								ratio.put(rs.getString(6), rs.getInt(7));
							}
						}
					}
					else {
						if(w==0) {
							ratio.put("Day","3주전\n"+countmember.get(i).getMname());
						}
						else if(w==1) {
							ratio.put("Day","2주전\n"+countmember.get(i).getMname());
						}
						else if(w==2) {
							ratio.put("Day","지난주\n"+countmember.get(i).getMname());
						}
						else if(w==3) {
							ratio.put("Day","이번주\n"+countmember.get(i).getMname());
						}						
					}
					ratiolist.put(ratio);
				}
			}
			return ratiolist;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONArray getratiom(int gnum) {
		try {
			// 그룹내 mnum 빼오기		
			ArrayList<Member> countmember = GroupDao.getgroupdao().countmember(gnum);
			JSONArray ratiolist = new JSONArray();	
			
			for(int w = 0 ; w <=3 ; w++) { // 날짜 표시 마무리, 일 안한날도 날짜표시 마무리해야함
				for(int i = 0 ; i < countmember.size() ; i++) {
					JSONObject ratio = new JSONObject(); // 하루 1사람 일한 내역 객체 반복문 안에 들어가야함
					//String sql = "SELECT a.*, b.hwork, b.hratio, c.mname, date_format(date_sub(curdate(),interval ? month),'%m-%d'), date_format(date_sub(curdate(),interval ? month),'%m-%d') FROM idid a join homework b on a.hnum=b.hnum join gbt.member c on a.mnum = c.mnum where a.gnum = ? and a.mnum = ? and a.date between date_sub(curdate(),interval ? month) and date_sub(curdate(),interval ? month) order by a.date asc, a.mnum asc";
					String sql = "SELECT a.*, b.hwork, b.hratio, c.mname, date_format(date_sub(curdate(),interval ? month),'%m월') FROM idid a join homework b on a.hnum=b.hnum join gbt.member c on a.mnum = c.mnum where a.gnum = ? and a.mnum = ? and month(a.date)=month(date_sub(curdate(),interval ? month)) order by a.date asc, a.mnum asc";
					ps = con.prepareStatement(sql);
					ps.setInt(1, (3-w));
					ps.setInt(2, gnum);
					ps.setInt(3, countmember.get(i).getMnum()); // arraylist mnum 넣어서 반복문 돌려야함
					ps.setInt(4, (3-w));
					rs = ps.executeQuery();
					if(rs.next()) {
						ratio.put("Day", rs.getString(9)+"\n"+rs.getString(8));
						ratio.put(rs.getString(6), rs.getInt(7));
						while(rs.next()) {
							if(ratio.has(rs.getString(6))) {
								ratio.put(rs.getString(6), (int)ratio.get(rs.getString(6))+ rs.getInt(7));
							}
							else {
								ratio.put(rs.getString(6), rs.getInt(7));
							}
						}
					}
					else {
						sql = "SELECT date_format(date_sub(curdate(),interval ? month),'%m월')";
						ps = con.prepareStatement(sql);
						ps.setInt(1, (3-w));
						rs = ps.executeQuery();
						if(rs.next()) {
							ratio.put("Day", rs.getString(1)+"\n"+countmember.get(i).getMname());
						}
					}
					ratiolist.put(ratio);
				}
			}
			return ratiolist;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

//	public String getratio(int gnum) { // html 테스트
//		try {
//			String html = "[{\"Day\" : \"05-18\" ";
//		
//			String sql = "SELECT a.*, b.hwork, b.hratio FROM idid a left join homework b on a.hnum=b.hnum where a.gnum = "+gnum;
//			ps = con.prepareStatement(sql);
//			rs = ps.executeQuery();
//
//			while(rs.next()) {
//				html += ", \"" +rs.getString(6)+ "\" : "+rs.getInt(7)+"";
//			}
//			
//			html += "}]";
//			
//			System.out.println(html);
//			return html;
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	// 집안일 추가에서 사용할 집안일리스트
	public ArrayList<Homework> gethomework2 (int gnum){
		try {
			ArrayList<Homework> homeworklist = new ArrayList<Homework>();
			String sql = "select * from homework where gnum = " +gnum;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Homework homework = new Homework(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				homeworklist.add(homework);
			}
			if(homeworklist.isEmpty()) {
				return null;
			}
			return homeworklist;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean setratio(int gnum, int[] ratioarray, int[] hnumarray) {
		try {
			for(int i = 0 ; i < ratioarray.length ; i++) {
				String sql = "update homework set hratio = "+ratioarray[i]+" where gnum = "+gnum+" and  hnum = "+hnumarray[i];
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
			}
			return true;	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updatename (int gnum, int hnum, String newname) {
		try {
			String sql = "update homework set hwork='"+newname+"' where gnum="+gnum+" and hnum="+hnum;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deletehomework (int gnum, int hnum) {
		try {
			String sql = "delete from homework where gnum="+gnum+" and hnum ="+hnum;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveidid (int gnum, int mnum, int[] checked) {
		try {
			for(Integer temp : checked) {
				String sql = "insert into idid (gnum,hnum,mnum) values ("+gnum+","+temp+","+mnum+")";
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
			}
			return true;	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkhomework (int gnum, String hwork) {
		try {
			String sql = "select * from homework where gnum = ? and hwork = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, gnum);
			ps.setString(2, hwork);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

/////////////////////////// 통계 구하기 백업 /////////////////////////////////////
//public JSONArray getratio(int gnum) {
//	try {
//		// 그룹내 mnum 빼오기		
//		ArrayList<Integer> countmember = GroupDao.getgroupdao().countmember(gnum);
//		
//		JSONArray ratiolist = new JSONArray();	
//		JSONObject ratio = new JSONObject();
//		JSONObject ratio2 = new JSONObject();
//		
//		String sql = "SELECT a.*, b.hwork, b.hratio, c.mname, date_format(a.date,'%m-%d' )FROM idid a join homework b on a.hnum=b.hnum join gbt.member c on a.mnum = c.mnum where a.gnum = ? and a.date = date_sub(curdate(),interval 1 day) order by a.date asc, a.mnum asc";
//		ps = con.prepareStatement(sql);
//		ps.setInt(1, gnum);
//		rs = ps.executeQuery();
//		if(rs.next()) {
//			int mnum = rs.getInt(5);
//			ratio.put("Day", rs.getString(9)+" "+rs.getString(8));
//			ratio.put(rs.getString(6), rs.getInt(7));
//			while(rs.next()) {
//				if(rs.getInt(5)==mnum) {
//					if(ratio.has(rs.getString(6))) {
//						ratio.put(rs.getString(6), (int)ratio.get(rs.getString(6))+ rs.getInt(7));
//					}
//					else {
//						ratio.put(rs.getString(6), rs.getInt(7));
//					}
//				}
//				else {
//					ratio2.put("Day", rs.getString(9)+" "+rs.getString(8));
//					if(ratio2.has(rs.getString(6))) {
//						ratio2.put(rs.getString(6), (int)ratio2.get(rs.getString(6))+ rs.getInt(7));
//					}
//					else {
//						ratio2.put(rs.getString(6), rs.getInt(7));
//					}
//				}
//			}
//		}
//		ratiolist.put(ratio);
//		ratiolist.put(ratio2);
//		System.out.println(ratiolist.toString());
//		return ratiolist;
//	}
//	catch(Exception e) {
//		e.printStackTrace();
//	}
//	return null;
//}
