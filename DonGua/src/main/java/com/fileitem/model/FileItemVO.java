package com.fileitem.model;

import java.io.Serializable;

public class FileItemVO implements Serializable {

	private static final long serialVersionUID = 5806892216654529655L;

	// 檔案項目
	private String fileTypeNo;
	private String fileTypeName;

	public FileItemVO() {
		super();
	}

	public FileItemVO(String fileTypeNo, String fileTypeName) {
		super();
		this.fileTypeNo = fileTypeNo;
		this.fileTypeName = fileTypeName;
	}

	public String getFileTypeNo() {
		return fileTypeNo;
	}

	public void setFileTypeNo(String fileTypeNo) {
		this.fileTypeNo = fileTypeNo;
	}

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

}
