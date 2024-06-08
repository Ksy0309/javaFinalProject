import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Game extends JFrame implements KeyListener{
	int GameLavel = 1;
	InGame ingame;
	MainMenu menu;
	Reward reward;
	Result result;
	EscapeMenu esc;
	Container con = getContentPane();
	public Game() {
		setTitle("첫번째 윈도우 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameManager.GM.game = this;
		menu = new MainMenu();
		reward = new Reward();
		result = new Result(); 
		esc = new EscapeMenu();
		con.add(esc);
		con.add(reward);
		con.add(result);
		con.add(menu);
		con.addKeyListener(this);
		con.setFocusable(true);
		con.requestFocus();
		setPreferredSize(new Dimension(900, 1260));
		//setSize(900, 1200);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void keyTyped(KeyEvent e) {
	}
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
