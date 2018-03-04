package com.query;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionFilters {
	
		QueryParameter access =new QueryParameter();
		static ArrayList<Integer> id=new ArrayList<Integer>();
		HashMap<Integer, ArrayList<String>> csvData = new HashMap<Integer, ArrayList<String>>();
		
		//variables for aggregate functions
		static int avg;
		static int sum;
		static int count;
		static int min;
		static int max;
		
		
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
					
					ArrayList<Integer> temp =new ArrayList<Integer>();
					int ind=QueryParameter.listHead.indexOf(QueryParameter.splitInput[i-1]);
					
					if(flag==0) {
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(Integer.parseInt(QueryParameter.list.get(ind+17*j))>Integer.parseInt(QueryParameter.splitInput[i+1])) {
								id.add(j);
							}
						}
					}
					
					if(flag==1) {
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(Integer.parseInt(QueryParameter.list.get(ind+17*j))>Integer.parseInt(QueryParameter.splitInput[i+1])) {
								
								if(!id.contains(j)) {
									//System.out.println(csvData.get(j+1));
									temp.add(j);
								}
							
							}else {
								temp.add(j);
							}
						}
						
					}
					
					if(flag==2) {
						

						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(Integer.parseInt(QueryParameter.list.get(ind+17*j))>Integer.parseInt(QueryParameter.splitInput[i+1])) {
								id.add(j);							
							}
						}
						
					}
					
					id.removeAll(temp);
					
				}else if(QueryParameter.splitInput[i].equals("<")){
					
					int ind=QueryParameter.listHead.indexOf(QueryParameter.splitInput[i-1]);
					ArrayList<Integer> temp =new ArrayList<Integer>();
					
					if(flag==0) {
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(Integer.parseInt(QueryParameter.list.get(ind+17*j))<Integer.parseInt(QueryParameter.splitInput[i+1])) {
								id.add(j);
							}
						}
						
					}
					if(flag==1) {
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(Integer.parseInt(QueryParameter.list.get(ind+17*j))<Integer.parseInt(QueryParameter.splitInput[i+1])) {
								
								if(!id.contains(j)) {
									//System.out.println(csvData.get(j+1));
									temp.add(j);
								}
							
							}else {
								temp.add(j);
							}
						}
						
					}
					
					if(flag==2) {
						

						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(Integer.parseInt(QueryParameter.list.get(ind+17*j))<Integer.parseInt(QueryParameter.splitInput[i+1])) {
								id.add(j);							
							}
						}
						
					}
					
					id.removeAll(temp);
					
				}else if(QueryParameter.splitInput[i].equals("=")){
					
					int ind=QueryParameter.listHead.indexOf(QueryParameter.splitInput[i-1]);
					ArrayList<Integer> temp =new ArrayList<Integer>();
					
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
					
					if(flag==1) {
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							try {
								
								if(Integer.parseInt(QueryParameter.list.get(ind+17*j))==Integer.parseInt(QueryParameter.splitInput[i+1])) {
									if(!id.contains(j)) {
										//System.out.println(csvData.get(j+1));
										temp.add(j);
									}
								}else {
									temp.add(j);
								}
								
							}catch(Exception e) {
								
								if(QueryParameter.list.get(ind+17*j).equals(QueryParameter.splitInput[i+1])){
								
									if(id.contains(j)) {
										//System.out.println(csvData.get(j+1));
										temp.add(j);
									}
									
								}else {
									temp.add(j);
								}
								
							}
							
						}
						
					}
					
					if(flag==2) {
						
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
					
					id.removeAll(temp);
					
				}else if(QueryParameter.splitInput[i].equals("and")) {
					
					flag=1;
					
				}else if(QueryParameter.splitInput[i].equals("or")) {
					
					flag=2;
					
				}else if(QueryParameter.splitInput[i].equals("order")) {
					
					
					
					
					
				}else {
					//System.out.println("all conditions resolved");
				}
			}
		}
		
		//Applying aggregate functions
		void aggregateFun(String aggrField[]) {
			
			for(int i=0;i<aggrField.length;i++) {
				
				if(aggrField[i].equals("avg")) {
					
					int ind=QueryParameter.listHead.indexOf(aggrField[i+1]);
					
					for(int j=0;j<(QueryParameter.list).size()/17;j++) {
						
						avg+=Integer.parseInt(QueryParameter.list.get(ind+17*j));
						
					}
					avg=avg/((QueryParameter.list).size()/17);
					System.out.println("average = "+avg);
					
					
				}else if (aggrField[i].equals("sum")){
					
                    int ind=QueryParameter.listHead.indexOf(aggrField[i+1]);
					
					for(int j=0;j<(QueryParameter.list).size()/17;j++) {
						
						sum+=Integer.parseInt(QueryParameter.list.get(ind+17*j));
						
					}
					System.out.println("sum = "+sum);
					
					
				}else if (aggrField[i].equals("min")) {
					
					int ind=QueryParameter.listHead.indexOf(aggrField[i+1]);
					
					for(int j=0;j<(QueryParameter.list).size()/17;j++) {
						
						if(Integer.parseInt(QueryParameter.list.get(ind+17*j))<min){
							min=Integer.parseInt(QueryParameter.list.get(ind+17*j));
						}
						
					}
					System.out.println("Mininum = "+min);
					
				}else if(aggrField[i].equals("max")) {
					
					int ind=QueryParameter.listHead.indexOf(aggrField[i+1]);
					
					for(int j=0;j<(QueryParameter.list).size()/17;j++) {
						
						if(max<Integer.parseInt(QueryParameter.list.get(ind+17*j))){
							max=Integer.parseInt(QueryParameter.list.get(ind+17*j));
						}
						
					}
					System.out.println("Maximum = "+max);
					
				}else if(aggrField[i].equals("count")) {
					
					if(aggrField[i+1].equals("*")) {
						
						
					}else {
					    
						int ind=QueryParameter.listHead.indexOf(aggrField[i+1]);
						
						for(int j=0;j<(QueryParameter.list).size()/17;j++) {
							
							if(QueryParameter.list.get(ind+17*j)!=null){
								count++;
							}
							
						}
						System.out.println("Count = "+count);
						
					}
					
				}else {
					
				}
				
				
				
				
			}
			
		}
		
		
	}



