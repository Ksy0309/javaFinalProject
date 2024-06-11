package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Game extends JFrame implements KeyListener{
	InGame ingame;	//인게임 화면 객체의 참조 변수
	MainMenu menu;	//메인 타이틀 메뉴 화면 객체의 참조 변수
	Reward reward;	//레벨 클리어 보상 화면 객체의 참조 변수
	Result result;	//게임 클리어 화면 객체의 참조 변수
	EscapeMenu esc;	//esc메뉴 화면의 참조 변수
	Container con = getContentPane();
	public Game() {
		//프레임 제목 설정
		setTitle("Java Finaltest Project");
		
		//창 종료시 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//GM에 객체 연결
		GameManager.GM.game = this;
		
		//기본적으로 각 화면에 출력될 객체 생성 후 프레임에 추가
		menu = new MainMenu();
		reward = new Reward();
		result = new Result(); 
		esc = new EscapeMenu();
		con.add(esc);
		con.add(reward);
		con.add(result);
		con.add(menu);
		
				
		//프레임 크기 및 기본 설정
		setPreferredSize(new Dimension(800, 1000));
		pack();
		setResizable(false);	//크기 변경 불가
		setLocationRelativeTo(null);	//실행시 화면 가운데 생성
		setVisible(true);
		
		//콘텐트패인에 키보드 리스너 설정
		con.addKeyListener(this);
		con.setFocusable(true);
		con.requestFocus();
	}
	
	//키보드 리스너설정
	public void keyTyped(KeyEvent e) {
	}
	//esc입력시 esc메뉴 출력
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && GameManager.GM.isongame == true) {
			if(!GameManager.GM.isEscON)
				GameManager.GM.escOn();
			else
				GameManager.GM.escOff();
		}
    }
	public void keyPressed(KeyEvent e) {
		
    }
}
