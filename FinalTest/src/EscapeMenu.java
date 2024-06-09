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
	EscapeMenu(){
		menuP = new JPanel();
		back = new JButton("돌아가기");
		//back.setSize(250, 250);
		back.setPreferredSize(new Dimension(200,200));
		home = new JButton("메인 메뉴");
		//home.setSize(250, 250);
		home.setPreferredSize(new Dimension(200,200));
		home.setIcon(homeI);
		home.setModel(new BModel());
		home.setOpaque(false);
		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		back.setIcon(backI);
		back.setModel(new BModel());
		back.setOpaque(false);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.addActionListener(this);
		home.addActionListener(this);
		menuP.setBackground(new Color(0,0,0,150));
		menuP.setPreferredSize(new Dimension(400, 800));
		menuP.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 130));
		setBackground(new Color(0,0,0,128));
		menuP.add(back);
		menuP.add(home);
		add(menuP);
		
		setSize(900, 1260);
		setLayout(new FlowLayout(FlowLayout.CENTER,75,230));
		
		setVisible(false);
	}
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
