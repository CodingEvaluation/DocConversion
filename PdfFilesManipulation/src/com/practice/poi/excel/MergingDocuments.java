package com.practice.poi.excel;

public class MergingDocuments {

	private String ClientSRF;
	private String DocFolderId;
	private String DMSDocType;
	private String FenergoDocType;
	private String DocumentIDs;
	private String EndResult;
	

	public MergingDocuments(String clientSRF, String docFolderId,
			String dMSDocType, String fenergoDocType, String documentIDs,
			String endResult) {
		super();
		ClientSRF = clientSRF;
		DocFolderId = docFolderId;
		DMSDocType = dMSDocType;
		FenergoDocType = fenergoDocType;
		DocumentIDs = documentIDs;
		EndResult = endResult;
	}
	public String getClientSRF() {
		return ClientSRF;
	}
	public void setClientSRF(String clientSRF) {
		ClientSRF = clientSRF;
	}
	public String getDocFolderId() {
		return DocFolderId;
	}
	public void setDocFolderId(String docFolderId) {
		DocFolderId = docFolderId;
	}
	public String getDMSDocType() {
		return DMSDocType;
	}
	public void setDMSDocType(String dMSDocType) {
		DMSDocType = dMSDocType;
	}
	public String getFenergoDocType() {
		return FenergoDocType;
	}
	public void setFenergoDocType(String fenergoDocType) {
		FenergoDocType = fenergoDocType;
	}
	public String getDocumentIDs() {
		return DocumentIDs;
	}
	public void setDocumentIDs(String documentIDs) {
		DocumentIDs = documentIDs;
	}
	public String getEndResult() {
		return EndResult;
	}
	public void setEndResult(String endResult) {
		EndResult = endResult;
	}
	
	
	
}
