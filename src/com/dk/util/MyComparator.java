package com.dk.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.dk.object.IBeacon;

public class MyComparator implements Comparator<IBeacon>{

	@Override
	public int compare(IBeacon o1, IBeacon o2) {
		// TODO Auto-generated method stub
		return o1.getRssi().compareTo(o2.getRssi());
	}
	
	public static void main(String[] args) {
		List<IBeacon> infos = new ArrayList<IBeacon>();
		for(int i=0;i<10;i++){
			IBeacon info = new IBeacon();
			info.setRssi(new Random().nextInt());
			infos.add(info);
		}
		
		Collections.sort(infos, new MyComparator());
		for(int i=0;i<infos.size();i++){
			System.out.println(infos.get(i).getRssi());
		}
		
		System.out.println("----------------------------");
		infos = infos.subList(0, 3);
		for(int i=0;i<infos.size();i++){
			System.out.println(infos.get(i).getRssi());
		}
	}

}
