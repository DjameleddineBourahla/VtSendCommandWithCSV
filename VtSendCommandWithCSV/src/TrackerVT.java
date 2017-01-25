

  
public class TrackerVT {

	private String imei;
	 private String operator;
	 
	 
	public TrackerVT(String imei, String operator) {
		super();
		this.imei = imei;
		this.operator = operator;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}

	@Override
	public String toString() {
		return "TrackerVT [deviceID=" + imei + ", operator=" + operator + "]";
	}
}
