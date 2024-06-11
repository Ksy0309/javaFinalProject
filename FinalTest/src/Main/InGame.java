package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class InGame extends JPanel{
	JProgressBar HPbar = new JProgressBar(JProgressBar.HORIZONTAL,0,GameManager.GM.Player.MaxHealth);
	NumGame NumPuz;
	Enemy enemy;
	
	public InGame(int n) {
		
		//플레이어의 HP바 성정
		HPbar.setForeground(Color.red);
		HPbar.setStringPainted(true);
		HPbar.setValue(GameManager.GM.Player.Health);
		HPbar.setString(GameManager.GM.Player.Health + " / " +GameManager.GM.Player.MaxHealth);
		
		
		
		//선택한 난이도에 맞게 퍼즐 게임의 객체 생성
		NumPuz = new NumGame(n);
		
		//적 객체 생성
		enemy = new Enemy();
				
		//퍼즐게임 외 영역인 p패널 성정
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.setPreferredSize(new Dimension(800, 470));
		p.add("Center", enemy);
		p.add("South", HPbar);
		
		//컴포넌트를 객체에 추가
		add(p);
		add(NumPuz);
		
		//객체의 기본 설정
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setLayout(new FlowLayout());
		setVisible(true);
		
		//GM에 객체 연결
		GameManager.GM.inGame = this;
	}
        
	//플레이어의 HP바의 현제 HP와 최대 HP 조정
    public void setHPbar() {
    	//플레이어의 HP가 0보다 낮아질 경우 0으로 설정
    	if(GameManager.GM.Player.Health < 0) {
    		HPbar.setValue(0);
    		HPbar.setString(0 + " / " +GameManager.GM.Player.MaxHealth);
    		
    	}
    	else {
    		HPbar.setValue(GameManager.GM.Player.Health);
    		HPbar.setString(GameManager.GM.Player.Health + " / " +GameManager.GM.Player.MaxHealth);
    	}
    }
}
