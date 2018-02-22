package com.query;

import java.util.*;
import java.util.Map.Entry;

public class MainClass {
	
	 
	public static void main(String[] args) {

		String input;	//Query string from user
		String tokens[]=null;	//parsed query string
		QueryParameter para=new QueryParameter();	//object of calling class
		ConditionFilters obj=new ConditionFilters();
		HashMap<String, ArrayList<String>> fieldResult = new HashMap<String, ArrayList<String>>();
		

		//Reading file and setting data types for integer and date
		para.read();
		

		System.out.println("Enter the query");

		//Getting input from user
		input="select city,id,season,team2 from ipl.csv where win_by_runs > 30 ";


		//Splitting and displaying input into array of words
		tokens=para.splitString(input);
		System.out.println("\nTokens of query is: ");
		for(String itr:tokens) {
			System.out.println(itr);
		}

		//Displaying the file
		String fileName=para.findFileName(tokens);
		System.out.println("\nFile name is: "+fileName+"\n");

		//Displaying the base part
		System.out.print("Base part is: ");
		ArrayList<String> base=para.findBase(tokens);
		for (int i=0; i<base.size(); i++) {
		    System.out.print(base.get(i)+" ");
		}
		

		//Displaying the filter part
		ArrayList<String> filter=para.findFilter(tokens);
		System.out.println();
		System.out.print("\nFilter part is: ");
		for (int i=0; i<filter.size(); i++) {
		    System.out.print(filter.get(i)+" ");
		}

		//Displaying the conditions
		ArrayList<String> cond=para.findCond(tokens);
		System.out.println("\n");
		System.out.print("Conditions are: ");
		for (int i=0; i<cond.size(); i++) {
		    System.out.print(cond.get(i)+" ");
		}

		//Displaying the logical operators
		ArrayList<String> log=para.findLog(tokens);
		System.out.println("\n");
		System.out.print("Logical operators are: ");
		for (int i=0; i<log.size(); i++) {
		    System.out.print(log.get(i)+" ");
		}


		//Displaying selected fields and aggregate functions
		ArrayList<String> fields=para.findFields(base);
		System.out.println("\n");
		System.out.print("Selected fields are: ");
		for (int i=0; i<fields.size(); i++) {
		    System.out.print(fields.get(i)+" ");
		}

		//Displaying order by field
		System.out.println();
		String order=para.findOrder(tokens);
		System.out.println();
		System.out.println("order by field id: "+order);

		//Displaying order by field
		String group=para.findGroup(tokens);
		System.out.println();
		System.out.println("group by field id: "+group);


		//Displaying aggregate functions as same as id's
		System.out.println();
		if(para.aggrString!=null) {
			System.out.print("Aggregate functions are: ");
			for (int i=0; i<(para.aggrString).size(); i++) {
				System.out.print((para.aggrString).get(i)+" ");
			}
		}
		
		//Selecting fields using sql query
		try {
			System.out.println("\n");
			for(int i=0;i<fields.size();i++) {	
				ArrayList<String> s=new ArrayList<String>();
				int j=QueryParameter.listHead.indexOf(fields.get(i));
				for(int k=j;k<QueryParameter.list.size();k=k+17) {
					s.add(QueryParameter.list.get(k));
				}	
				fieldResult.put(fields.get(i),s);
			}
			
			for(int i=0;i<QueryParameter.list.size()/17;i++) {
				for (Map.Entry<String, ArrayList<String>> entry : fieldResult.entrySet()) {
					ArrayList<String> value = entry.getValue();
					//System.out.print(value.get(i)+" ");				
				}
				//System.out.println();
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			//System.out.println(e);
		}
		
		//setting arraylist inside hashmap for each data line
		obj.init();

		for (Entry<Integer, ArrayList<String>> entry : obj.csvData.entrySet()) {
			ArrayList<String> value = entry.getValue();
			//System.out.println(entry.getKey()+" "+value);				
		}
		
		//displaying conditions
		obj.condResult();
		for(int j=0;j<ConditionFilters.id.size();j++) {
			for(int i=0;i<QueryParameter.list.size()/17;i++) {
				if(i==ConditionFilters.id.get(j)) {
						System.out.println(obj.csvData.get(i+1));
			
				}
			}
		}	
		
	}

}
