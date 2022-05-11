package com.careapply.model;

public class CareApplyPVO {
	private Integer carerId;
	private String memName;
	private String memGender;
	private String memPhone;
	private String memEmail;
	private Double priceHour;
	private Double priceHalfday;
	private Double priceDay;
	private Double cost;

	public Integer getCarerId() {
		return carerId;
	}

	public void setCarerId(Integer carerId) {
		this.carerId = carerId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemGender() {
		return memGender;
	}

	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public Double getPriceHour() {
		return priceHour;
	}

	public void setPriceHour(Double priceHour) {
		this.priceHour = priceHour;
	}

	public Double getPriceHalfday() {
		return priceHalfday;
	}

	public void setPriceHalfday(Double priceHalfday) {
		this.priceHalfday = priceHalfday;
	}

	public Double getPriceDay() {
		return priceDay;
	}

	public void setPriceDay(Double priceDay) {
		this.priceDay = priceDay;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "CareApplyPVO [carerId=" + carerId + ", memName=" + memName + ", memGender=" + memGender + ", memPhone="
				+ memPhone + ", memEmail=" + memEmail + ", priceHour=" + priceHour + ", priceHalfday=" + priceHalfday
				+ ", priceDay=" + priceDay + ", cost=" + cost + "]";
	}

}
