package net.jeeshop.services.manage.oss.bean;

import java.io.Serializable;

/**
 * 七牛OSS
 * 
 * @author jingmiao
 * 
 */
public class QiniuOSS implements Serializable {
	
	private static final long serialVersionUID = -5168762902314497649L;
	
	private String ACCESS_KEY;
	private String SECRET_KEY;
	private String bucketName;
	private String OSS_ENDPOINT;

	public String getACCESS_KEY() {
		return ACCESS_KEY;
	}
	
	public void setACCESS_KEY(String aCCESS_KEY) {
		ACCESS_KEY = aCCESS_KEY;
	}

	public String getSECRET_KEY() {
		return SECRET_KEY;
	}
	
	public void setSECRET_KEY(String aSECRET_KEY) {
		SECRET_KEY = aSECRET_KEY;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getOSS_ENDPOINT() {
		return OSS_ENDPOINT;
	}

	public void setOSS_ENDPOINT(String oSS_ENDPOINT) {
		OSS_ENDPOINT = oSS_ENDPOINT;
	}

	public void clear() {
		ACCESS_KEY = null;
		SECRET_KEY = null;
		bucketName = null;
		OSS_ENDPOINT = null;
	}

	@Override
	public String toString() {
		return "AliyunOSS [ACCESS_KEY=" + ACCESS_KEY 
				+ ", SECRET_KEY=" + SECRET_KEY
				+ ", OSS_ENDPOINT=" + OSS_ENDPOINT
				+ ", bucketName=" + bucketName + "]";
	}

}
