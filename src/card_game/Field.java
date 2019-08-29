package card_game;

public class Field {
	/*
	 所有牌的能力 雖然全部寫出來 但後來發現沒三小路用 :)
	 			  atk def
	int[] card1 = {0,0};//下回合atk*2
	int[] card2 = {5,1};//1cd
	int[] card3 = {3,2};//2cd def+1 後方數值為加過後的數字
	int[] card4 = {2,3};//1cd
	int[] card5 = {2,4};//2cd atk+1同上
	int[] card6 = {1,1};//+3
	int[] card7 = {1,1};//+5
	int[] card8 = {0,0};//對手剩一滴
	int[] card9 = {0,0};//無視防禦 對手生命減7
	*/
	private int round = 0 ; 
	public void round() { //計算回合數
		this.round++;
	}
	public int getround() { //讀取回合數
		return this.round;
	}
}
