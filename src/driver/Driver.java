package driver;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import DAO.SicknessDAO;
import Models.Sickness;

public class Driver {
	static Scanner sc = new Scanner (System.in);
	
	public static void main(String[] args) {
		ArrayList<String> symptomsOptions = new ArrayList<String>();
		symptomsOptions.add("red spots - 1");
		symptomsOptions.add("fever - 2");
		symptomsOptions.add("mosquito bites - 3");
		symptomsOptions.add("tummy ache - 4");
		symptomsOptions.add("colds - 5");
		symptomsOptions.add("sneezing - 6");
		
		System.out.println("Enter symptom id: ");
		int symptomId = sc.nextInt();
		
		// gets Sicknesses
		ArrayList<Sickness> sicknesses = (new SicknessDAO()).getSicknessesWithSymptomId(symptomId);
		
		for (Sickness sickness: sicknesses){
			System.out.println(sickness);
		}
		
		Random rand = new Random();
		Sickness sickness = sicknesses.get(rand.nextInt(sicknesses.size()));
		
		System.out.println("**********CHOSEN SICKNESS**********");
		System.out.println(sickness);
		
		
	}
}
