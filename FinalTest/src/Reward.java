import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Reward extends JPanel implements ActionListener{
	JButton RB[] = new JButton[3];
	public Reward() {
		setBackground(new Color(0,0,0,128));
		//setPreferredSize(new Dimension(300, 600));
		setSize(300, 600);
		setLayout(new FlowLayout(FlowLayout.CENTER,75,60));
		for(int i=0;i<3;i++) {
			RB[i] = new JButton();
			RB[i].setPreferredSize(new Dimension(150, 120));
			add(RB[i]);
			RB[i].addActionListener(this);
		}
		RB[0].setText("체력 100% 회복");
		RB[1].setText("공격력 50% 증가");
		RB[2].setText("방어력 20% 증가");
		setVisible(false);
	}
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		//GameManager.GM.ep.setVisible(true);
		if(bt.getText().equals("체력 100% 회복")) {
			GameManager.GM.PlayerHealth = GameManager.GM.PlayerMaxHealth;
			GameManager.GM.inGame.setHPbar();
			GameManager.GM.nextLavel();
			setVisible(false);
		}
		if(bt.getText().equals("공격력 50% 증가")) {
			GameManager.GM.PlayerDamage *= 1.5f;
			GameManager.GM.nextLavel();
			setVisible(false);
		}
		if(bt.getText().equals("방어력 20% 증가")) {
			GameManager.GM.PlayerArmor += 20;
			GameManager.GM.nextLavel();
			setVisible(false);
		}
	}
}
