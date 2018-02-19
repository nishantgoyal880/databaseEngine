package com.query;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class QueryParameter {
	

	String splitInput[];
	String fileName;
	ArrayList<String> baseString =new ArrayList<String>();
	ArrayList<String> filterString=new ArrayList<String>();
	ArrayList<String> condString=new ArrayList<String>();
	ArrayList<String> logString=new ArrayList<String>();
	ArrayList<String> fieldsString=new ArrayList<String>();
	ArrayList<String> aggrString=new ArrayList<String>();
	String ordString;
	String grpString;
	
	//Splitting string into chunks
	public String[] splitString(String input){
		splitInput=input.split(" ");
		return splitInput;
	}
	
	//getting file name
	public String findFileName(String tokens[]) {
		for(int i=0;i<tokens.length;i++) {
			fileName=tokens[i];
			if(fileName.equals("from")) {
				fileName=tokens[i+1];
				break;
			}
		}
		return fileName;
	}
	
	//Getting the base part
	public ArrayList<String> findBase(String tokens[]){ 
		for(int i=0;i<tokens.length;i++) {
			String base=tokens[i];
			if(base.equals("where")) {
				break;
			}else {
				baseString.add(base);
			}
		}
		return baseString;
	}
	
	//Getting the filter part
	ArrayList<String> findFilter(String tokens[]){
		int flagF=0;
		for(int i=0;i<tokens.length;i++) {
			String filter=tokens[i];
			if(flagF==1) {
				filterString.add(filter);
			}
			if(filter.equals("where")) {
				flagF=1;
			}
		}
		return filterString;
	}
	
	//Getting the condition part
	ArrayList<String> findCond(String tokens[]){
		int flagC=0;
		for(int i=0;i<tokens.length;i++) {
			String cond=tokens[i];
			if(cond.equals("group")||cond.equals("order")) {
				break;
			}
            if(flagC==1 && !(cond.equals("and")||cond.equals("or"))) {
                condString.add(cond);
			}
			if(cond.equals("where")) {
				flagC=1;
			}
		}
		return condString;
	}
	
	//Getting logical operators
	ArrayList<String> findLog(String tokens[]){
		for(int i=0;i<tokens.length;i++) {
			String log=tokens[i];
            if(log.equals("and")||log.equals("or")||log.equals("not")) {
            	logString.add(log);
            }
		}
		return logString;
	}
	
	//Getting selected id's and aggregate functions
	ArrayList<String> findFields(ArrayList<String> base){
		String selField="";
		int ind=base.indexOf("from");
		for(int i=1;i<ind;i++) {
			selField=selField+base.get(i);
		}
		String selectedField[]=selField.split(",");
		for(String itr:selectedField) {
			if(!(Pattern.compile("\\s?\\w{1,5}\\(\\w*\\)").matcher(itr).matches())) {
				fieldsString.add(itr);
			}else {
				aggrString.add(itr);
			}
		}
		return fieldsString;
	}
	
	//Getting order by field
	String findOrder(String tokens[]) {
		for(int i=0;i<tokens.length;i++) {
		    ordString=tokens[i];
			if(ordString.equals("order")) {
				ordString=tokens[i+2];
				break;					
			}else {
				ordString="";
			}
		}
		return ordString;
	}	
	
	//Getting group by field
	String findGroup(String tokens[]) {
		for(int i=0;i<tokens.length;i++) {
			grpString=tokens[i];
			if(grpString.equals("group")) {
				grpString=tokens[i+2];
				break;					
			}else {
				grpString="";
			}
		}
		return grpString;
	}	
	
	

}
