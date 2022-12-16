package open;

import java.util.Random;

public class Main {

	public static void main(String[] args) throws Exception {
		
		int[] keyList = new int[10000];
		for (int i = 1; i < 10000; i++)
		{
			int r = gimmeRandom(1, 100000); 	//big random numbers as keys
			keyList[i] = r;						//lets us reference
//			System.out.println(r + " is the key just added");			//ALL the keys
		}
		System.out.println("\n=-=-=-=-=- keyList initialized =-=-=-=-=-");
		
		
		//===========================Testing Steve, the open-addressing table ===============
		Steve table = new Steve();
		System.out.println("\n=======the Steve 'table' exists. No, not the Table called Steve, the Steve called Table. Have a nice day!.");		
		//adding things to steve and logging all of the keys for proofing
		for (int i = 1; i < 10000; i++)
		{
			int r = keyList[i];					//lets us reference keys as needed
//			printTable(table);					//this will pring *lots* of things
			table.HashInsert(r, i);				//r is the key stored above, i is a placeholder Value so we know what order things were added in
//			System.out.println(r + " is the key just added");			//ALL the keys
		}
		
		//System Check! How fast do things actually run?   ========Check for Steve
		long startTime1;
		long endTime1;
		startTime1 = System.currentTimeMillis();
		for(int i =0; i < 100; i++)
		{
			int num = gimmeRandom(1, 10000); //select a random number to search for
			System.out.println("(Item #" + num + " is value" + table.Get(keyList[num]).Value + " is key " + keyList[num] + ") "); //occasionally breaks and I have no idea
		}
		endTime1 = System.currentTimeMillis();
//		printTable(table);	//Un-comment and run at peril of your own RAM. There's a lot there, and at this point it will print all 21000 nodes.
		
		System.out.println("\n======================================= done testing Steve, now testing Danny(2)  ================================================= \n\n");
		
		
		//===========================Testing Danny, the open-addressing table ===============
		Danny2point0 table2 = new Danny2point0();
		System.out.println("\n=======the Danny 'table' exists. No, not the Table called Danny, the Danny called Table. Deal with it.");		
		//adding things to steve and logging all of the keys for proofing
		for (int i = 1; i < 10000; i++)
		{
			int r = keyList[i];					//lets us reference keys as needed
//			printTable(table);					//this will pring *lots* of things
			table2.Add(r, i);					//r is the key stored above, i is a placeholder Value so we know what order things were added in
//			System.out.println(r + " is the key just added");			//ALL the keys
		}

		//System Check! How fast do things actually run?   ========Check for Steve
		long startTime2;
		long endTime2;
		startTime2 = System.currentTimeMillis();
		for(int i =0; i < 100; i++)
		{
			int num = gimmeRandom(1, 10000); //select a random number to search for
			System.out.println("(Item #" + num + " is value " + table2.Get(keyList[num]).Value + " is key " + keyList[num] + ") "); //occasionally breaks and I have no idea
		}
		endTime2 = System.currentTimeMillis();		
//				printTable(table)
		
		
		
		System.out.println("\n==================================================================================================================\n" +
							"Ellapsed Time for Steve is " + (endTime1 - startTime1) + " Milliseconds.  === In table Steve there are " + table.Count + " items.\n" +
							"Ellapsed Time for Danny is " + (endTime2 - startTime2) + " Milliseconds.  === In table Danny there are... more than " + table2.Count + " items? idk why it reads that wayt though.\n" +
							"==================================================================================================================\n");	
		
		//cabbage
	}
	
	
	//minor tool functions
	public static int gimmeRandom(int min, int max) //I didn't feel like doing the logic more than once
	{
		Random rd = new Random();
		int num = rd.nextInt(max-min) + min;
		return num;
	}
	
	public static void printTable(Steve table)
	{
		int[] arr = new int[table.buckets.length];
		for(int i = 0; i < arr.length; i++)
		{
//			arr[i] = table.buckets[i].Value;
			System.out.print("(K:" + table.buckets[i].Key + ", S:" + table.buckets[i].State + ", C:" + table.buckets[i].Code + ", V:" + table.buckets[i].Value + ");\t");
		}
		System.out.println("\n=-=-=-=-=- Count is " + table.Count  +  " Compared to size of " + table.Size + " meaning IsChunky:" + table.IsChunky() + "  =-=-=-=-=");
	}
	
	public static int[] copyToNewArray(int[] arr1)
	{
		int[] arr2 = new int[arr1.length];
		for(int i = 0; i < arr1.length; i++)
		{
			arr2[i] = arr1[i];
		}
		return arr2;
	}

}
