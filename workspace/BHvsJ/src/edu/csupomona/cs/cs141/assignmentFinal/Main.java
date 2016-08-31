package edu.csupomona.cs.cs141.assignmentFinal;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner qb = new Scanner(System.in);
		
		int i = qb.nextInt();
		
		System.out.println(itFibonacci(i));
		
		

	}
	 static int itFibonacci(int n){
		int i=1;
		int j=1;
		int fin=1;
		
		for(int k=2;k<n;k++)
		{
			fin= i+j;
			i=j;
			j=fin;
							
		}
		
		
		return j;
		
	}

}
