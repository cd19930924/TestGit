package com.file.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

public class FileVO implements Serializable {

	private static final long serialVersionUID = 6812031416008533930L;

	// 照片檔案
	private Integer fileID; // AI
	private Integer carerID;
	private String fileTypeNo;
	private Blob fileContent;
	private Timestamp uploadDate; // currentTimeStamp
	private String fileContentString; // 40
	private String certification; // 40
	private String applyFileContent; // 26
	private String fileTypeName; // 26

	public FileVO() {
		super();
	}

	public Integer getFileID() {
		return fileID;
	}

	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}

	public Integer getCarerID() {
		return carerID;
	}

	public void setCarerID(Integer carerID) {
		this.carerID = carerID;
	}

	public String getFileTypeNo() {
		return fileTypeNo;
	}

	public void setFileTypeNo(String fileTypeNo) {
		this.fileTypeNo = fileTypeNo;
	}

	public Blob getFileContent() {
		return fileContent;
	}

	public void setFileContent(Blob fileContent) {
		this.fileContent = fileContent;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFileContentString() {
		return fileContentString;
	}

	public void setFileContentString(String fileContentString) {
		this.fileContentString = fileContentString;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getApplyFileContent() {
		return applyFileContent;
	}

	public void setApplyFileContent(String applyFileContent) {
		this.applyFileContent = applyFileContent;
	}

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

}
