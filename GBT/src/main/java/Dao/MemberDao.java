package Dao;

import Dto.Member;

public class MemberDao extends Dao{
	
	public static MemberDao memberdao = new MemberDao();
	public static MemberDao getmemberdao() {return memberdao;}
	
	public MemberDao() {
		super();
	}
	
	public boolean signup (Member member) { // 회원가입
		try {
			String sql = "insert into member (mid,mpassword,mname,mphone) values (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getMid());
			ps.setString(2, member.getMpassword());
			ps.setString(3, member.getMname());
			ps.setString(4, member.getMphone());
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int login (String id, String password) { // 로그인
		try {
			String sql = "select mnum from member where mid=? and mpassword=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String findid (String name, String phone) { // 아이디 찾기
		try {
			String sql = "select mid from member where mname=? and mphone=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String findpw (String name, String phone, String id) { // 비번찾기
		try {
			String sql = "select mpassword from member where mname=? and mphone=? and mid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Member updateinfo(int mnum) { // 개인정보 출력
		try {
			String sql = "select * from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			if(rs.next()) {
				Member member = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				return member;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateinfo2(String name, String password, String phone, int mnum) { // 개인정보 수정
		try {
			String sql = "update member set mname=?, mpassword=?, mphone=? where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, phone);
			ps.setInt(4, mnum);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(int mnum) { // 회원 탈퇴
		try {
			String sql = "delete from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean idcheck(String mid) { // 아이디 중복체크
		try {
			String sql = "select mid from member where mid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
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
