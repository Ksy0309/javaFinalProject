package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Reward extends JPanel implements ActionListener{
	JButton RB[] = new JButton[3];	//레벨 클리어 보상 버튼 배열
	JPanel Box;		//버튼 박스 패널
	JLabel clearT;	//텍스트 출력 라벨
	ImageIcon[] rewardI = new ImageIcon[3];		//버튼 이미지 배열
	
	public Reward() {
		//패널 설정 초기화
		setBackground(new Color(0,0,0,128));
		setSize(800, 1000);
		setLayout(new FlowLayout(FlowLayout.CENTER,800,80));
		
		//보상 이미지 생성 후 초기화
		rewardI[0] = new ImageIcon("./asset/UI/HPposion.png");
		rewardI[1] = new ImageIcon("./asset/UI/addAttackP.png");
		rewardI[2] = new ImageIcon("./asset/UI/addArmorP.png");
		
		//클리어 텍스트 및 설정 초기화
		clearT = new JLabel("Level Clear!!!");
		clearT.setForeground(Color.white);
		clearT.setFont(new Font("Dialog", Font.BOLD,40));
		clearT.setHorizontalAlignment(JLabel.CENTER);
		
		//화면 박스 설정 초기화
		Box = new JPanel();
		Box.setPreferredSize(new Dimension(400, 800));
		Box.setBackground(new Color(0,0,0,100));
		Box.setLayout(new FlowLayout(FlowLayout.CENTER,100,60));
		Box.add(clearT);
		
		//레벨 클리어 보상 버튼 생성 후 설정 초기화
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
		
		//패널에 Box삽입
		add(Box);
		setVisible(false);
	}
	
	//각 버튼의 이벤트 리스너 정의
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		//체력 회복
		if(bt.getIcon() == rewardI[0]) {
			GameManager.GM.Player.Health = GameManager.GM.Player.MaxHealth;
			GameManager.GM.inGame.setHPbar();
			GameManager.GM.nextLavel();
			setVisible(false);
		}
		//공격력 증가
		if(bt.getIcon() == rewardI[1]) {
			GameManager.GM.Player.Damage *= 1.5f;
			GameManager.GM.nextLavel();
			setVisible(false);
		}
		//방어력 증가
		if(bt.getIcon() == rewardI[2]) {
			GameManager.GM.Player.Armor += 20;
			GameManager.GM.nextLavel();
			setVisible(false);
		}
	}
}
