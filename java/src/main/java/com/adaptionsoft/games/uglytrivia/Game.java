package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.enums.Category;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.adaptionsoft.games.enums.Category.*;

public class Game {
    ArrayList<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory().getName());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			System.out.println(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			System.out.println("The category is " + currentCategory().getName());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		String questionExtracted = extractNextQuestion();

		System.out.println(questionExtracted);
	}

	private String extractNextQuestion() {
		switch (currentCategory()) {
			case POP:
				return popQuestions.removeFirst();
			case SCIENCE:
				return scienceQuestions.removeFirst();
			case SPORTS:
				return sportsQuestions.removeFirst();
			case ROCK:
				return rockQuestions.removeFirst();
			default:
				throw new IllegalStateException("Angelo's fault");
		}
	}

	private Category currentCategory() {
		switch (places[currentPlayer] % 4) {
			case 0:
				return POP;
			case 1:
				return SCIENCE;
			case 2:
				return SPORTS;
			case 3:
				return ROCK;
			default:
				throw new IllegalStateException("Angelo's fault");
		}
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
			giveNextPlayerTurn();
			return true;
		} else {
			System.out.println("Answer was correct!!!!");
			incrementGoldCoinsForCurrentPlayer();

			boolean winner = didPlayerWin();
			giveNextPlayerTurn();

			return winner;
		}
	}

	private void giveNextPlayerTurn() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	private void incrementGoldCoinsForCurrentPlayer() {
		purses[currentPlayer]++;

		System.out.println(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
