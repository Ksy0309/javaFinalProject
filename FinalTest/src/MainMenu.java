import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainMenu extends JPanel implements ActionListener{
	NumGameButton buttons[] = new NumGameButton[3];
	JPanel LavelP = new JPanel();
	
	public MainMenu() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 40, 40));
		setPreferredSize(new Dimension(300, 600));
		//setBackground(Color.black);
		for(int i=3;i<6;i++) {
			buttons[i-3] = new NumGameButton(i);
			LavelP.add(buttons[i-3]);
			buttons[i-3].addActionListener(this);
		}
		add(LavelP);
		
	}
	public void actionPerformed(ActionEvent e) {
		NumGameButton bt = (NumGameButton)e.getSource();
		//GameManager.GM.ep.setVisible(true);
		if(bt.getText().equals("3")) {
			GameManager.GM.stageLavel = 1;
			GameManager.GM.startGame(3);
		}
		if(bt.getText().equals("4")) {
			GameManager.GM.stageLavel = 1;
			GameManager.GM.startGame(4);
		}
		if(bt.getText().equals("5")) {
			GameManager.GM.stageLavel = 1;
			GameManager.GM.startGame(5);
		}
	}
	public void frozen() {
		for(int i=0;i<3;i++) {
			buttons[i].setEnabled(false);
		}
	}
	public void unFrozen() {
		for(int i = 0; i<3;i++) {
			buttons[i].setEnabled(true);
		}
	}
}
