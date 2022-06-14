package Dto;

public class Asked {

	private int agnum;
	private int frommnum;
	private int tomnum;
	private String gname;
	private String fromid;
	private int gnum;
	
	public Asked() {
		
	}

	public Asked(int agnum, int frommnum, int tomnum, String gname, String fromid, int gnum) {
		super();
		this.agnum = agnum;
		this.frommnum = frommnum;
		this.tomnum = tomnum;
		this.gname = gname;
		this.fromid = fromid;
		this.gnum = gnum;
	}

	public int getAgnum() {
		return agnum;
	}

	public void setAgnum(int agnum) {
		this.agnum = agnum;
	}

	public int getFrommnum() {
		return frommnum;
	}

	public void setFrommnum(int frommnum) {
		this.frommnum = frommnum;
	}

	public int getTomnum() {
		return tomnum;
	}

	public void setTomnum(int tomnum) {
		this.tomnum = tomnum;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getFromid() {
		return fromid;
	}

	public void setFromid(String fromid) {
		this.fromid = fromid;
	}

	public int getGnum() {
		return gnum;
	}

	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	
	
}
