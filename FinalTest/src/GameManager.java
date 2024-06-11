import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class GameManager {
	public static GameManager GM = new GameManager();
	AudioManager AM = new AudioManager();
	int stepCount;
	int map = 0;
	int col = 0;
	int PlayerMaxHealth = 100;
	int PlayerHealth = PlayerMaxHealth;
	int PlayerDamage = 300;
	int PlayerArmor = 10;
	int playerSheild = 0;
	int stageLavel; 
	int sec = 0;
	int tmpSec = 0;
	int tmpStep = 0;
	int totalDamage = 0;
	int score = 0;
	boolean isongame;
	boolean isSheild = false;
	boolean isEscON = false;
	InGame inGame;
	Game game;
	Enemy enemy;
	NumGame NG;
	public void clear(int c) {
		if(c == 0) {
			System.out.println("clear!!!");
			AM.attackSound();
			enemy.Health -= PlayerDamage;
			totalDamage += PlayerDamage;
			System.out.println(PlayerHealth);
			getScore();
			enemy.setHPbar();
		}
		else {
			System.out.println("ceild!!!");
			AM.SheildSound();
			isSheild = true;
			enemy.Sheild.setVisible(true);
			playerSheild = 15 * (col);
			enemy.setStep();
		}
	}
	public void countStep() {
		enemy.setStep();
	}
	public void BeAttecked() {
		if(isSheild != true) {
			AM.beAttackedSound();
			PlayerHealth -= enemy.attackPower*(1-(1/(float)PlayerArmor));
			inGame.setHPbar();
			System.out.println("beAttacked!!!");
		}
		else {
			AM.SheildSound();
			System.out.println("isSheild!!!");
		}
		if(PlayerHealth < 0) {
			NG.frozen();
			
			endGame(0);
		}
	}
	public void startMainGame() {
		new Game();
	}
	public void startGame(int lavel) {
		isongame = true;
		stepCount = 0;
		sec = 0;
		totalDamage = 0;
		score = 0;
		startTime();
		PlayerMaxHealth = 100;
		PlayerHealth = PlayerMaxHealth;
		game.ingame = new InGame(lavel);
		inGame.setHPbar();
		game.con.add(game.ingame);
		game.menu.frozen();
		game.menu.setVisible(false);
	}
	public void endGame(int R) {
		isongame = false;
		game.result.setResultText(R);
		game.result.setVisible(true);
		
	}
	public void exitGame() {
		game.con.remove(game.ingame);
		isongame = false;
		game.result.setVisible(false);
		game.menu.GoStart();
		game.menu.setVisible(true);
		game.menu.unFrozen();
	}
	public void clearLavel() {
		stageLavel++;
		score += 50000;
		NG.frozen();
		if(stageLavel>3) {
			endGame(1);
		}
		else {
			
			game.reward.setVisible(true);
		}
	}
	public void nextLavel() {
		enemy.setLevel(stageLavel);
		NG.unFrozen();
		
	}
	public void startTime() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				if(isongame && !isEscON) {
					sec++;
					tmpSec++;
				}
				if(!isongame) {
					timer.cancel();
				}
			}
		};
		timer.schedule(task, 0, 1000);
	}
	public int getSec() {
		return sec%60;
	}
	public int getMin() {
		return sec/60;
	}
	public void getScore() {
		score += (int)(PlayerDamage*100*(float)(1f+(50f/tmpStep))*(float)(1f+(50f/tmpSec)));
		enemy.Score.setText(String.format("%010d", score));
		System.out.println("+"+(int)(PlayerDamage*100*(float)(1f+(50f/tmpStep))*(float)(1f+(50f/tmpSec))));
		System.out.println("현재 : " + score);
		tmpSec = 0;
		tmpStep = 0;
	}
	public String getTotalScore() {
		String total ="";
		System.out.println("최종 : " +score);
		enemy.Score.setText(String.format("%010d", score));
		total = String.format("%010d", score);
		return total;
	}
	public void escOn() {
		GameManager.GM.isEscON = true;
		enemy.esc.setEnabled(false);
		GameManager.GM.inGame.NumPuz.frozen();
		game.esc.setVisible(true);
	}
	public void escOff() {
		GameManager.GM.inGame.NumPuz.unFrozen();
		enemy.esc.setEnabled(true);
		GameManager.GM.isEscON = false;
		game.esc.setVisible(false);
	}
}
