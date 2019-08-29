package card_game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class play_game {
	private static int[] cardset = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5,
			5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 11, 12, 12, 13, 13, 13, 13 };// 43
	private static player_rule player = new player();
	private static player_rule cpu = new cpu();
	private static Field field = new Field();
	private static Random random = new Random();

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);

		ArrayList cards_player = new ArrayList(3);// 玩家手上的牌組
		for (int i = 0; i < 3; i++)
			cards_player.add(cardset[random.nextInt(cardset.length)]);// 選3張牌
		// 遊戲開始
		int commend = -1;
		while (commend != 0) {
			field.round();// 計算回合數

			// 測試 沒有介面的遊戲
			System.out.println("回合數 : " + field.getround());
			System.out.print("玩家血量 : " + player.gethp());
			System.out.print("玩家攻擊 : " + player.getAtk());
			System.out.println("玩家防禦 : " + player.getDef());

			System.out.print("電腦血量 : " + cpu.gethp());
			System.out.print("電腦攻擊 : " + cpu.getAtk());
			System.out.println("電腦防禦 : " + cpu.getDef());

			System.out.println("玩家手上牌為 : " + cards_player);
			System.out.print("想出第幾張牌?(從0開始算) : ");

			int cpu_choose = random.nextInt(9) + 1; // 電腦隨機選牌
			cpu.setCard(cpu_choose); // 紀錄電腦的選牌
			cpu.affect(cpu_choose, cpu);

			int choose = key.nextInt(); // 選擇要出的牌的位置
			player.setCard((int) cards_player.get(choose)); // 紀錄玩家出的牌
			player.affect(player.getCard(), player); // 紀錄效果
			draw_cards(cards_player, choose); // 抽牌
			attack(); // 攻擊囉
			// 判定勝負
			if (cpu.gethp() <= 0) {
				System.out.println("You win~");
				break;
			}
			if (player.gethp() <= 0) {
				System.out.println("輸電腦 摳連");
				break;
			}
		}

	}

	public static void draw_cards(ArrayList a, int card_pos) { // 抽牌的動作 丟入的參數為剛剛出的牌 int card 為棄牌的位置
		a.remove(card_pos);
		a.add(cardset[random.nextInt(cardset.length)]); // 隨機抽一張
	}

	public static void special() {
		// 判定特殊卡牌 也就是(8號以上)兩張陷阱卡和一些擴充的東西
		if (player.getCard() == 8)
			cpu.sethp(3);
		else if (player.getCard() == 9)
			cpu.sethp(cpu.gethp() - 7);
		else if (player.getCard() == 10)
			player.sethp(0);
		else if (player.getCard() == 11)
			cpu.sethp(0);
		else if (player.getCard() == 13) {
			cpu.setAtk(0);
			player.sethp(1);
			cpu.sethp(1);
		}
		
		if (cpu.getCard() == 8)
			player.sethp(3);
		else if (cpu.getCard() == 9)
			player.sethp(player.gethp() - 7);
		else if (cpu.getCard() == 10)
			cpu.sethp(0);
		else if (cpu.getCard() == 11)
			player.sethp(0);
		else if (cpu.getCard() == 13) {
			player.setAtk(0);
			player.sethp(1);
			cpu.sethp(1);
		}

	}

	public static void attack() {
		special();
		// 計算本回合玩家和電腦所造成的傷害
		int cpu_damage = player.getAtk() - cpu.getDef();
		int player_damage = cpu.getAtk() - player.getDef();

		System.out.println("cpu的牌" + cpu.getCard());

		// 攻擊>防禦 則扣血
		if (cpu_damage > 0)
			cpu.sethp(cpu.gethp() - cpu_damage);
		if (player_damage > 0)
			player.sethp(player.gethp() - player_damage);

	}

}
