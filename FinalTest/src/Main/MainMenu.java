package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainMenu extends JPanel implements ActionListener{
	JButton buttons[] = new JButton[3];	//난이도 선택 버튼 배열
	JPanel LevelP = new JPanel();	//난이도 선택 화면 패널
	JPanel LevelTP = new JPanel();	//난이도 선택 화면의 라벨과 뒤로가기의 패널
	JPanel startP = new JPanel();	//타이틀 화면의 시작, 종료 버튼의 패널
	JPanel titleP = new JPanel();	//타이틀 이미지 라벨을 넣을 패널
	JLabel NumLev = new JLabel("난이도");	//난이도 화면 설명 라벨
	ImageIcon startI = new ImageIcon("./asset/UI/startB.png");	//시작 버튼 이미지 초기화
	ImageIcon exitI = new ImageIcon("./asset/UI/exitB.png");	//종료 버튼 이미지 초기화
	ImageIcon title = new ImageIcon("./asset/UI/title.png");	//타이틀 이미지 초기화
	ImageIcon[] Num = new ImageIcon[3];		//난이도 버튼의 이미지 배열
	ImageIcon backI = new ImageIcon("./asset/UI/backB.png");	//뒤로가기 버튼 이미지 초기화
	
	//객체 생성자
	public MainMenu() {
		//객체 패널의 경개와 레이아웃 설정
		setBorder(BorderFactory.createEmptyBorder(150 , 0 , 0 , 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
		
		//시작 버튼 초기화
		JButton start = new JButton();
		start.setPreferredSize(new Dimension(250, 150));
		start.addActionListener(this);
		start.setIcon(startI);
		start.setOpaque(false);
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setModel(new BModel());
		
		//종료 버튼 초기화
		JButton eixt = new JButton();
		eixt.setPreferredSize(new Dimension(250, 150));
		eixt.addActionListener(this);
		eixt.setIcon(exitI);
		eixt.setOpaque(false);
		eixt.setBorderPainted(false);
		eixt.setContentAreaFilled(false);
		eixt.setModel(new BModel());
		
		//시작 화면의 버튼 패널 초기화
		startP.add(start);
		startP.add(eixt);
		startP.setBackground(new Color(0,0,0,0));
		startP.setPreferredSize(new Dimension(900, 1100));
		startP.setBorder(BorderFactory.createEmptyBorder(30 , 0 , 0 , 0));
		startP.setLayout(new FlowLayout(FlowLayout.CENTER, 800, 50));
		
		//타이틀 이미지 패널 초기화
		titleP.setBackground(Color.gray);
		titleP.add(new JLabel(title));
		titleP.setPreferredSize(new Dimension(600, 300));
		titleP.setVisible(true);
		
		//뒤로가기 버튼 초기화
		JButton back = new JButton();
		back.setIcon(backI);
		back.addActionListener(this);
		back.setPreferredSize(new Dimension(100, 100));
		back.setOpaque(false);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		
		//난이도 화면 설명 텍스트 설정 초기화
		NumLev.setFont(new Font("Dialog", Font.BOLD,60));
		NumLev.setForeground(Color.white);
		NumLev.setHorizontalAlignment(JLabel.CENTER);
		
		//난이도 선택 화면의 윗 패널 초기화
		LevelTP.setPreferredSize(new Dimension(900,200));
		LevelTP.setLayout(new FlowLayout(FlowLayout.LEFT,130,0));
		LevelTP.add(back);
		LevelTP.add(NumLev);
		LevelTP.setBackground(Color.gray);
		
		//난이도 패널에 윗 패널 삽입
		LevelP.add(LevelTP);
		
		//각 난이도 선택 버튼 설정
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
		//난이도 선택 패널 설정
		LevelP.setBackground(Color.gray);
		LevelP.setPreferredSize(new Dimension(900, 900));
		LevelP.setVisible(false);
		
		//객체의 배경색 설정
		setBackground(Color.gray);
		
		//각 패널을 객체에 추가
		add(LevelP);
		add(titleP);
		add(startP);
		
	}
	
	//버튼 이벤트 리스너 설정
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		
		//3x3버튼 클릭 시
		if(bt.getIcon() == Num[0]) {
			GameManager.GM.stageLevel = 1;
			GameManager.GM.startGame(3);
		}
		//4x4버튼 클릭 시
		if(bt.getIcon() == Num[1]) {
			GameManager.GM.stageLevel = 1;
			GameManager.GM.startGame(4);
		}
		//5x5버튼 클릭 시
		if(bt.getIcon() == Num[2]) {
			GameManager.GM.stageLevel = 1;
			GameManager.GM.startGame(5);
		}
		//시작버튼 클릭 시
		if(bt.getIcon() == startI) {
			GoLevel();
		}
		//시작버튼 클릭 시
		if(bt.getIcon() == exitI) {
			System.exit(0);
		}
		if(bt.getIcon() == backI) {
			GoStart();
		}
	}
	
	//난이도 선택 화면으로 이동
	public void GoLevel() {
		startP.setVisible(false);
		titleP.setVisible(false);
		LevelP.setVisible(true);
	}
	
	//타이틀 화면으로 이동
	public void GoStart() {
		LevelP.setVisible(false);
		startP.setVisible(true);
		titleP.setVisible(true);
	}

}
