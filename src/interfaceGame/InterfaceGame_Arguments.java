package interfaceGame;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import card_game.Field;
import card_game.cpu;
import card_game.player;
import card_game.player_rule;

public abstract class InterfaceGame_Arguments {
	static int[] cardset = { 1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,5,5,5,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,9,9,9, 10,11, 12, 12,13,13, 13, 13 };// 43
	 static player_rule player = new player();
	 static player_rule cpu = new cpu();
	 static Field field = new Field();
	 static Random random = new Random();
	// 設立顯示所需變數
	static int card1=0, card2=0, card3=0, cardCom=0;
	static JTextArea roundResult = new JTextArea();
	// 顯示背景畫面
	 static JLabel background = new JLabel();
	 static JLabel background2 = new JLabel();
	// 顯示HP
	 static JLabel hpPlayerLabel = new JLabel();
	 static JLabel hpComLabel = new JLabel();
	// 顯示手牌按鈕陣列
	 static JButton[] cardPic = new JButton[3];
	 static JButton[] cardPicF = new JButton[3];
	// 電腦手牌
	 static JButton comPicIn = new JButton();
	// 顯示第幾回合
	 static JLabel roundCount = new JLabel();
	// 看結果
	 static JButton fight = new JButton("fight");
	// 回到手牌畫面
	 static JButton reset = new JButton("Next Round");
	// 按鈕指令
	 static enum Action {
		CARDLEFT, CARDMIDDLE, CARDRIGHT, RESET, FIGHT
	}
}
