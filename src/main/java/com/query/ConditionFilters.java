package com.query;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionFilters {
	
		QueryParameter access =new QueryParameter();
		ArrayList<Integer> id=new ArrayList<Integer>();
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
		
	}



