package com.query;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionFilters {
	
		QueryParameter access =new QueryParameter();
		ArrayList<Integer> id=new ArrayList<Integer>();
		HashMap<Integer, ArrayList<String>> csvData = new HashMap<Integer, ArrayList<String>>();
		
		void init() {
			System.out.println("i m here");
			for(Integer i=1;i<=(access.list).size()/17;i++) {
				System.out.println("i m here");
				ArrayList<String> s= new ArrayList<String>();
				for(int j=0;j<access.list.size();j++) {
					int z=0;
					if(z==16) {
						s.add(access.list.get(j));
						z++;
						break;
					}
				}
				csvData.put(i,s);
			}
		}
		
	}



