package com.query;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionFilters {
	
		QueryParameter access =new QueryParameter();
		ArrayList<Integer> id=new ArrayList<Integer>();
		HashMap<Integer, ArrayList<String>> csvData = new HashMap<Integer, ArrayList<String>>();
		
		void init() {
			for(Integer i=0;i<(access.list).size()/17;i++) {
				ArrayList<String> s= new ArrayList<String>();
				for(int j=0+16*i;j<access.list.size();j++) {
					s.add(access.list.get(j));
					if(j==16*(i+1)) {
						break;
					}
				}
				csvData.put(i+1,s);
			}
		}
		
	}



