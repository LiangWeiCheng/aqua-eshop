package com.aqua.office.jacob;

import java.util.Date;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class MeetingMinutesDoc {
	
	private ActiveXComponent msWordApp = null;
	
	private Dispatch document = null;
	
	public MeetingMinutesDoc(){
		if (msWordApp == null) {  
			msWordApp = new ActiveXComponent("Word.Application");  
	    }  
	}
	
	public void setVisible(boolean visible) {  
		msWordApp.setProperty("Visible", new Variant(visible));  
	}
	
	public void openFile(String wordFilePath) {  
		Dispatch documents = Dispatch.get(msWordApp, "Documents").toDispatch();  
		document = Dispatch.call(documents, "Open", wordFilePath).toDispatch();
	}
	
	public void closeFile(){
		if (document != null) 
		{
		    Dispatch.call(document, "Close", new Variant(0));
		    document = null;
		}
		if (msWordApp != null) 
		{
		    Dispatch.call(msWordApp, "Quit");
		    msWordApp = null;
		}

	}
	
	public void dealTable(MeetingMinutes meetingMinutes){
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		if(meetingMinutes.getFirstParts().size()>0){
			insertFirstTable(Dispatch.call(tables, "Item", new Variant(1)).toDispatch(),meetingMinutes.getFirstParts());
		}
		if(meetingMinutes.getSecondParts().size()>0){
			insertSecondTable(Dispatch.call(tables, "Item", new Variant(2)).toDispatch(),meetingMinutes.getSecondParts());
		}
	}
	
	 public void addLastTableRow(Dispatch rows) {
		 Dispatch row = Dispatch.get(rows, "Last").toDispatch(); 
		 Dispatch.call(rows, "Add", new Variant(row)); 
	 }
	 
	 public void autoFitTable(Dispatch table) { 
	        Dispatch cols = Dispatch.get(table, "Columns").toDispatch(); 
	        Dispatch.call(cols, "AutoFit"); 
	} 
	 
	
	private void insertFirstTable(Dispatch firstTable, List<FirstPart> firstParts) {
		 Dispatch rows = Dispatch.get(firstTable, "Rows").toDispatch(); 
		 int rowCount = Dispatch.get(rows, "Count").changeType(Variant.VariantInt).getInt();
		 int addRowNumber = firstParts.size()+1-rowCount;
		 if(addRowNumber>0){
			 for (int i = 0; i < addRowNumber; i++) {
				 addLastTableRow(rows);
			}
			rowCount+=addRowNumber;
		 }
		 if(firstParts.size()+1<rowCount){
			 rowCount=firstParts.size()+1;
		 }
		 for (int i = 2; i < rowCount+1; i++) {
			FirstPart firstPart = firstParts.get(i-2);
			Dispatch row = Dispatch.call(rows, "Item", new Variant(i)).toDispatch();
			Dispatch cells = Dispatch.get(row, "Cells").toDispatch();
			int cellCount = Dispatch.get(cells, "Count").changeType(Variant.VariantInt).getInt();
			for (int j = 1; j < cellCount+1; j++) {
				Dispatch cell = Dispatch.call(firstTable, "Cell", new Variant(i),new Variant(j)).toDispatch();
				Dispatch.call(cell, "Select");
				Dispatch selection = Dispatch.get(msWordApp, "Selection").toDispatch(); 
				if(j==1){
					Dispatch.put(selection, "Text", i-1);
				}else if (j==2) {
					Dispatch.put(selection, "Text", firstPart.getProjectName());
				}else if (j==3) {
					Dispatch.put(selection, "Text", firstPart.getRequestDepartment());
				}else if (j==4) {
					Dispatch.put(selection, "Text", firstPart.getExpenditure());
				}else if (j==5) {
					Dispatch.put(selection, "Text", firstPart.getMethod());
				}else if (j==6) {
					Dispatch.put(selection, "Text", firstPart.getRemark());
				}
			}
		}
		 autoFitTable(firstTable);
	}
	
	private void insertSecondTable(Dispatch secondTable, List<SecondPart> secondParts) {
		 Dispatch rows = Dispatch.get(secondTable, "Rows").toDispatch(); 
		 int rowCount = Dispatch.get(rows, "Count").changeType(Variant.VariantInt).getInt();
		 int addRowNumber = secondParts.size()+1-rowCount;
		 if(addRowNumber>0){
			 for (int i = 0; i < addRowNumber; i++) {
				 addLastTableRow(rows);
			}
			rowCount+=addRowNumber;
		 }
		 if(secondParts.size()+1<rowCount){
			 rowCount=secondParts.size()+1;
		 }
		 for (int i = 2; i < rowCount+1; i++) {
			SecondPart secondPart = secondParts.get(i-2);
			Dispatch row = Dispatch.call(rows, "Item", new Variant(i)).toDispatch();
			Dispatch cells = Dispatch.get(row, "Cells").toDispatch();
			int cellCount = Dispatch.get(cells, "Count").changeType(Variant.VariantInt).getInt();
			for (int j = 1; j < cellCount+1; j++) {
				Dispatch cell = Dispatch.call(secondTable, "Cell", new Variant(i),new Variant(j)).toDispatch();
				Dispatch.call(cell, "Select");
				Dispatch selection = Dispatch.get(msWordApp, "Selection").toDispatch(); 
				if(j==1){
					Dispatch.put(selection, "Text", i-1);
				}else if (j==2) {
					Dispatch.put(selection, "Text", secondPart.getProjectName());
				}else if (j==3) {
					Dispatch.put(selection, "Text", secondPart.getRequestDepartment());
				}else if (j==4) {
					Dispatch.put(selection, "Text", secondPart.getExpenditure());
				}else if (j==5) {
					Dispatch.put(selection, "Text", secondPart.getSupplier());
				}else if (j==6) {
					Dispatch.put(selection, "Text", secondPart.getPrice());
				}else if (j==7) {
					Dispatch.put(selection, "Text", secondPart.getApproveDate());
				}else if (j==8) {
					Dispatch.put(selection, "Text", secondPart.getMethod());
				}else if (j==9) {
					Dispatch.put(selection, "Text", secondPart.isApproveCondition()?"Y":"N");
				}else if (j==10) {
					Dispatch.put(selection, "Text", secondPart.getRemark());
				}
			}
		}
		 autoFitTable(secondTable);
	}
	
	public void saveFileAs(String sourceFile,String destinationFile,MeetingMinutes meetingMinutes) {  
		setVisible(false);
		openFile(sourceFile);
		dealTable(meetingMinutes);
		Dispatch.call(document, "SaveAs", destinationFile);  
		closeFile();
	} 

	public static void main(String...strings){
		MeetingMinutes meetingMinutes = new MeetingMinutes();
		meetingMinutes.setPurchaseSig("purchase sig");
		meetingMinutes.setFinanceSig("finance sig");
		meetingMinutes.setLawSig("law sig");
		meetingMinutes.setAuditSig("audit sig");
		meetingMinutes.setDisciplineSig("discipline sig");
		meetingMinutes.addFirstPart(new FirstPart("projectName1","requestDepartment1", 100023000.793, "method1", "remark1"));
		meetingMinutes.addFirstPart(new FirstPart("projectName2",
				"requestDepartment2", 100023000.793, "method2", "remark2"));
		meetingMinutes.addFirstPart(new FirstPart("projectName3",
				"requestDepartment3", 100023000.793, "method3", "remark3"));
		meetingMinutes.addFirstPart(new FirstPart("projectName4",
				"requestDepartment4", 100023000.793, "method4", "remark4"));
		meetingMinutes.addFirstPart(new FirstPart("projectName5",
				"requestDepartment5", 100023000.793, "method5", "remark5"));
		meetingMinutes.addFirstPart(new FirstPart("projectName6",
				"requestDepartment6", 100023000.793, "method6", "remark6"));
		meetingMinutes.addFirstPart(new FirstPart("projectName7",
				"requestDepartment7", 100023000.793, "method7", "remark7"));
		meetingMinutes.addSecondPart(new SecondPart("projectName1",
				"requestDepartment1", 100023000.793, "supplier1", "price1",
				new Date(), "method1", true, "remark1"));
		meetingMinutes.addSecondPart(new SecondPart("projectName2",
				"requestDepartment2", 100023000.793, "supplier2", "price2",
				new Date(), "method2", true, "remark2"));
		meetingMinutes.addSecondPart(new SecondPart("projectName3",
				"requestDepartment3", 100023000.793, "supplier3", "price3",
				new Date(), "method3", false, "remark3"));
		
		meetingMinutes.addSecondPart(new SecondPart("projectName4",
				"requestDepartment4", 100023000.793, "supplier4", "price4",
				new Date(), "method4", false, "remark4"));
		meetingMinutes.addSecondPart(new SecondPart("项目5",
				"requestDepartment5", 100023000.793, "supplier5", "price5",
				new Date(), "method5", false, "remark5"));
		meetingMinutes.addSecondPart(new SecondPart("项目6",
				"requestDepartment6", 100023000.793, "supplier6", "price6",
				new Date(), "method6", true, "remark6"));
		meetingMinutes.addSecondPart(new SecondPart("项目7",
				"申请部门7", 100023000.793, "供应商7", "价格7",
				new Date(), "采购方式7", false, "备注7"));
		
		String filePath="D:\\项目需求\\3.QS5-PC300-004非生产材料采购货源委员会MM.doc";
		String savaPath="D:\\项目需求\\save_by_java.doc";
		MeetingMinutesDoc meetingMinutesDoc = new MeetingMinutesDoc();
		meetingMinutesDoc.saveFileAs(filePath,savaPath,meetingMinutes);
	}
	
}
