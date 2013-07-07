package dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class FileContent {
	String fileId;
	Blob blob;
	byte[] temp;
	
	public FileContent(String fileId, byte[] blob) {
		super();
		this.fileId = fileId;
		this.temp = blob;
	}
	
	public FileContent() {
		
	}
	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * @return the blob
	 */
	public Blob getBlob() {
		return blob;
	}
	/**
	 * @param blob the blob to set
	 */
	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	
}
