package com.kse.slp.modules.onlinestores.modules.incomingarticles.validation;

public class mIncomingArticleValidation {
	
	private String articleCode;
	private int amount;
	private float price;
	private String sp_code;
	private String date;
	
	public String getSp_code() {
		return sp_code;
	}
	public void setSp_code(String sp_code) {
		this.sp_code = sp_code;
	}
	public String getArticleCode() {
		return articleCode;
	}
	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public mIncomingArticleValidation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public mIncomingArticleValidation(String articleCode, int amount, float price, String sp_code, String date) {
		super();
		this.articleCode = articleCode;
		this.amount = amount;
		this.price = price;
		this.sp_code = sp_code;
		this.date = date;
	}
	@Override
	public String toString() {
		return "mIncomingArticleValidation [articleCode=" + articleCode + ", amount=" + amount + ", price=" + price
				+ ", sp_code=" + sp_code + ", date=" + date + "]";
	}
	
}
