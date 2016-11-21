package homeJobMarketplace.model;

public class Application {
	private int appId;
	private int jobId;
	private int memberId;
	private int expectedPay;
	public int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getExpectedPay() {
		return expectedPay;
	}

	public void setExpectedPay(int expectedPay) {
		this.expectedPay = expectedPay;
	}
	
	@Override
	public String toString() {
		return "Application [applicationId=" + appId + ", jobId="
				+ jobId +" Sitter Id="+memberId+ ",expectedPay="
				+ expectedPay + "]";
	}
}
