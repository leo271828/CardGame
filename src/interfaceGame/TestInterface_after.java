package interfaceGame;

import java.util.*;

import card_game.*;

public class TestInterface_after {
	private static int[] cardset = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, // 抽牌的機率
																															// 從這抽牌
			5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 11, 12, 12, 13, 13, 13, 13 };// 43
	private static player_rule player = new player();
	private static player_rule cpu = new cpu();
	private static Field field = new Field();
	private static Random random = new Random();

	public static void main(String[] args) {
		int[] cards_player = new int[3];// 玩家手上牌組
		for (int i = 0; i < 3; i++)
			cards_player[i] = cardset[random.nextInt(cardset.length)];
		// 遊戲開始		
		InterfaceGame_after test1 = new InterfaceGame_after(cards_player[0],cards_player[1] ,cards_player[2], 5, 15, 15);
		
		
		}

	

	


}