package driver;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import DAO.EpisodeSetDAO;
import DAO.SicknessDAO;
import DAO.SymptomDAO;
import Models.EpisodeSet;
import Models.Sickness;
import Models.Symptom;

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
		
		System.out.println("Enter symptom: ");
		String symptomInput = sc.nextLine();
		
		// gets random sickness with symptom chosen
		System.out.println("****************** chosen sickness ******************");
		Symptom chosenSymptom = (new SymptomDAO()).getRandomSicknessIdWithSymptom(symptomInput);
		int sicknessId = chosenSymptom.getSicknessId();
		Sickness chosenSickness = (new SicknessDAO()).getSicknessWithId(sicknessId);
		System.out.println(chosenSickness);
		
		// gets random episode set
		System.out.println("****************** chosen story template ******************");
		EpisodeSet chosenEpisodeSet = (new EpisodeSetDAO()).getRandomEpisodeSet();
		System.out.println(chosenEpisodeSet);
	}
}
