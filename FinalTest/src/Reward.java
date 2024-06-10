import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Reward extends JPanel implements ActionListener{
	JButton RB[] = new JButton[3];
	JPanel Box;
	JLabel clearT;
	ImageIcon[] rewardI = new ImageIcon[3];
	
	public Reward() {
		setBackground(new Color(0,0,0,128));
		setSize(800, 1000);
		setLayout(new FlowLayout(FlowLayout.CENTER,800,80));
		rewardI[0] = new ImageIcon("./asset/UI/HPposion.png");
		rewardI[1] = new ImageIcon("./asset/UI/addAttackP.png");
		rewardI[2] = new ImageIcon("./asset/UI/addArmorP.png");
		clearT = new JLabel("Level Clear!!!");
		clearT.setForeground(Color.white);
		clearT.setFont(new Font("Dialog", Font.BOLD,40));
		clearT.setHorizontalAlignment(JLabel.CENTER);
		Box = new JPanel();
		Box.setPreferredSize(new Dimension(400, 800));
		Box.setBackground(new Color(0,0,0,100));
		Box.setLayout(new FlowLayout(FlowLayout.CENTER,100,60));
		Box.add(clearT);
		for(int i=0;i<3;i++) {
			RB[i] = new JButton();
			RB[i].setPreferredSize(new Dimension(150, 120));
			Box.add(RB[i]);
			RB[i].addActionListener(this);
			RB[i].setIcon(rewardI[i]);
			RB[i].setModel(new BModel());
			RB[i].setOpaque(false);
			RB[i].setBorderPainted(false);
			RB[i].setContentAreaFilled(false);
		}
		//RB[0].setText("체력 100% 회복");
		//RB[1].setText("공격력 50% 증가");
		//RB[2].setText("방어력 20% 증가");
		add(Box);
		setVisible(false);
	}
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		if(bt.getIcon() == rewardI[0]) {
			GameManager.GM.PlayerHealth = GameManager.GM.PlayerMaxHealth;
			GameManager.GM.inGame.setHPbar();
			GameManager.GM.nextLavel();
			setVisible(false);
		}
		if(bt.getIcon() == rewardI[1]) {
			GameManager.GM.PlayerDamage *= 1.5f;
			GameManager.GM.nextLavel();
			setVisible(false);
		}
		if(bt.getIcon() == rewardI[2]) {
			GameManager.GM.PlayerArmor += 20;
			GameManager.GM.nextLavel();
			setVisible(false);
		}
	}
}
