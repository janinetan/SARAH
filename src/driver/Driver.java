package driver;

import java.awt.Event;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import DAO.EpisodeDAO;
import DAO.EpisodeSetDAO;
import DAO.SicknessDAO;
import DAO.SymptomDAO;
import Models.Episode;
import Models.EpisodeSet;
import Models.Sickness;
import Models.Symptom;

public class Driver {
	static Scanner sc = new Scanner (System.in);
	
	public static void main(String[] args) {
		// theme selection (red spots, fever, mosquito bites, tummy ache, colds, sneezing)
		
		System.out.println("Enter symptom: ");
		String symptomInput = sc.nextLine();
		
		// gets random sickness with symptom chosen
		System.out.println("****************** chose sickness ******************");
		Symptom selectedSymptom = (new SymptomDAO()).getRandomSicknessIdWithSymptom(symptomInput);
		int sicknessId = selectedSymptom.getSicknessId();
		Sickness sickness = (new SicknessDAO()).getSicknessWithId(sicknessId);
		System.out.println(sickness);
		
		// gets random episode set
		System.out.println("****************** chose story template ******************");
		EpisodeSet episodeSet = (new EpisodeSetDAO()).getRandomEpisodeSet();
		System.out.println(episodeSet);
		
		// story get episodes
		System.out.println("****************** run story episodes ******************");
		ArrayList<Integer> episodesId = episodeSet.getEpisodesId();
		for (int tempEpisodeId: episodesId){
			Episode episode = (new EpisodeDAO()).getEpisodeById(tempEpisodeId);
//			ArrayList<Integer> eventsId = episode.getEventId(); 
//			for (int tempEventId: eventsId){
//				
//			}
			System.out.println(episode);
		}
	}
}
