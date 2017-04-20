package com.dk.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dk.object.DataForString;
import com.dk.object.GPS;
import com.dk.object.IBeacon;
import com.dk.object.LoRa;

public class StringAnalysis {
	
	public static String stringAnalysis(String[] args){
		StringBuffer returnString  = new StringBuffer();
		returnString.append("7b");
		
//		String str = "7B 00 00 41 4C 54 5F 4C 6F 52 61 30 30 31 05 00 1E 07 05 C0 A8 00 01 50 06 15 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 7D";
//		String str = "7B 00 00 41 4C 54 5F 4C 6F 52 61 30 30 31 05 00 52 07 05 C0 A8 00 01 50 05 17 AC 02 34 FE AC 02 34 FE AC 02 34 FE AC 02 34 FE 00 01 00 01 10 23 50 05 17 AC 02 34 FE AC 02 34 FE AC 02 34 FE AC 02 34 FE 00 01 00 02 20 23 50 05 17 AC 02 34 FE AC 02 34 FE AC 02 34 FE AC 02 34 FE 00 01 00 03 30 23 50 7D";
		String str = "7b 00 21 41 4c 54 5f 4c 4f 52 41 30 30 31 05 00 84 07 05 c0 a8 00 03 00 05 17 e2 c5 6f b5 ff fb 48 f2 b0 60 f0 f5 a7 10 96 e0 00 01 00 02 b4 00 50 05 17 e2 c5 6f b5 ff fb 48 f2 b0 60 f0 f5 a7 10 96 e0 00 01 00 01 b3 00 50 05 17 e2 c5 6f b5 ff fb 48 f2 b0 60 f0 f5 a7 10 96 e0 00 01 00 03 b7 00 50 05 17 e2 c5 6f b5 ff fb 48 f2 b0 60 f0 f5 a7 10 96 e0 00 01 00 05 b2 00 50 05 17 e2 c5 6f b5 ff fb 48 f2 b0 60 f0 f5 a7 10 96 e0 00 01 00 04 b3 00 50 7d";
//		String str = "7b 00 84 41 4c 54 5f 4c 4f 52 41 30 30 31 01 00 08 03 06 04 01 01 00 0f 04 7d";
		str = str.toUpperCase();
		System.out.println(str);
		String[] strs = str.split(" ");
		DataForString dataString = new DataForString();
		GPS gps = new GPS();
		IBeacon ibeacon = new IBeacon();
		LoRa lora = new LoRa();
		boolean is7b = strs[0].equals("7B");
		boolean is7d = strs[strs.length-1].equals("7D");
		if(is7b&&is7d){
//			int count = Integer.parseInt(strs[1],16)+Integer.parseInt(strs[2],16);
//			System.out.println(count);
			String count = strs[1]+strs[2];
			dataString.setJishu(count);
			returnString.append(count);
			StringBuffer sn = new StringBuffer();
			for(int i = 3;i<14;i++){
				sn.append(strs[i]);
			}
			dataString.setSn(sn.toString());
			returnString.append(sn);
			String instructions = strs[14];
			System.out.println(instructions);
			switch (instructions) {
			case "05":
				returnString.append("85");
//				int length = Integer.parseInt(strs[15]+strs[16],16);
				String length = strs[15]+strs[16];
				dataString.setDataLength(length);
				returnString.append(length);
				String TNum = strs[17];
				System.out.println(TNum);
				int LLength = Integer.parseInt(strs[18],16);
				System.out.println(LLength);
				StringBuffer mac = new StringBuffer();
				mac.append(Integer.parseInt(strs[19],16));
				mac.append(".");
				mac.append(Integer.parseInt(strs[20],16));
				mac.append(".");
				mac.append(Integer.parseInt(strs[21],16));
				mac.append(".");
				mac.append(Integer.parseInt(strs[22],16));
				lora.setIp(mac.toString());
				int quantity = Integer.parseInt(strs[23],16);
				lora.setQuantity(quantity);
				
				String T = strs[24];
				System.out.println("t:"+T);
				switch (T) {
				case "05":
					System.out.println("IBeacon---------------------------------------------------------------------------");
					StringBuffer Vdatas = new StringBuffer();
					for(int i =24;i<strs.length-1;i++){
						Vdatas.append(strs[i]);
					}
					System.out.println("length:"+(Vdatas.toString().length()/50));
					List<String> lists = new ArrayList<String>();
					for(int i = 0;i<Vdatas.toString().length()/50;i++){
						String ss = Vdatas.toString().substring(i*50,i*50+50);
						lists.add(ss.substring(2));
					}
					String[] datas = (String[])lists.toArray(new String[lists.size()]);
//					String[] datas = Vdatas.toString().split(T);
					for(String s:datas){
						int dataLength = Integer.parseInt(s.substring(0, 2),16);
						int dataActiveLength = s.substring(2,s.length()).length()/2;
						if(dataLength==dataActiveLength){
							String data = s.substring(2,s.length());
							String uuid = data.substring(0,32);
							ibeacon.setUuid(uuid);
							System.out.println("uuid:"+uuid);
							int maior = Integer.parseInt(data.substring(32,36),16);
							ibeacon.setMaior(maior);
							System.out.println("maior:"+maior);
							int minor = Integer.parseInt(data.substring(36,40),16);
							ibeacon.setMinor(minor);
							System.out.println("minor:"+minor);
							int rssi = Integer.parseInt(data.substring(40,42),16);
							ibeacon.setRssi(rssi);
							System.out.println("rssi:"+rssi);
							int power = Integer.parseInt(data.substring(42,44),16);
							ibeacon.setPower(power);
							System.out.println("power:"+power);
							int bat = Integer.parseInt(data.substring(44,46),16);
							ibeacon.setBat(bat);
							System.out.println("bat:"+bat);
						}
					}
					break;
					
				case "06":
					System.out.println("GPS---------------------------------------------------------------------------------");
					int dataLength = Integer.parseInt(strs[25],16);
					Vdatas = new StringBuffer();
					for(int i =26;i<strs.length-1;i++){
						Vdatas.append(strs[i]);
					}
					int dataActiveLength = Vdatas.toString().length()/2;
					if(dataLength==dataActiveLength){
						String data = Vdatas.toString();
						String isLocation = data.substring(0,2);
						gps.setIsLocation(isLocation);
						System.out.println("isLocation:"+isLocation);
						
						
						Date date = new Date();
						date.setTime(Integer.parseInt(data.substring(2,14),16));
						System.out.println("date:"+date);
						
						
						String we = data.substring(14,16);
						double longitude = 0.0;//经度
						String longitudeDD = data.substring(16,18);
						String longitudeMM = data.substring(18,24);
						longitude = Double.parseDouble(longitudeDD+"."+longitudeMM);
						gps.setLongitude(longitude);
						
						System.out.println("longitude:"+we+":"+longitude);
						double latitude = 0.0;//纬度
						String ns = data.substring(24,26);
						String latitudeDD = data.substring(26,28);
						String latitudeMM = data.substring(28,34);
						latitude = Double.parseDouble(latitudeDD+"."+latitudeMM);
						gps.setLatitude(latitude);
						
						System.out.println("latitude:"+ns+":"+latitude);
						int altitude = Integer.parseInt(data.substring(34,38),16);//海拔
						System.out.println("altitude:"+altitude+"m");
						int direction = Integer.parseInt(data.substring(38,40),16);//方向:正北为0,为世纪芳香的/2
						gps.setDirection(direction);
						System.out.println("direction:"+direction);
						int speed = Integer.parseInt(data.substring(40,42),16);
						gps.setSpeed(speed);
						System.out.println("speed:"+speed+"KM/H");
					}
					break;
					
				default:
					break;
				}
				
				break;

			case "01":
					returnString.append("81");
					dataString.setDataLength(strs[15]+strs[16]);
					returnString.append(strs[15]+strs[16]);
				break;
			default:
				break;
			}
			returnString.append("090101");
			returnString.append("7d");
			
		}
		return returnString.toString().toUpperCase();
	}
	
	
	public static void main(String[] args) {
		String[] strs = null;
		String str = stringAnalysis(strs);
		System.out.println("return:"+str);
	}

}
