import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class GameManager {
	public static GameManager GM = new GameManager();
	int stepCount;
	int map = 0;
	int PlayerMaxHealth = 100;
	int PlayerHealth = PlayerMaxHealth;
	int PlayerDamage = 66;
	int PlayerArmor = 10;
	int stageLavel; 
	int sec = 0;
	int tmpSec = 0;
	int tmpStep = 0;
	int totalDamage = 0;
	int score = 0;
	boolean isongame;
	InGame inGame;
	Game game;
	Enemy enemy;
	NumGame NG;
	public void clear() {
		System.out.println("clear!!!");
		enemy.Health -= PlayerDamage;
		totalDamage += PlayerDamage;
		System.out.println(PlayerHealth);
		getScore();
		enemy.setHPbar();
	}
	public void countStep() {
		enemy.setStep();
	}
	public void BeAttecked() {
		PlayerHealth -= enemy.attackPower*(1-(1/(float)PlayerArmor));
		inGame.setHPbar();
		System.out.println("beAttacked!!!");
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
		game.result.setVisible(false);
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
				if(isongame) {
					sec++;
					tmpSec++;
				}
				else {
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
		System.out.println("+"+(int)(PlayerDamage*100*(float)(1f+(50f/tmpStep))*(float)(1f+(50f/tmpSec))));
		System.out.println("현재 : " + score);
		tmpSec = 0;
		tmpStep = 0;
	}
	public String getTotalScore() {
		String total ="";
		System.out.println("최종 : " +score);
		total = String.format("%010d", score);
		return total;
	}
}
