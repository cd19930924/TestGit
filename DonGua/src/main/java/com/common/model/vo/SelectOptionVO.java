package com.common.model.vo;

import java.util.ArrayList;
import java.util.List;

public class SelectOptionVO {
	private String category;
	private List<Option> list = new ArrayList<>();
	
	public static class Option {
		private String value;
		private String text;
		
		public Option(String value, String text) {
			super();
			this.value = value;
			this.text = text;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		@Override
		public String toString() {
			return "Option [value=" + value + ", text=" + text + "]";
		}
		
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public List<Option> getList() {
		return list;
	}


	public void setList(List<Option> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "SelectOptionVO [category=" + category + ", list=" + list + "]";
	}
	
}


