package Dto;

public class Homework {
	
	private int hnum;
	private int gnum;
	private String hwork;
	private int hratio;
	
	public Homework() {
		// TODO Auto-generated constructor stub
	}

	public Homework(int hnum, int gnum, String hwork, int hratio) {
		super();
		this.hnum = hnum;
		this.gnum = gnum;
		this.hwork = hwork;
		this.hratio = hratio;
	}

	public int getHnum() {
		return hnum;
	}

	public void setHnum(int hnum) {
		this.hnum = hnum;
	}

	public int getGnum() {
		return gnum;
	}

	public void setGnum(int gnum) {
		this.gnum = gnum;
	}

	public String getHwork() {
		return hwork;
	}

	public void setHwork(String hwork) {
		this.hwork = hwork;
	}

	public int getHratio() {
		return hratio;
	}

	public void setHratio(int hratio) {
		this.hratio = hratio;
	}

	@Override
	public String toString() {
		return "Homework [hnum=" + hnum + ", gnum=" + gnum + ", hwork=" + hwork + ", hratio=" + hratio + "]";
	}
	
}
