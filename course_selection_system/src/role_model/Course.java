package role_model;

public class Course {
	private String kh;
	private String km;
	private String gh;
	String sksj;
	String skdd;
	
	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public String getSksj() {
		return sksj;
	}

	public void setSksj(String sksj) {
		this.sksj = sksj;
	}

	public String getSkdd() {
		return skdd;
	}

	public void setSkdd(String skdd) {
		this.skdd = skdd;
	}
	public String toString(){
		return this.km;
	}
}