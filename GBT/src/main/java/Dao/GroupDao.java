package Dao;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import Dto.Asked;
import Dto.Group;
import Dto.Groupmember;
import Dto.Member;

public class GroupDao extends Dao{
	
	public static GroupDao groupdao = new GroupDao();
	public static GroupDao getgroupdao() {return groupdao;}
	
	public GroupDao() {
		super();
	}
	
	public JSONArray findgroup(String search,int loginmnum,int gnum){ // 회원검색
		JSONArray memberlist = new JSONArray();
		try {
			String sql = "SELECT a.* , b.gnum FROM gbt.member a left join groupmember b on a.mnum=b.mnum where a.mnum not in(?) and (mphone like '%"+search+"%')";
			ps = con.prepareStatement(sql);
			ps.setInt(1, loginmnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(6)==gnum) {
				}
				else {
					JSONObject member2 = new JSONObject();
					member2.put("mnum", rs.getInt(1));
					member2.put("mid", rs.getString(2));
					member2.put("mname", rs.getString(4));
					member2.put("mphone", rs.getString(5));
					memberlist.put(member2);
				}
			}		
			return memberlist;		
		}catch(Exception e) {}		
		return null;
	}
	
	public boolean askgroup(int loginmnum, int tomnum, int gnum) { // 그룹요청
		try {
				String sql = "insert into askgroup(frommnum,tomnum,gnum) values(?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, loginmnum);
				ps.setInt(2, tomnum);
				ps.setInt(3, gnum);
				ps.executeUpdate();
				return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkask(int tomnum, int gnum) {  // 그룹요청 유효성검사
		try {
			String sql = "select * from askgroup where gnum=? and tomnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, gnum);
			ps.setInt(2, tomnum);
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
	
	public ArrayList<Asked> asked(int mnum) { // 친구요청내역
		try {
			ArrayList<Asked> askedlist = new ArrayList<Asked>();
			String sql = "select a.agnum, a.frommnum, a.tomnum, b.gname, a.mid, a.gnum from (select agnum, frommnum, tomnum, mid, gnum from askgroup a left join gbt.member b on a.frommnum=b.mnum) a left join gbt.group b on a.gnum=b.gnum where tomnum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				Asked asked = new Asked(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				askedlist.add(asked);
			}
			if(askedlist.isEmpty()) {
				return null;
			}
			return askedlist;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public void deleteask(int agnum) {  // 친구요청삭제
		try {
			String sql = "delete from askgroup where agnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, agnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public boolean makegroup(String groupname, int mnum) { // 그룹만들기
		try {
				String sql = "INSERT INTO gbt.group (gname) values(?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, groupname);
				ps.executeUpdate();
				ps.close();
				// 그룹만든사람은 바로 그룹멤버로 등록
				String sql2 = "INSERT INTO groupmember (gnum,mnum) VALUES(LAST_INSERT_ID(),?)";
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mnum);
				ps.executeUpdate();
				return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean joingroup(int gnum, int loginmnum) { // 요청 수락해서 그룹멤버로 들어가기
		try {
				String sql = "INSERT INTO groupmember (gnum,mnum) VALUES(?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, gnum);
				ps.setInt(2, loginmnum);
				ps.executeUpdate();
				return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
	public ArrayList<Group> grouplist (int loginmnum){ // 그룹 리스트
		try {
			ArrayList<Group> grouplist = new ArrayList<Group>();
			String sql = "select a.*, b.mid from (select gbt.group.gnum, gbt.group.gname, groupmember.mnum from groupmember left join gbt.group on groupmember.gnum=gbt.group.gnum where mnum=?) a left join member b on a.mnum=b.mnum";
			ps = con.prepareStatement(sql);
			ps.setInt(1, loginmnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				Group group = new Group(rs.getInt(1), rs.getString(2), rs.getString(4));
				grouplist.add(group);
			}
			if(grouplist.isEmpty()) {
				return null;
			}
			return grouplist;		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public ArrayList<Groupmember> gmlist (int gnum){ // 그룹 멤버 리스트
		try {
			ArrayList<Groupmember> gmlist = new ArrayList<Groupmember>();
			String sql = "select a.*, b.mid, b.mname from (select a.gnum, b.gname,a.mnum from groupmember a left join gbt.group b on a.gnum=b.gnum) a left join member b on a.mnum=b.mnum where gnum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, gnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				Groupmember gmember = new Groupmember(rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(1), rs.getString(2));
				gmlist.add(gmember);
			}
			if(gmlist.isEmpty()) {
				return null;
			}
			return gmlist;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean groupout(int loginmnum, int gnum) { // 그룹 나가기
		try {
			String sql = "delete from groupmember where mnum=? and gnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, loginmnum);
			ps.setInt(2, gnum);
			ps.executeUpdate();
			ps.close(); // 남은 그룹멤버가 있는지 체크
			String sql2 = "select * from groupmember where gnum=?";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, gnum);
			rs = ps.executeQuery();
			if(rs.next()) { // 그룹원 있으면 메소드 종료
				ps.close();
				return true;
			}
			else { // 남아있는 그룹원이 없으면 그룹도 삭제
				ps.close();
				String sql3 = "delete from gbt.group where gnum=?";
				ps = con.prepareStatement(sql3);
				ps.setInt(1, gnum);
				ps.executeUpdate();
				ps.close();
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Member> countmember(int gnum) { // 그룹 멤버수 세기
		ArrayList<Member> countmember = new ArrayList<>();
		try {
			String sql = "select a.*, b.mname from gbt.groupmember a join member b on a.mnum = b.mnum where gnum ="+gnum;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = new Member(rs.getInt(2), null, null, rs.getString(4), null);
				countmember.add(member);			
			}
			return countmember;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
