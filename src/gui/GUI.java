package gui;

import java.io.IOException;
import java.util.Scanner;

import logic.Functions;

public class GUI {
	public static void main(String[] args) {
		Functions funcs = new Functions();
		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter file name");
//		String fileName = sc.nextLine(); // sample is test.txt
		try {
			funcs.ParseFile("test.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(funcs.GetAllMachineOfType("large machine"));
		System.out.println(funcs.GetCrossSellForMachine("large machine", "smallest"));
		System.out.println(funcs.GetAllChoices("espresso", "vanilla"));
		System.out.println(funcs.GetAllMachineOfType("espresso machine"));
		System.out.println(funcs.GetCrossSellForMachine("espresso machine", "smallest"));
		System.out.println(funcs.GetCrossSellForGeneral("vanilla"));
	}

}
