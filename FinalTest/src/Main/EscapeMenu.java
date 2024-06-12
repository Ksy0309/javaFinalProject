package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class EscapeMenu extends JPanel implements ActionListener{
	JPanel menuP;
	JButton back;
	JButton home;
	ImageIcon homeI = new ImageIcon("./asset/UI/escHome.png");
	ImageIcon backI = new ImageIcon("./asset/UI/escBack.png");
	public EscapeMenu(){
		
		//esc메뉴의 홈 버튼 설정
		home = new JButton("메인 메뉴");
		home.setPreferredSize(new Dimension(200,200));
		home.setIcon(homeI);
		home.setModel(new BModel());
		home.setOpaque(false);
		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		home.addActionListener(this);
		
		//esc메뉴 인게임으로 돌아가기 버튼 설정
		back = new JButton("돌아가기");
		back.setPreferredSize(new Dimension(200,200));
		back.setIcon(backI);
		back.setModel(new BModel());
		back.setOpaque(false);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.addActionListener(this);
		
		//돌아가기와 홈버튼을 담을 판넬 설정
		menuP = new JPanel();
		menuP.setBackground(new Color(0,0,0,150));
		menuP.setPreferredSize(new Dimension(400, 800));
		menuP.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 130));
		menuP.add(back);
		menuP.add(home);
		
		//판넬 추가
		add(menuP);
		
		//판넬 UI설정
		setBackground(new Color(0,0,0,128));
		setSize(800, 1000);
		setLayout(new FlowLayout(FlowLayout.CENTER,0,80));
		setVisible(false);
	}
	
	//esc메뉴의 버튼을 눌렀을 때 실행할 이벤트리스너
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		System.out.println(bt.getText());
		if(bt.getText().equals("돌아가기")) {
			GameManager.GM.escOff();
		}
		if(bt.getText().equals("메인 메뉴")) {
			GameManager.GM.exitGame();
			GameManager.GM.isEscON = false;
			setVisible(false);
		}
		
	}
}
