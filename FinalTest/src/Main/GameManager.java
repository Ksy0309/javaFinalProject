package Main;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class GameManager {
	public static GameManager GM = new GameManager();
	AudioManager AM = new AudioManager();
	Player Player = new Player();
	//게임 전체에서 사용할 변수들 선언
	int stepCount, stageLevel, sec, totalDamage, score;
	int map;	//퍼즐의 마지막 버튼의 인덱스(전체 버튼의 개수)
	int col;	//퍼즐의 행 또는 열의 개수
	int tmpSec = 0;	//각 퍼즐의 풀이 시간
	int tmpStep = 0;//각 퍼즐의 풀이 이동 횟수
	
	//여부 확인에 필요한 boolean 변수 선언
	boolean isongame;
	boolean isSheild = false;
	boolean isEscON = false;
	
	//각 객체를 GM에 연결할 참조 변수 선언
	InGame inGame;
	Game game;
	Enemy enemy;
	NumGame NG;
	
	//플레이어가 공격 또는 방어 패턴 성공 시 실행
	public void attack(int c) {
		//공격 시
		if(c == 0) {
			System.out.println("Attack!!!");
			AM.attackSound();
			enemy.Health -= Player.Damage;
			totalDamage += Player.Damage;
			getScore();
			enemy.setHPbar();
		}
		//방어 시
		else {
			System.out.println("Sheild!!!");
			AM.SheildSound();
			isSheild = true;
			enemy.Sheild.setVisible(true);
			Player.Sheild = 15 * (col);
			enemy.setStep();
		}
	}
	
	//enemy의 공격 스텝 카운트 설정
	public void countStep() {
		enemy.setStep();
	}
	
	//플레이어가 공격 받을 경우 수행
	public void BeAttecked() {
		//방어 상태가 아닐 경우 피격 데미지 연산
		if(isSheild != true) {
			AM.beAttackedSound();
			Player.Health -= enemy.attackPower*(1-(1/(float)Player.Armor));
			inGame.setHPbar();
			System.out.println("beAttacked!!!");
		}
		//방어 상태일 경우 피격X
		else {
			AM.SheildSound();
			System.out.println("isSheild!!!");
		}
		//공격 받고 HP가 0이 될경우 게임 오버
		if(Player.Health < 0) {
			NG.frozen();
			endGame(0);
		}
	}
	
	//게임 프로그램 시작
	public void startMainGame() {
		new Game();
	}
	
	//인게임 시작 후 각 변수들 초기화
	public void startGame(int lavel) {
		isongame = true;
		stepCount = 0;
		sec = 0;
		totalDamage = 0;
		score = 0;
		startTime();
		Player.MaxHealth = 100;
		Player.Health = Player.MaxHealth;
		game.ingame = new InGame(lavel);
		inGame.setHPbar();
		game.con.add(game.ingame);
		game.menu.setVisible(false);
		AM.startBGM();
	}
	
	//인게임 게임 오버 또는 클리어 시 실행
	public void endGame(int R) {
		isongame = false;
		game.result.setResultText(R);
		game.result.setVisible(true);
		
	}
	
	//인게임 종료 후 메인 화면 이동
	public void exitGame() {
		AM.stopBGM();
		game.con.remove(game.ingame);
		isongame = false;
		game.result.setVisible(false);
		game.menu.GoStart();
		game.menu.setVisible(true);
	}
	
	//스테이지 클리어 시 수행
	public void clearLavel() {
		stageLevel++;
		score += 50000;
		NG.frozen();
		//보스 처치 시
		if(stageLevel>3) {
			endGame(1);
		}
		//적 처치 시
		else {
			game.reward.setVisible(true);
		}
	}
	
	//다음 스테이지로 이동
	public void nextLavel() {
		AM.stopBGM();
		AM.startBGM();
		enemy.setLevel(stageLevel);
		NG.unFrozen();
		
	}
	
	//시간 타이머 시작
	public void startTime() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				//인게임이고 esc메뉴가 아닐 경우 시간 추가
				if(isongame && !isEscON) {
					sec++;
					tmpSec++;
				}
				//인게임 종료 시 시간 연산 종료
				if(!isongame) {
					timer.cancel();
				}
			}
		};
		//1초 마다 실행
		timer.schedule(task, 0, 1000);
	}
	
	//소요 시간의 초 반환
	public int getSec() {
		return sec%60;
	}
	//소요 시간의 분 반환
	public int getMin() {
		return sec/60;
	}
	
	//클리어 시 데미지, 퍼즐 소모 시간,퍼즐 이동 횟수를 통해 점수 연산 후 추가 
	public void getScore() {
		score += (int)(Player.Damage*100*(float)(1f+(50f/tmpStep))*(float)(1f+(50f/tmpSec)));
		enemy.Score.setText(String.format("%010d", score));
		System.out.println("+"+(int)(Player.Damage*100*(float)(1f+(50f/tmpStep))*(float)(1f+(50f/tmpSec))));
		System.out.println("현재 : " + score);
		tmpSec = 0;
		tmpStep = 0;
	}
	
	//최종 점수 반환
	public String getTotalScore() {
		String total ="";
		System.out.println("최종 : " +score);
		enemy.Score.setText(String.format("%010d", score));
		total = String.format("%010d", score);
		return total;
	}
	
	//esc메뉴 출력
	public void escOn() {
		GameManager.GM.isEscON = true;
		enemy.esc.setEnabled(false);
		GameManager.GM.inGame.NumPuz.frozen();
		game.esc.setVisible(true);
	}
	
	//esc메뉴 종료
	public void escOff() {
		GameManager.GM.inGame.NumPuz.unFrozen();
		enemy.esc.setEnabled(true);
		GameManager.GM.isEscON = false;
		game.esc.setVisible(false);
	}
}
