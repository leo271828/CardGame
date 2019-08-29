package interfaceGame;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

import card_game.*;

public class InterfaceGame_after extends JFrame implements ActionListener,InterfaceGame_Arguments  {

	protected static final int[] cardset = { 1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,5,5,5,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,9,9,9, 10,11, 12, 12,13,13, 13, 13 };// 43
	protected static final player_rule player = new player();
	protected static final player_rule cpu = new cpu();
	protected static final Field field = new Field();
	protected static final Random random = new Random();
	// 設立顯示所需變數
	protected int card1, card2, card3, cardCom; //這傢伙會動
	JTextArea roundResult = new JTextArea();
	// 顯示背景畫面
	protected JLabel background = new JLabel();
	protected JLabel background2 = new JLabel();
	// 顯示HP
	protected JLabel hpPlayerLabel = new JLabel();
	protected JLabel hpComLabel = new JLabel();
	// 顯示手牌按鈕陣列
	JButton[] cardPic = new JButton[3];
	JButton[] cardPicF = new JButton[3];
	// 電腦手牌
	JButton comPicIn = new JButton();
	// 顯示第幾回合
	protected JLabel roundCount = new JLabel();
	// 看結果
	protected JButton fight = new JButton("fight");
	// 回到手牌畫面
	protected JButton reset = new JButton("Next Round");
	// 按鈕指令
	protected enum Action {
		CARDLEFT, CARDMIDDLE, CARDRIGHT, RESET, FIGHT
	}
	
	public InterfaceGame_after(int card1, int card2, int card3, int cardCom, int hpPlayer, int hpCom) {//建構子
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		player.first(card1, card2, card3);
		player.aa();
		this.cardCom = cardCom;
		cpu.sethp(hpCom);
		player.sethp(hpPlayer);
		//JLabel hpPlayerTrans = new JLabel("Player HP " + hpPlayer);// 傳入的初始血量
		this.hpPlayerLabel.setText("Player HP " + hpPlayer);// = hpPlayerTrans;
		this.hpComLabel.setText("Com HP " + hpCom); 
		//JLabel roundCountTrans = new JLabel("Round " + Integer.toString(round));
		//this.roundCount = roundCountTrans;
		HandCardsView();
	}
	
	// 手牌畫面
	protected void HandCardsView() {
		roundResult.setVisible(false);
		// 視窗大小設定
		setSize(1200, 630);// 1700,900
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLUE);
		setLayout(null);

		// 加入卡片
		String pathPic;
		for (int i = 0; i < 3; i++) {
			int[] cardIn = { card1, card2, card3 };
			pathPic = "src/interfaceGame/card" + cardIn[i] + ".png";
			ImageIcon icon = new ImageIcon(pathPic);
			icon.setImage(icon.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT));
			JButton cardPicIn = new JButton(icon);
			cardPicIn.setSize(icon.getIconWidth(), icon.getIconHeight());// 將圖片和按鈕對齊
			cardPic[i] = cardPicIn;
		}
		cardPic[0].setLocation(280, 220);// 420,200
		cardPic[1].setLocation(500, 220);// 725,200
		cardPic[2].setLocation(715, 220);// 1030,200
		this.getLayeredPane().add(cardPic[0], 100);
		this.getLayeredPane().add(cardPic[1], 100);
		this.getLayeredPane().add(cardPic[2], 100);

		// 加入血量
		hpPlayerLabel.setSize(400, 100);
		hpPlayerLabel.setLocation(120, 50);
		hpPlayerLabel.setFont(new java.awt.Font("Dialog", 1, 40));
		hpComLabel.setSize(400, 100);
		hpComLabel.setLocation(860, 50);
		hpComLabel.setFont(new java.awt.Font("Dialog", 1, 40));
		hpComLabel.setVisible(true);
		this.getLayeredPane().add(hpPlayerLabel, Integer.MAX_VALUE);
		this.getLayeredPane().add(hpComLabel, Integer.MAX_VALUE);

		// 加入回合數
		roundCount.setSize(150, 100);
		roundCount.setLocation(545, 42);
		roundCount.setFont(new java.awt.Font("Dialog", 1, 30));
		roundCount.setVisible(true);
		this.getLayeredPane().add(roundCount, 100);

		// 放入背景圖片
		ImageIcon backgroundPic = new ImageIcon("src/interfaceGame/background.png");
		backgroundPic.setImage(backgroundPic.getImage().getScaledInstance(1200, 630, Image.SCALE_DEFAULT));
		//background.setLocation(0,0);
		background = new JLabel(backgroundPic);
		background.setSize(backgroundPic.getIconWidth(), backgroundPic.getIconHeight());
		//this.getLayeredPane().add(background, Integer.MIN_VALUE);

		// 按圖片
		cardPic[0].addActionListener(this);
		cardPic[1].addActionListener(this);
		cardPic[2].addActionListener(this);
		cardPic[0].setActionCommand(Action.CARDLEFT.name());
		cardPic[1].setActionCommand(Action.CARDMIDDLE.name());
		cardPic[2].setActionCommand(Action.CARDRIGHT.name());
		this.setVisible(true);
		//this.repaint();
	}

	// button反應 選擇出哪張牌
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Action.CARDLEFT.name()) {
			field.round();
			player.setCard(card1);
			//洗牌
			DrawCards(player.getcp(),0);
			player.set_new_Card(0);
			BattleView(cardPic[0], cardCom, card1);
			this.card1 = player.get_new_Card();
		}
		else if (e.getActionCommand() == Action.CARDMIDDLE.name()) {
			field.round();
			player.setCard(card2);
			//洗牌
			DrawCards(player.getcp(),1);//洗牌
			player.set_new_Card(1);
			BattleView(cardPic[1], cardCom, card2);
			this.card2 = player.get_new_Card();
		} 
		else if (e.getActionCommand() == Action.CARDRIGHT.name()) {
			field.round();
			player.setCard(card3);
			//洗牌
			DrawCards(player.getcp(),2);
			player.set_new_Card(2);
			BattleView(cardPic[2], cardCom, card3);
			this.card3 = player.get_new_Card();
		}
		// 計算回合數
		if (e.getActionCommand() == Action.FIGHT.name()) {
			roundResult.setVisible(true);
		if (cpu.gethp() <= 0) {
				
				JLabel end = new JLabel("You win~");
				end.setSize(800, 100);
				end.setFont(new java.awt.Font("Dialog", 1, 40));
				end.setLocation(500, 100);//310
				this.getLayeredPane().add(end, 10000);
				end.setVisible(true);
				/*
				try {
					Thread.sleep(3000);
					System.exit(0);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}*/

			}
		else if (player.gethp() <= 0) {
				JLabel end = new JLabel("輸電腦 摳連");
				end.setSize(800, 100);
				end.setFont(new java.awt.Font("Dialog", 1, 40));
				end.setLocation(500, 100);
				this.getLayeredPane().add(end, 10000);
				end.setVisible(true);/*
				try {
					Thread.sleep(3000);
					System.exit(0);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(0);*/

			}
		if (cpu.gethp() > 0 && player.gethp() > 0) {
			roundResult.setText("回合數 : " + field.getround() 
			+ "\n" + "玩家的牌 : " + player.getCard()
			+ "\n" + "玩家血量 : " + player.gethp() + "\n" + "玩家攻擊 : "	+ player.getAtk()
			+ "\n" + "玩家防禦 : " + player.getDef() + "\n" + "電腦血量 : " + cpu.gethp() 
			+ "\n" + "電腦攻擊 : " + cpu.getAtk() + "\n" + "電腦防禦 : " + cpu.getDef()
			+ "\n" + "電腦下回合的牌 : " + this.cardCom );
			//顯示己方與對手生命值
			String x = "Player hp: "+String.valueOf(player.gethp());
			hpPlayerLabel.setText(x);;
			//this.getLayeredPane().add(hpPlayerLabel, 1000000);
			hpPlayerLabel.setVisible(true);
			String y = "Com hp: "+String.valueOf(cpu.gethp());
			hpComLabel.setText(y) ;
			//this.getLayeredPane().add(hpComLabel, 1000000);
			hpComLabel.setVisible(true);
			roundResult.setEditable(false);
		}
			this.getLayeredPane().add(roundResult, 10000000);
			roundResult.setSize(200, 250);
			roundResult.setLocation(500, 200);
			roundResult.setFont(new java.awt.Font("Dialog", 1, 20));
			roundResult.setBackground(Color.LIGHT_GRAY);

			this.getLayeredPane().add(reset, 10000000);
			reset.addActionListener(this);
			reset.setActionCommand(Action.RESET.name());
			reset.setSize(150, 40);
			reset.setLocation(525, 520);
			reset.setBackground(Color.PINK);
			reset.setVisible(true);
			fight.setVisible(false);
			background.setVisible(false);
			//this.getLayeredPane().add(background2, 10000000);
			//background2.setVisible(true);
		}
		if (e.getActionCommand() == Action.RESET.name()) {
			// 開始下一回合
			cardPic[0].setVisible(false);
			cardPic[1].setVisible(false);
			cardPic[2].setVisible(false);
			comPicIn.setVisible(false);
			background2.setVisible(false);
			this.getLayeredPane().add(hpPlayerLabel, 100000000);
			hpPlayerLabel.setVisible(true);
			this.getLayeredPane().add(hpComLabel, 100000000);
			hpComLabel.setVisible(true);
			reset.setVisible(false);
			fight.setVisible(false);
			HandCardsView();

		}
	}

	// 對戰畫面
	protected void BattleView(JButton chosen, int com, int card_num) {
		roundResult.setVisible(false);
		cardPic[0].setVisible(false);
		cardPic[1].setVisible(false);
		cardPic[2].setVisible(false);
		hpPlayerLabel.setVisible(false);
		hpComLabel.setVisible(false);
		background.setVisible(false);
		chosen.setLocation(150, 150);
		chosen.setVisible(true);
		add(fight);
		fight.addActionListener(this);
		fight.setActionCommand(Action.FIGHT.name());
		fight.setSize(150, 40);
		fight.setLocation(525, 520);
		fight.setBackground(Color.PINK);
		fight.setVisible(true);

		ImageIcon comPic = new ImageIcon("src/interfaceGame/card" + com + ".png");
		comPic.setImage(comPic.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT));
		JButton comPic2 = new JButton(comPic);
		// comPic2.setVisible(false);
		comPicIn = (comPic2);
		comPicIn.setSize(comPic.getIconWidth(), comPic.getIconHeight());
		comPicIn.setLocation(850, 150);
		comPicIn.setVisible(true);
		add(comPicIn);
		ImageIcon backgroundPic2 = new ImageIcon("src/interfaceGame/background2.png");
		backgroundPic2.setImage(backgroundPic2.getImage().getScaledInstance(1200, 630, Image.SCALE_DEFAULT));
		background2 = new JLabel(backgroundPic2);
		background2.setSize(backgroundPic2.getIconWidth(), backgroundPic2.getIconHeight());
		//add(background2);

		cpu.setCard(cardCom);
		player.affect(card_num, player);
		cpu.affect(com, cpu);
		Attack();
		this.cardCom = cardset[random.nextInt(cardset.length)];

	}
 
	public static final void DrawCards(int[] a, int card_pos) { // 抽牌的動作 丟入的參數為剛剛出的牌 int card_pos 為棄牌的位置
		a[card_pos] = cardset[random.nextInt(cardset.length)];
		//int b = a[card_pos];
		//System.out.println(b);
	}

	public static final void Special() {
		// 判定特殊卡牌 也就是(8號以上)兩張陷阱卡和一些擴充的東西
		if (player.getCard() == 11)
			cpu.sethp(0);
		else if (player.getCard() == 10)
			player.sethp(0);
		else if (player.getCard() == 8)
				cpu.sethp(3);
		else if (player.getCard() == 9)
			cpu.sethp(cpu.gethp() - 7);
		else if (player.getCard() == 13) {
			cpu.setAtk(0);
			player.sethp(1);
			cpu.sethp(1);
		}

		if (cpu.getCard() == 11)
			player.sethp(0);
		else if (cpu.getCard() == 10)
			cpu.sethp(0);
		else if (cpu.getCard() == 8)
			player.sethp(3);
		else if (cpu.getCard() == 9)
			player.sethp(player.gethp() - 7);
		else if (cpu.getCard() == 13) {
			player.setAtk(0);
			player.sethp(1);
			cpu.sethp(1);
		}

	}

	public static final void Attack() {
		Special();
		// 計算本回合玩家和電腦所造成的傷害
		int cpu_damage = player.getAtk() - cpu.getDef();
		int player_damage = cpu.getAtk() - player.getDef();

		// 攻擊>防禦 則扣血
		if (cpu_damage > 0)
			cpu.sethp(cpu.gethp() - cpu_damage);
		if (player_damage > 0)
			player.sethp(player.gethp() - player_damage);
	}
}