import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Enemy extends JPanel implements ActionListener{
	int MaxHealth = 100;
	int Health;
	int attackPower = 10;
	int attackStep = 0;
	int MaxAttackStep = 100;
	String name = new String("");
	JLabel NameText;
	JLabel Step;
	JLabel Sheild;
	JLabel Score;
	JButton esc;
	ImageIcon level1 = new ImageIcon("./asset/Enemy/Mushmom.png");
	ImageIcon level2 = new ImageIcon("./asset/Enemy/Colossus.png");
	ImageIcon level3 = new ImageIcon("./asset/Enemy/Boss.png");
	JProgressBar HPbar = new JProgressBar(JProgressBar.HORIZONTAL,0,MaxHealth);
	JPanel Back, topMenu;
	JPanel P;
	public Enemy(int level) {
		GameManager.GM.enemy = this;
		GridLayout grid = new GridLayout(3, 1);
		setLayout(null);
		NameText = new JLabel(name);
		NameText.setHorizontalAlignment(JLabel.CENTER);
		Sheild = new JLabel(Integer.toString(GameManager.GM.playerSheild));
		Sheild.setForeground(Color.green);
		//Sheild.setVisible(false);
		Score = new JLabel();
		Score.setText("현재 점수 : "+String.format("%010d", GameManager.GM.score));
		Score.setFont(new Font("Dialog", Font.BOLD, 25));
		Score.setBounds(30, 10, 400, 40);
		HPbar.setForeground(Color.red);
		P = new JPanel();
		Back = new JPanel();
		topMenu = new JPanel();
		Back.setSize(800,500);
		topMenu.setSize(800, 60);
		Step = new JLabel(Integer.toString(attackStep));
		esc = new JButton();
		setLevel(GameManager.GM.stageLavel);
		NameText.setBounds(300,40,200,100);
		NameText.setFont(new Font("Dialog", Font.BOLD, 30));
		HPbar.setBounds(200, 120, 400, 15);
		P.setBounds(250, 140, 300, 300);
		P.setOpaque(true);
		P.setBackground(new Color(0,0,0,0));
		//P.setBounds(250, 150, 400, 400);
		//Step.setBounds(650, 200, 100, 100);
		Step.setBounds(550, 230, 100, 100);
		Step.setBorder(BorderFactory.createLineBorder(Color.black));
		Step.setHorizontalAlignment(JLabel.CENTER);
		Step.setFont(new Font("Dialog", Font.BOLD,25));
		//Sheild.setBounds(790, 325,100,100);
		Sheild.setBounds(685, 340,100,100);
		Sheild.setBorder(BorderFactory.createLineBorder(Color.green));
		Sheild.setHorizontalAlignment(JLabel.CENTER);
		Sheild.setFont(new Font("Dialog", Font.BOLD, 25));
		Sheild.setVisible(false);
		esc.setBounds(730, 0, 60, 60);
		esc.setIcon(new ImageIcon("./asset/UI/escIcon.png"));
		esc.setOpaque(false);
		esc.setBorderPainted(false);
		esc.setContentAreaFilled(false);
		esc.addActionListener(this);
		esc.setModel(new BModel());
		add(new JLabel(new ImageIcon("./asset/Enemy/Boss.png")));
		add(NameText);
		HPbar.setStringPainted(true);
		add(HPbar);
		add(Step);
		add(P);
		
		add(Sheild);
		add(Score);
		add(esc);
		add(topMenu);
		add(Back);
		//setPreferredSize(new Dimension(286, 258));
		System.out.println(getSize());
		
	}
	
	public void setHPbar() {
		if(Health <= 0) {
			HPbar.setValue(0);
			HPbar.setString(0 + " / " +MaxHealth);
			GameManager.GM.clearLavel();
		}
		else {
			HPbar.setValue(Health);
			HPbar.setString(Health + " / " +MaxHealth);
		}
    }
	
	public void setStep() {
		attackStep--;
		Step.setText(Integer.toString(attackStep));
		Sheild.setText(Integer.toString(GameManager.GM.playerSheild));
		if(Step.getText().equals("0")) {
			GameManager.GM.BeAttecked();
			attackStep = MaxAttackStep;
			Step.setText(Integer.toString(attackStep));
			if(GameManager.GM.PlayerHealth <= 0) {
				GameManager.GM.endGame(0);
			}
		}
	}
	
	public void setLevel(int level) {
		if(level == 1) {
			MaxHealth = 50;
			Health = MaxHealth; 
			HPbar.setMaximum(MaxHealth);
			attackPower = 10;
			MaxAttackStep = 80*(GameManager.GM.col-2+GameManager.GM.col-3);
			attackStep = MaxAttackStep;
			name = "적1";
			NameText.setText(name);
			Step.setText(Integer.toString(attackStep));
			//P.setBackground(Color.red);
			P.add(new JLabel(level1));
			Back.add(new JLabel(new ImageIcon("./asset/Enemy/MushmomBack.png")));
			setHPbar();
		}
		if(level == 2) {
			MaxHealth = 70;
			Health = MaxHealth; 
			HPbar.setMaximum(MaxHealth);
			attackPower = 20;
			MaxAttackStep = 60*(GameManager.GM.col-2+GameManager.GM.col-3); 
			attackStep = MaxAttackStep;
			name = "적2";
			NameText.setText(name);
			Step.setText(Integer.toString(attackStep));
			//P.setBackground(Color.blue);
			P.add(new JLabel(level2));
			Back.add(new JLabel(new ImageIcon("./asset/Enemy/ColossusBack.png")));
			setHPbar();
		}
		if(level == 3) {
			MaxHealth = 100;
			Health = MaxHealth; 
			HPbar.setMaximum(MaxHealth);
			attackPower = 25;
			MaxAttackStep = 50*(GameManager.GM.col-2+GameManager.GM.col-3); 
			attackStep = MaxAttackStep;
			name = "보스";
			NameText.setText(name);
			Step.setText(Integer.toString(attackStep));
			//P.setBackground(Color.green);
			P.add(new JLabel(level3));
			Back.add(new JLabel(new ImageIcon("./asset/Enemy/BossBack.png")));
			setHPbar();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		GameManager.GM.escOn();
	}
}
