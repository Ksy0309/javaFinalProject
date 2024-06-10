import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainMenu extends JPanel implements ActionListener{
	JButton buttons[] = new JButton[3];
	JPanel LevelP = new JPanel();
	JPanel LevelTP = new JPanel();
	JPanel startP = new JPanel();
	JPanel titleP = new JPanel();
	JLabel NumLev = new JLabel("난이도");
	ImageIcon startI = new ImageIcon("./asset/UI/startB.png");
	ImageIcon exitI = new ImageIcon("./asset/UI/exitB.png");
	ImageIcon title = new ImageIcon("./asset/UI/title.png");
	ImageIcon[] Num = new ImageIcon[3];
	ImageIcon backI = new ImageIcon("./asset/UI/backB.png");
	public MainMenu() {
		setBorder(BorderFactory.createEmptyBorder(150 , 0 , 0 , 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
		//setPreferredSize(new Dimension(300, 600));
		JButton start = new JButton();
		//start.setText("Start");
		start.setPreferredSize(new Dimension(250, 150));
		JButton eixt = new JButton();
		//eixt.setText("Eixt");
		eixt.setPreferredSize(new Dimension(250, 150));
		start.addActionListener(this);
		start.setIcon(startI);
		start.setOpaque(false);
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setModel(new BModel());
		eixt.addActionListener(this);
		eixt.setIcon(exitI);
		eixt.setOpaque(false);
		eixt.setBorderPainted(false);
		eixt.setContentAreaFilled(false);
		eixt.setModel(new BModel());
		startP.add(start);
		startP.add(eixt);
		startP.setBackground(new Color(0,0,0,0));
		startP.setPreferredSize(new Dimension(900, 1100));
		startP.setBorder(BorderFactory.createEmptyBorder(30 , 0 , 0 , 0));
		startP.setLayout(new FlowLayout(FlowLayout.CENTER, 800, 50));
		titleP.setBackground(Color.gray);
		titleP.add(new JLabel(title));
		titleP.setPreferredSize(new Dimension(600, 300));
		titleP.setVisible(true);
		JButton back = new JButton();
		back.setIcon(backI);
		back.addActionListener(this);
		back.setPreferredSize(new Dimension(100, 100));
		back.setOpaque(false);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		LevelTP.add(back);
		LevelTP.add(NumLev);
		LevelTP.setBackground(Color.gray);
		NumLev.setFont(new Font("Dialog", Font.BOLD,60));
		NumLev.setForeground(Color.white);
		NumLev.setHorizontalAlignment(JLabel.CENTER);
		//LevelTP.setBackground(Color.blue);
		LevelTP.setPreferredSize(new Dimension(900,200));
		LevelTP.setLayout(new FlowLayout(FlowLayout.LEFT,130,0));
		LevelP.add(LevelTP);
		for(int i=3;i<6;i++) {
			buttons[i-3] = new JButton();
			buttons[i-3].setPreferredSize(new Dimension(250, 250));
			LevelP.add(buttons[i-3]);
			buttons[i-3].addActionListener(this);
			Num[i-3] = new ImageIcon("./asset/UI/"+i+"x"+i+"B.png");
			buttons[i-3].setIcon(Num[i-3]);
			buttons[i-3].setOpaque(false);
			buttons[i-3].setBorderPainted(false);
			buttons[i-3].setContentAreaFilled(false);
			buttons[i-3].setModel(new BModel());
			
		}
		LevelP.setBackground(Color.gray);
		LevelP.setPreferredSize(new Dimension(900, 900));
		LevelP.setVisible(false);
		setBackground(Color.gray);
		add(LevelP);
		add(titleP);
		add(startP);
		
	}
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		JButton BT = (JButton)e.getSource();
		if(bt.getIcon() == Num[0]) {
			GameManager.GM.stageLavel = 1;
			GameManager.GM.startGame(3);
		}
		if(bt.getIcon() == Num[1]) {
			GameManager.GM.stageLavel = 1;
			GameManager.GM.startGame(4);
		}
		if(bt.getIcon() == Num[2]) {
			GameManager.GM.stageLavel = 1;
			GameManager.GM.startGame(5);
		}
		if(bt.getIcon() == startI) {
			GoLevel();
		}
		if(bt.getIcon() == exitI) {
			System.exit(0);
		}
		if(bt.getIcon() == backI) {
			GoStart();
		}
	}
	public void GoLevel() {
		startP.setVisible(false);
		titleP.setVisible(false);
		LevelP.setVisible(true);
	}
	
	public void GoStart() {
		LevelP.setVisible(false);
		startP.setVisible(true);
		titleP.setVisible(true);
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
