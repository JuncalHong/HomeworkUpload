package service;

import java.io.IOException;

public class ComputerControl {
	public static void closeWindow(){
		try {
			Runtime.getRuntime().exec("shutdown -s -t 00");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void restartWindow(){
		
		try {
			Runtime.getRuntime().exec("shutdown -r -t 00");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void Logoff(){
		try {
			Runtime.getRuntime().exec("shutdown -l");  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}