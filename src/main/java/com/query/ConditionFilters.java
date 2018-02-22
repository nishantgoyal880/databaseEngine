package com.query;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionFilters {
	
		QueryParameter access =new QueryParameter();
		static ArrayList<Integer> id=new ArrayList<Integer>();
		HashMap<Integer, ArrayList<String>> csvData = new HashMap<Integer, ArrayList<String>>();
		
		//making arraylist of every field
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
		
		//checking where conditions
		void condResult() {
			
			int flag = 0;
			
			for(int i=0;i<QueryParameter.splitInput.length;i++) {
				
				if(QueryParameter.splitInput[i].equals(">")) {
					
					int ind=QueryParameter.listHead.indexOf(QueryParameter.splitInput[i-1]);
					
					if(flag==0) {
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(Integer.parseInt(QueryParameter.list.get(ind+17*j))>Integer.parseInt(QueryParameter.splitInput[i+1])) {
								id.add(j);
							}
						}
					}
					
				}else if(QueryParameter.splitInput[i].equals("<")){
					
					int ind=QueryParameter.listHead.indexOf(QueryParameter.splitInput[i-1]);
					
					if(flag==0) {
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(Integer.parseInt(QueryParameter.list.get(ind+17*j))<Integer.parseInt(QueryParameter.splitInput[i+1])) {
								id.add(j);
							}
						}
						
					}
					
				}else if(QueryParameter.splitInput[i].equals("=")){
					
					int ind=QueryParameter.listHead.indexOf(QueryParameter.splitInput[i-1]);
					
					if(flag==0) {
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							try {
								
								if(Integer.parseInt(QueryParameter.list.get(ind+17*j))==Integer.parseInt(QueryParameter.splitInput[i+1])) {
									id.add(j);
								}
								
							}catch(Exception e) {
								
								if(QueryParameter.list.get(ind+17*j).equals(QueryParameter.splitInput[i+1])){
									
									id.add(j);
									
								}
								
							}
							
						}
						
					}
					
				}else if(QueryParameter.splitInput[i].equals("and")) {
					
					flag=1;
					
				}else if(QueryParameter.splitInput[i].equals("or")) {
					
					flag=2;
					
				}else {
					//System.out.println("all conditions resolved");
				}
			}
		}
		
	}



