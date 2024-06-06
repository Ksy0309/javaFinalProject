import javax.swing.*;
import java.awt.*;
public class Enemy extends JPanel{
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
	JProgressBar HPbar = new JProgressBar(JProgressBar.HORIZONTAL,0,MaxHealth);
	JPanel P;
	public Enemy(int level) {
		GameManager.GM.enemy = this;
		GridLayout grid = new GridLayout(3, 1);
		setLayout(null);
		NameText = new JLabel(name);
		NameText.setHorizontalAlignment(JLabel.CENTER);
		Sheild = new JLabel(Integer.toString(GameManager.GM.playerSheild));
		Sheild.setForeground(Color.green);
		Sheild.setVisible(false);
		Score = new JLabel();
		Score.setText(String.format("%010d", GameManager.GM.score));
		HPbar.setForeground(Color.red);
		P = new JPanel();
		Step = new JLabel(Integer.toString(attackStep));

		setLevel(GameManager.GM.stageLavel);
		NameText.setBounds(((286-50)/2),35,50,20);
		HPbar.setBounds(((286-200)/2), 55, 200, 15);
		P.setBounds(((286-150)/2), 80, 150, 150);
		Step.setBounds(286-50, (258/2), 50, 50);
		Sheild.setBounds(((286-40)),230,50,20);
		Score.setBounds(0, 0, 200, 40);
		add(NameText);
		HPbar.setStringPainted(true);
		add(HPbar);
		add(P);
		add(Step);
		add(Sheild);
		add(Score);
		setPreferredSize(new Dimension(286, 258));
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
			P.setBackground(Color.red);
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
			P.setBackground(Color.blue);
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
			P.setBackground(Color.green);
			setHPbar();
		}
	}
}
