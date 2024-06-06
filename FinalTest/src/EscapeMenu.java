import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class EscapeMenu extends JPanel implements ActionListener{
	JPanel menuP;
	JButton back;
	JButton home;
	EscapeMenu(){
		menuP = new JPanel();
		back = new JButton("돌아가기");
		home = new JButton("메인 메뉴");
		back.addActionListener(this);
		home.addActionListener(this);
		menuP.setBackground(new Color(0,0,0,50));
		menuP.setPreferredSize(new Dimension(200, 400));
		setBackground(new Color(0,0,0,128));
		menuP.add(back);
		menuP.add(home);
		add(menuP);
		
		setSize(300, 600);
		setLayout(new FlowLayout(FlowLayout.CENTER,75,60));
		
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
