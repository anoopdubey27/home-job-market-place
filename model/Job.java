package homeJobMarketplace.model;

import java.util.Date;

public class Job {
	private int jobId;
	private String jobTitle;
	private int postedBy;
	private int payPerHour;
	public Date startDate;
	public Date endDate;
	public String startTime;
	public String endTime;
	public int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getJobId() {
		return jobId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDateDate) {
		this.endDate = endDateDate;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	public int getPayPerHour() {
		return payPerHour;
	}

	public void setPayPerHour(int payPerHour) {
		this.payPerHour = payPerHour;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobTitle=" + jobTitle + ", postedBy="
				+ postedBy + ", startDate=" + startDate + ", endDate="
				+ endDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", payPerHour=" + payPerHour +" Status="+status+ "]";
	}
}
