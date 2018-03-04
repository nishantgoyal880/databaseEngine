package com.query;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

public class QueryParameter {
	

	static String splitInput[];
	String fileName;
	ArrayList<String> baseString =new ArrayList<String>();
	ArrayList<String> filterString=new ArrayList<String>();
	ArrayList<String> condString=new ArrayList<String>();
	ArrayList<String> logString=new ArrayList<String>();
	ArrayList<String> fieldsString=new ArrayList<String>();
	ArrayList<String> aggrString=new ArrayList<String>();
	String ordString;
	String grpString;
	static int index;//index for order by clause
	
	//variables of read function
	static ArrayList<String> list=new ArrayList<String>();
	static ArrayList<String> listHead=new ArrayList<String>();
	
	
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
	
	//Reading file and setting data types for integer and date
	public void read() {
		
		BufferedReader br=null;
		String line="";

		int flag=0;

		try {
			br=new BufferedReader(new FileReader("ipl.csv"));
			while ((line = br.readLine()) != null) {
				if(flag==0) {
					listHead.addAll(Arrays.asList(line.split(",")));
					flag=1;
				}else {
					String spl[]=line.split(",");
					if(spl.length==18) {
						spl[14]=spl[14]+spl[15];
						spl[15]=spl[16];
						spl[16]=spl[17];
					}
					List<String> li=new ArrayList<String>();
					li.addAll(Arrays.asList(spl));
					if(li.size()==18) {
						li.remove(17);
					}
					list.addAll(li);
				}
			}
		}catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }


		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<list.size();i++) {
			if(Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])").matcher(list.get(i)).matches()) {
				try {
					df.parse(list.get(i));
				}catch(Exception e) {
					System.out.println(e);
				}
			}else if(Pattern.compile("\\d+").matcher(list.get(i)).matches()) {
					Integer.valueOf(list.get(i));
			}
			 else {
			}
		}

	}
	
	//applying order by clause

	JSONObject sortL(Map<Integer,List<String>> sortList,int val) {
		index=val;
		List<HashMap.Entry<Integer,List<String>>> entries = new ArrayList<HashMap.Entry<Integer,List<String>>>(sortList.entrySet());

        Collections.sort(entries, new Comparator<HashMap.Entry<Integer,List<String>>>() {
            public int compare(HashMap.Entry<Integer,List<String>> l1, HashMap.Entry<Integer,List<String>> l2) {
            	
                return l1.getValue().get(index).compareTo(l2.getValue().get(index));
            }
        });
        
        JSONObject json=new JSONObject();
        int i=0;
        for (Map.Entry<Integer,List<String>> e : entries) {
        	json.put(i,e.getValue());
        	i++;	        
        }
        
		return json;
	}
	
	
	

}
