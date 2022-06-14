package Dto;

public class Groupmember {
	
	private int mnum;
	private String mid;
	private String mname;
	private int gnum;
	private String gname;
	
	public Groupmember() {
		// TODO Auto-generated constructor stub
	}

	public Groupmember(int mnum, String mid, String mname, int gnum, String gname) {
		super();
		this.mnum = mnum;
		this.mid = mid;
		this.mname = mname;
		this.gnum = gnum;
		this.gname = gname;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getGnum() {
		return gnum;
	}

	public void setGnum(int gnum) {
		this.gnum = gnum;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}
	
	


}
