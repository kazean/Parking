package com.parking.vo;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable{
	private String review_c_id;				// 리뷰 작성한 고객 아이디
	private int review_parking_code;		// 리뷰 해당되는 주차장 코드
	private int review_score;				// 리뷰 별점 (최소 1 / 최대 5,1씩 증가)
	private String review_contents;
	private Date review_date;				// 리뷰 작성일 (default CURRENT_TIMESTAMP)
	private boolean review_declaration;		// 신고여부 (true&1:신고o / false&0: 신고x)
	private String c_name;					// DBX 작성한 고객이름
	
	public Review() {
		super();
	}

	
	
	public Review(int review_parking_code) {
		super();
		this.review_parking_code = review_parking_code;
	}



	public Review(String review_c_id, int review_parking_code, int review_score, String review_contents,
			Date review_date, boolean review_declaration, String c_name) {
		super();
		this.review_c_id = review_c_id;
		this.review_parking_code = review_parking_code;
		this.review_score = review_score;
		this.review_contents = review_contents;
		this.review_date = review_date;
		this.review_declaration = review_declaration;
		this.c_name = c_name;
	}

	public String getReview_c_id() {
		return review_c_id;
	}

	public void setReview_c_id(String review_c_id) {
		this.review_c_id = review_c_id;
	}

	public int getReview_parking_code() {
		return review_parking_code;
	}

	public void setReview_parking_code(int review_parking_code) {
		this.review_parking_code = review_parking_code;
	}

	public int getReview_score() {
		return review_score;
	}

	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}

	public String getReview_contents() {
		return review_contents;
	}

	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

	public boolean isReview_declaration() {
		return review_declaration;
	}

	public void setReview_declaration(boolean review_declaration) {
		this.review_declaration = review_declaration;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	@Override
	public String toString() {
		return "Review [review_c_id=" + review_c_id + ", review_parking_code=" + review_parking_code + ", review_score="
				+ review_score + ", review_contents=" + review_contents + ", review_date=" + review_date
				+ ", review_declaration=" + review_declaration + ", c_name=" + c_name + "]";
	}
	
	
	
}
