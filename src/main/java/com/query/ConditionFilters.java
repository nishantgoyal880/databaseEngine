package com.query;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionFilters {
	
		QueryParameter access =new QueryParameter();
		static ArrayList<Integer> id=new ArrayList<Integer>();
		HashMap<Integer, ArrayList<String>> csvData = new HashMap<Integer, ArrayList<String>>();
		
		
		void init() {
			for(Integer i=0;i<(QueryParameter.list).size()/17;i++) {
				ArrayList<String> s= new ArrayList<String>();
				for(int j=17*i;j<QueryParameter.list.size();) {
					s.add(QueryParameter.list.get(j));
					if(j==17*(i+1)) {
						break;
					}
					j++;
				}
				csvData.put(i+1,s);
			}
		}
		
		void condResult() {
			
			for(int i=0;i<QueryParameter.splitInput.length;i++) {
				
				if(QueryParameter.splitInput[i].equals(">")) {
					
					int ind=QueryParameter.listHead.indexOf(QueryParameter.splitInput[i-1]);
					
					for(int j=0;j<(QueryParameter.list).size()/17;j++) {
						if(Integer.parseInt(QueryParameter.list.get(ind+17*j))>Integer.parseInt(QueryParameter.splitInput[i+1])) {
							id.add(j);
						}
					}
					
				}else if(QueryParameter.splitInput[i]=="<"){
					
				}else if(QueryParameter.splitInput[i]=="="){
					
				}else if(QueryParameter.splitInput[i]=="and") {
					
				}else if(QueryParameter.splitInput[i]=="or") {
					
				}else {
					//System.out.println("all conditions resolved");
				}
			}
		}
		
	}



