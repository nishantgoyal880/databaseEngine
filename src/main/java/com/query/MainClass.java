package com.query;

import java.io.*;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

public class MainClass {
	public static void main(String[] args) {

		String input;	//Query string from user
		String tokens[]=null;	//parsed query string
		QueryParameter para=new QueryParameter();	//object of calling class
		

		//Reading file and setting data types for integer and date
		BufferedReader br=null;
		String line="";
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> listHead=new ArrayList<String>();
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



		System.out.println("Enter the query");

		//Getting input from user
		input="select city from ipl.csv";


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
		System.out.println("\n");
		for(int i=0;i<fields.size();i++) {
			System.out.println(fields.get(i)+": ");
			int j=listHead.indexOf(fields.get(i));
			for(int k=j;k<list.size();k=k+17) {
				System.out.println(list.get(k));
			}
		}
	}

}
