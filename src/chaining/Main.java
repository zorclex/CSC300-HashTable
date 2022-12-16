package chaining;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		String[] keyList = new String[10000];
		for (int i = 0; i < keyList.length; i++)
		{
			int r = gimmeRandom(0, 1000000); 	//big random numbers as keys
			String s = Integer.toString(r);		//converted into string because we had a rough time understanding the assignment instructions
			keyList[i] = s;
//			System.out.print(keyList[i] + " ");	//ALL the keys
		}
		System.out.println("\n=-=-=-=-=- keyList initialized =-=-=-=-=-");

		long startTime;
		long endTime;
		
		System.out.println("\n\n=======the Danny 'table' exists. No, not the Table called Danny, the Danny called Table. Deal with it.");

		Danny table = new Danny();
		
		//adding things to danny
//		table.Add("abc", "One");
//		table.Add("def", "Two");
//		table.Add("ghi", "Three");
//		table.Add("jkl", "Four");
//		table.Add("mno", "Five");
//		table.Add("pqr", "Six");
//		table.Add("stu", "Seven");
//		table.Add("vwx", "Eight");
//		table.Add("yza", "Nine");
//		table.Add("bcd", "Zero");
		for(int i =0; i < keyList.length-1; i++)
		{
			table.Add(keyList[i], Integer.toString(i)); //insert items into the table with teh value as the order they were added and the key as the randomized key from the list
		}
		
		//System Check! How fast do things actually run?
		startTime = System.currentTimeMillis();
		for(int i =0; i < 100; i++)
		{
			int num = gimmeRandom(0, 9999); //select a random number to search for
			System.out.print("(Item #" + i + " is " + table.Get(keyList[num]).Value + " is key " + keyList[num] + ") "); //occasionally breaks and I have no idea
		}
		endTime = System.currentTimeMillis();
		System.out.println("\n\nEllapsed Time is " + (endTime - startTime) + " Milliseconds");
				
		//cabbage
	}
	
	public static int gimmeRandom(int min, int max) //I didn't feel like doing hte locig more than once
	{
		Random rd = new Random();
		int num = rd.nextInt(max-min) + min;
		return num;
	}

}
