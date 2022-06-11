package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.domain.Deck;
import com.adaptionsoft.games.enums.Category;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.adaptionsoft.games.enums.Category.*;

public class Game {
	public static final int MAX_QUESTIONS_NUMBER = 50;

	Deck deck; // TODO: Make this more extensible

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
    	for (int i = 0; i < MAX_QUESTIONS_NUMBER; i++) {
			popQuestions.addLast(createCategoryQuestion(i, POP));
			scienceQuestions.addLast(createCategoryQuestion(i, SCIENCE));
			sportsQuestions.addLast(createCategoryQuestion(i, SPORTS));
			rockQuestions.addLast(createCategoryQuestion(i, ROCK));
    	}

		deck = new Deck(MAX_QUESTIONS_NUMBER);
    }

	private String createCategoryQuestion(int index, Category category){
		return String.format("%s Question %d", category.getName(),index);
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
		return deck.getNextQuestion(currentCategory());
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
