package card_game;

public abstract class player_rule {
	private int hp;
	private int atk = 0 ,def = 0 ,new_card_num ,card_num; //攻擊防禦
	private boolean cd1 = false ,cd2 = false , cd3 = false ; //用於計算受限於兩回合的卡牌所出現的回合數 分別為 card1,card2,card3
	private boolean ccd1 = false ,ccd2 = false , ccd3 = false ;//同上
	private int[] cards_player = new int[3];
	
	public player_rule(int hp) {
		this.hp = hp;
	}
	public int gethp() {
		return this.hp;
	}
	public void sethp(int hp) {
		this.hp = hp;
	}
	
	public void first(int a,int b, int c) {
		cards_player[0] = a;
		cards_player[1] = b;
		cards_player[2] = c;
	}
	public void setcp(int[] a) {
		cards_player = a;
	}
	public int[] getcp () {
		return this.cards_player;
	}
	
	public void set_new_Card(int card_pos) {//新抽的牌
		new_card_num = cards_player[card_pos];
	}
	public int get_new_Card() {
		return new_card_num;
	}
	
	public void setCard(int card) { //當前出的牌
		card_num = card;
	}
	public int getCard() {
		return card_num;
	}
	
	public void aa() {
		System.out.println("目前手牌為 : ");
		System.out.print(cards_player[0] + " ");
		System.out.print(cards_player[1] + " ");
		System.out.println(cards_player[2]);
	}
	
	//紀錄卡牌效果 以及改變當回合卡片攻防數值
	public void affect(int a,player_rule role) { 
		// int a是卡牌編號
		//player_rule role為player或cpu
		
		switch(a){//依卡牌編號輸出其效果
			case 1 :
				cd1 = true;//打開這一回合作用的開關
				atk = 0;
				def = 0;
				break;
			case 2 :
				atk = 5;
				def = 1;
				break;
			case 3 :
				cd2 = true;//再持續一回合 def+1
				atk = 3;
				def = 1;
				break;
			case 4 :
				atk = 2;
				def = 3;
				break;
			case 5 :
				cd3 = true;//再持續一回合 atk+1
				atk = 1;
				def = 4;
				break;
				//魔法卡 回血
			case 6 ://+3
				atk = 0;
				def = 1;
				role.sethp(role.gethp()+3);
				break;
			case 7 ://+5
				atk = 0;
				def = 1;
				role.sethp(role.gethp()+5);
				break;
				//陷阱卡
			case 8 :// 將對方血量降至3 
				atk = 0;
				def = 0;
				break;
			case 9 :// 無視對方防禦 直接將對方hp-7
				atk = 0;
				def = 0;
				break;
			case 10 ://自殺
				atk = 0;
				def = 100;
				break;
			case 11 ://直接幹掉對手
				atk = 100;
				def = 0;
				break;
			case 12 ://很強
				atk = 20;
				def = 10;
				break;
			case 13 ://togther to die
				atk = 0;
				def = 0;
				break;
			default :
				break;
		}
		//以下六個if 還是口頭講好了 註解好麻煩 耶
		if (ccd1) {
			atk*=2;
			ccd1 = !ccd1;
		}
		if (ccd2) {
			def+=1;
			ccd2 = !ccd2;
		}
		if (ccd3) {
			atk+=1;
			ccd3 = !ccd3;
		}
		
		if (cd1) {
			ccd1 = !ccd1;//將原本的 false 轉成 true 打開下一回合作用的開關
			cd1 = !cd1;//關起這一回合作用的開關
		}
		if (cd2) {
			ccd2 = !ccd2;
			cd2 = !cd2;
		}
		if (cd3) {
			ccd3 = !ccd3;
			cd3 = !cd3;
		}
		

	}
	public int getAtk() {
		return atk;
	}
	public void  setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void  setDef(int def) {
		this.def = def;
	}

}