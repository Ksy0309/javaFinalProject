import javax.swing.*;
import java.awt.*;
public class Game extends JFrame{
	int GameLavel = 1;
	InGame ingame;
	MainMenu menu;
	Reward reward;
	Result result;
	Container con = getContentPane();
	public Game() {
		setTitle("첫번째 윈도우 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		GameManager.GM.game = this;
		menu = new MainMenu();
		reward = new Reward();
		result = new Result(); 
		con.add(reward);
		con.add(result);
		con.add(menu);
		setPreferredSize(new Dimension(300, 600));
		pack();
		setVisible(true);
	}
}
