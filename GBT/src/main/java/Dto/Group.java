package Dto;

public class Group {
	
	private int gnum;
	private String gname;
	private String mid;

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public Group(int gnum, String gname, String mid) {
		super();
		this.gnum = gnum;
		this.gname = gname;
		this.mid = mid;
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

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	
	
}
