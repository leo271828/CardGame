package interfaceGame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Interface_Performed extends InterfaceGame_Arguments{

	public static Random random = new Random();
	// button反應 選擇出哪張牌
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Action.CARDLEFT.name()) {
			
			InterfaceGame_after.field.round();
			InterfaceGame_after.player.setCard(InterfaceGame_Arguments.card1);
			// 洗牌
			DrawCards(InterfaceGame_after.player.getcp(), 0);
			InterfaceGame_after.player.set_new_Card(0);
			BattleView(InterfaceGame_Arguments.cardPic[0], InterfaceGame_Arguments.cardCom, InterfaceGame_Arguments.card1);
			InterfaceGame_Arguments.card1 = InterfaceGame_after.player.get_new_Card();
		} else if (e.getActionCommand() == Action.CARDMIDDLE.name()) {
			InterfaceGame_after.field.round();
			InterfaceGame_after.player.setCard(InterfaceGame_Arguments.card2);
			// 洗牌
			DrawCards(InterfaceGame_after.player.getcp(), 1);// 洗牌
			InterfaceGame_after.player.set_new_Card(1);
			BattleView(InterfaceGame_Arguments.cardPic[1], InterfaceGame_Arguments.cardCom, InterfaceGame_Arguments.card2);
			InterfaceGame_Arguments.card2 = InterfaceGame_after.player.get_new_Card();
		} else if (e.getActionCommand() == Action.CARDRIGHT.name()) {
			InterfaceGame_after.field.round();
			InterfaceGame_after.player.setCard(InterfaceGame_Arguments.card3);
			// 洗牌
			DrawCards(InterfaceGame_after.player.getcp(), 2);
			InterfaceGame_after.player.set_new_Card(2);
			BattleView(InterfaceGame_Arguments.cardPic[2], InterfaceGame_Arguments.cardCom, InterfaceGame_Arguments.card3);
			InterfaceGame_Arguments.card3 = InterfaceGame_after.player.get_new_Card();
		}
		// 計算回合數
		if (e.getActionCommand() == Action.FIGHT.name()) {
			InterfaceGame_Arguments.roundResult.setVisible(true);
			if (InterfaceGame_after.cpu.gethp() <= 0) {

				JLabel end = new JLabel("You win~");
				end.setSize(800, 100);
				end.setFont(new java.awt.Font("Dialog", 1, 40));
				end.setLocation(500, 100);// 310
				end.setVisible(true);
				/*
				 * try { Thread.sleep(3000); System.exit(0); } catch (InterruptedException e1) {
				 * e1.printStackTrace(); }
				 */

			} else if (InterfaceGame_after.player.gethp() <= 0) {
				JLabel end = new JLabel("輸電腦 摳連");
				end.setSize(800, 100);
				end.setFont(new java.awt.Font("Dialog", 1, 40));
				end.setLocation(500, 100);
				end.setVisible(true);/*
										 * try { Thread.sleep(3000); System.exit(0); } catch (InterruptedException e1) {
										 * e1.printStackTrace(); } System.exit(0);
										 */

			}
			if (InterfaceGame_after.cpu.gethp() > 0 && InterfaceGame_after.player.gethp() > 0) {
				InterfaceGame_Arguments.roundResult.setText("回合數 : " + InterfaceGame_after.field.getround() + "\n" + "玩家的牌 : "
						+ InterfaceGame_Arguments.player.getCard() + "\n" + "玩家血量 : " + InterfaceGame_Arguments.player.gethp() + "\n" + "玩家攻擊 : "
						+ InterfaceGame_after.player.getAtk() + "\n" + "玩家防禦 : " + InterfaceGame_Arguments.player.getDef() + "\n" + "電腦血量 : "
						+ InterfaceGame_after.cpu.gethp() + "\n" + "電腦攻擊 : " + InterfaceGame_Arguments.cpu.getAtk() + "\n" + "電腦防禦 : "
						+ InterfaceGame_after.cpu.getDef() + "\n" + "電腦下回合的牌 : " + InterfaceGame_Arguments.cardCom);
				// 顯示己方與對手生命值
				String x = "Player hp: " + String.valueOf(InterfaceGame_after.player.gethp());
				InterfaceGame_Arguments.hpPlayerLabel.setText(x);
				;
				// this.getLayeredPane().add(hpPlayerLabel, 1000000);
				InterfaceGame_Arguments.hpPlayerLabel.setVisible(true);
				String y = "Com hp: " + String.valueOf(InterfaceGame_Arguments.cpu.gethp());
				InterfaceGame_Arguments.hpComLabel.setText(y);
				// this.getLayeredPane().add(hpComLabel, 1000000);
				InterfaceGame_Arguments.hpComLabel.setVisible(true);
				InterfaceGame_Arguments.roundResult.setEditable(false);
			}
			InterfaceGame_Arguments.roundResult.setSize(200, 250);
			InterfaceGame_Arguments.roundResult.setLocation(500, 200);
			InterfaceGame_Arguments.roundResult.setFont(new java.awt.Font("Dialog", 1, 20));
			InterfaceGame_Arguments.roundResult.setBackground(Color.LIGHT_GRAY);

			InterfaceGame_Arguments.reset.setActionCommand(Action.RESET.name());
			InterfaceGame_Arguments.reset.setSize(150, 40);
			InterfaceGame_Arguments.reset.setLocation(525, 520);
			InterfaceGame_Arguments.reset.setBackground(Color.PINK);
			InterfaceGame_Arguments.reset.setVisible(true);
			InterfaceGame_Arguments.fight.setVisible(false);
			InterfaceGame_Arguments.background.setVisible(false);
			// this.getLayeredPane().add(background2, 10000000);
			// background2.setVisible(true);
		}
		if (e.getActionCommand() == Action.RESET.name()) {
			// 開始下一回合
			InterfaceGame_Arguments.cardPic[0].setVisible(false);
			InterfaceGame_Arguments.cardPic[1].setVisible(false);
			InterfaceGame_Arguments.cardPic[2].setVisible(false);
			InterfaceGame_Arguments.comPicIn.setVisible(false);
			InterfaceGame_Arguments.background2.setVisible(false);
			InterfaceGame_Arguments.hpPlayerLabel.setVisible(true);
			InterfaceGame_Arguments.hpComLabel.setVisible(true);
			InterfaceGame_Arguments.reset.setVisible(false);
			InterfaceGame_Arguments.fight.setVisible(false);

		}
	}

	// 對戰畫面
	protected void BattleView(JButton chosen, int com, int card_num) {
		InterfaceGame_Arguments.roundResult.setVisible(false);
		InterfaceGame_Arguments.cardPic[0].setVisible(false);
		InterfaceGame_Arguments.cardPic[1].setVisible(false);
		InterfaceGame_Arguments.cardPic[2].setVisible(false);
		InterfaceGame_Arguments.hpPlayerLabel.setVisible(false);
		InterfaceGame_Arguments.hpComLabel.setVisible(false);
		InterfaceGame_Arguments.background.setVisible(false);
		chosen.setLocation(150, 150);
		chosen.setVisible(true);
		InterfaceGame_Arguments.fight.setActionCommand(Action.FIGHT.name());
		InterfaceGame_Arguments.fight.setSize(150, 40);
		InterfaceGame_Arguments.fight.setLocation(525, 520);
		InterfaceGame_Arguments.fight.setBackground(Color.PINK);
		InterfaceGame_Arguments.fight.setVisible(true);

		ImageIcon comPic = new ImageIcon("src/interfaceGame/card" + com + ".png");
		comPic.setImage(comPic.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT));
		JButton comPic2 = new JButton(comPic);
		// comPic2.setVisible(false);
		InterfaceGame_Arguments.comPicIn = (comPic2);
		InterfaceGame_Arguments.comPicIn.setSize(comPic.getIconWidth(), comPic.getIconHeight());
		InterfaceGame_Arguments.comPicIn.setLocation(850, 150);
		InterfaceGame_Arguments.comPicIn.setVisible(true);
		ImageIcon backgroundPic2 = new ImageIcon("src/interfaceGame/background2.png");
		backgroundPic2.setImage(backgroundPic2.getImage().getScaledInstance(1200, 630, Image.SCALE_DEFAULT));
		InterfaceGame_Arguments.background2 = new JLabel(backgroundPic2);
		InterfaceGame_Arguments.background2.setSize(backgroundPic2.getIconWidth(), backgroundPic2.getIconHeight());
		// add(background2);

		InterfaceGame_Arguments.cpu.setCard(InterfaceGame_Arguments.cardCom);
		InterfaceGame_Arguments.player.affect(card_num, InterfaceGame_after.player);
		InterfaceGame_after.cpu.affect(com, InterfaceGame_after.cpu);
		Attack();
		InterfaceGame_Arguments.cardCom = InterfaceGame_Arguments.cardset[random.nextInt(InterfaceGame_Arguments.cardset.length)];

	}

	public static void DrawCards(int[] a, int card_pos) { // 抽牌的動作 丟入的參數為剛剛出的牌 int card_pos 為棄牌的位置
		a[card_pos] = InterfaceGame_Arguments.cardset[random.nextInt(InterfaceGame_Arguments.cardset.length)];
		// int b = a[card_pos];
		// System.out.println(b);
	}

	public static void Special() {
		// 判定特殊卡牌 也就是(8號以上)兩張陷阱卡和一些擴充的東西
		if (InterfaceGame_after.player.getCard() == 11)
			InterfaceGame_after.cpu.sethp(0);
		else if (InterfaceGame_after.player.getCard() == 10)
			InterfaceGame_after.player.sethp(0);
		else if (InterfaceGame_after.player.getCard() == 8)
			InterfaceGame_after.cpu.sethp(3);
		else if (InterfaceGame_after.player.getCard() == 9)
			InterfaceGame_after.cpu.sethp(InterfaceGame_after.cpu.gethp() - 7);
		else if (InterfaceGame_after.player.getCard() == 13) {
			InterfaceGame_after.cpu.setAtk(0);
			InterfaceGame_after.player.sethp(1);
			InterfaceGame_after.cpu.sethp(1);
		}

		if (InterfaceGame_after.cpu.getCard() == 11)
			InterfaceGame_after.player.sethp(0);
		else if (InterfaceGame_after.cpu.getCard() == 10)
			InterfaceGame_after.cpu.sethp(0);
		else if (InterfaceGame_after.cpu.getCard() == 8)
			InterfaceGame_after.player.sethp(3);
		else if (InterfaceGame_after.cpu.getCard() == 9)
			InterfaceGame_after.player.sethp(InterfaceGame_after.player.gethp() - 7);
		else if (InterfaceGame_after.cpu.getCard() == 13) {
			InterfaceGame_after.player.setAtk(0);
			InterfaceGame_after.player.sethp(1);
			InterfaceGame_after.cpu.sethp(1);
		}

	}

	public static void Attack() {
		Special();
		// 計算本回合玩家和電腦所造成的傷害
		int cpu_damage = InterfaceGame_after.player.getAtk() - InterfaceGame_after.cpu.getDef();
		int player_damage = InterfaceGame_after.cpu.getAtk() - InterfaceGame_after.player.getDef();

		// 攻擊>防禦 則扣血
		if (cpu_damage > 0)
			InterfaceGame_after.cpu.sethp(InterfaceGame_after.cpu.gethp() - cpu_damage);
		if (player_damage > 0)
			InterfaceGame_after.player.sethp(InterfaceGame_after.player.gethp() - player_damage);
	}
}
