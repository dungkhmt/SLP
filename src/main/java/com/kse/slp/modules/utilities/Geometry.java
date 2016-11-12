package com.kse.slp.modules.utilities;

public class Geometry {
	public static double angle(double x1, double y1, double x2, double y2){
		// the angle of vector (x1,y1)-->(x2,y2)
		double dx = x1 - x2; 
		double dy = y1 - y2;
		if(Math.abs(dx) < 0.0000001){
			if(dy > 0) return Math.PI/2+Math.PI; else return Math.PI/2;
		}
		double angle = Math.atan(Math.abs(dy)/Math.abs(dx));
		//System.out.println("Utility::angle, dx = " + dx + ", dy = " + dy + ", angle = " + angle);
		if(dx > 0){
			if(dy < 0) return Math.PI-angle;
			else if(dy > 0) return Math.PI + angle;
			else return Math.PI;
		}else{// dx < 0
			if(dy < 0) return angle;
			else if(dy > 0) return 2*Math.PI - angle;
			else return 0;
		}
		
		//if(dx < 0 && dy < 0) return angle;
		//else if(dx > 0 && dy < 0) return Math.PI-angle;
		//else if(dx > 0 && dy > 0) return 1.5*Math.PI - angle;
		//else return 2*Math.PI-angle;
		
	}
	public static double angleDegree(double a){
		return a*180/Math.PI;
	}
	public static double angleABC(double XA, double YA, double XB, double YB, double XC, double YC){
		// return the angle ABC where A = (XA, YA), B=(XB,YB), C = (XC, YC)
		// x equiv latitude, y equiv longitude
		double a = angle(XB,YB,XA,YA);
		double c = angle(XB,YB,XC, YC);
		
		double r = a-c;
		//System.out.println("a = " + a + ", c = " + c + ", r = " + r + ", PI = " + Math.PI);
		while(r < 0) r += 2*Math.PI;
		while(r > 2*Math.PI) r -= 2*Math.PI;
		if(r > Math.PI) r = r - Math.PI;
			
		return r;
	}
	
	public static void main(String[] args){
		double XA = 1; 
		double YA = 2;
		double XB = 3;
		double YB = 2;
		double XC = 4;
		double YC = 2;
		double a = angleABC(XA,YA,XB,YB,XC,YC);
		System.out.println(angleDegree(a));
	}
}
