import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class InGame extends JPanel{
	JProgressBar HPbar = new JProgressBar(JProgressBar.HORIZONTAL,0,GameManager.GM.PlayerMaxHealth);
	NumGame NumPuz;
	Enemy enemy;
	
	public InGame(int n) {
		
		GridLayout grid = new GridLayout(2, 1);
		NumPuz = new NumGame(n);
		JPanel p = new JPanel();
		
		HPbar.setForeground(Color.red);
		HPbar.setStringPainted(true);
		HPbar.setValue(GameManager.GM.PlayerHealth);
		HPbar.setString(GameManager.GM.PlayerHealth + " / " +GameManager.GM.PlayerMaxHealth);
		p.setSize(300, 300);
		p.setLayout(new BorderLayout());
		
		enemy = new Enemy(n-2);
		p.add("Center", enemy);
		p.add("South", HPbar);
		add(p);
		add(NumPuz);
		
		setPreferredSize(new Dimension(300,600));
		setLayout(grid);
		setVisible(true);
		GameManager.GM.inGame = this;
		System.out.println(enemy.getSize());
		
	}
        
 
    public void setHPbar() {
    	if(GameManager.GM.PlayerHealth < 0) {
    		HPbar.setValue(0);
    		HPbar.setString(0 + " / " +GameManager.GM.PlayerMaxHealth);
    		
    	}
    	else {
    		
    		HPbar.setValue(GameManager.GM.PlayerHealth);
    		HPbar.setString(GameManager.GM.PlayerHealth + " / " +GameManager.GM.PlayerMaxHealth);
    	}
    }
}
