import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainMenu extends JPanel implements ActionListener{
	NumGameButton buttons[] = new NumGameButton[3];
	JPanel LavelP = new JPanel();
	JPanel startP = new JPanel();
	
	public MainMenu() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 40, 40));
		setPreferredSize(new Dimension(300, 600));
		//setBackground(Color.black);
		NumGameButton start = new NumGameButton(7);
		start.setText("Start");
		NumGameButton eixt = new NumGameButton(7);
		eixt.setText("Eixt");
		start.addActionListener(this);
		startP.add(start);
		startP.add(eixt);
		NumGameButton back = new NumGameButton(7);
		back.setText("<-");
		back.addActionListener(this);
		LavelP.add(back);
		for(int i=3;i<6;i++) {
			buttons[i-3] = new NumGameButton(i);
			LavelP.add(buttons[i-3]);
			buttons[i-3].addActionListener(this);
		}
		LavelP.setVisible(false);
		add(LavelP);
		add(startP);
	}
	public void actionPerformed(ActionEvent e) {
		NumGameButton bt = (NumGameButton)e.getSource();
		JButton BT = (JButton)e.getSource();
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
		if(bt.getText().equals("Start")) {
			GoLavel();
		}
		if(bt.getText().equals("Eixt")) {
			//GoLavel();
		}
		if(bt.getText().equals("<-")) {
			GoStart();
		}
	}
	public void GoLavel() {
		startP.setVisible(false);
		LavelP.setVisible(true);
	}
	
	public void GoStart() {
		LavelP.setVisible(false);
		startP.setVisible(true);
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
