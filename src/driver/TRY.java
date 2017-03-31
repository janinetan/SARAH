package driver;

import java.util.ArrayList;
import java.util.Random;

import DAO.ActionDAO;
import DAO.SymptomMapDAO;
import Models.Action;

public class TRY {
	public static int random(ArrayList<Integer> catalogue){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(catalogue.size());
		return index;
	}
	
	
	public static void main(String[]args){
		String location = "park";
		ArrayList<Integer> post_condition = new ArrayList<Integer>();
		ArrayList<Integer> actions = new ArrayList<Integer>();
		Action currentAction;
		Action reverseAction; 
		ActionDAO accessAction = new ActionDAO();
		SymptomMapDAO accessNextAction = new SymptomMapDAO();
		
		currentAction = accessAction.getActionWithNoPrecondition();
		actions.add(currentAction.getId());
		//play current Action
		post_condition = currentAction.getPostCondition();
		
		currentAction = accessAction.getActionByLocationandPrecondition(post_condition.get(random(post_condition)), location);
		
		
		while(actions.size() < 3){
			//kinuha ko reverse action kasi kailangan ilagay sa message 
			reverseAction = accessAction.getActionById(currentAction.getReversePrecondition().get(random(currentAction.getReversePrecondition())));
			//play current action
			actions.add(currentAction.getId());
			
			//if response positive
				//play reverse
				post_condition.remove(post_condition.indexOf(reverseAction.getPostCondition()));
				currentAction = accessAction.getActionByLocationandPrecondition(post_condition.get(random(post_condition)), location);
			
			//if response negative
				 ArrayList<Integer> possibleActions = new ArrayList<Integer>();
				 possibleActions = accessNextAction.getAllActionsPossible(currentAction.getId());
				 //kailangan ko sickness somewhere here
				 for(int i =0; i < possibleActions.size();i++){
					 Action tempAction = accessAction.getActionById(possibleActions.get(i));
					 if(post_condition.contains(tempAction.getAssertionId()) && tempAction.getLocation().contains(location) && !(actions.contains(tempAction.getId()))){
						 currentAction = tempAction;
						 break;
					 }
				 }
		}
	}
}
