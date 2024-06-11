package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Enemy extends JPanel implements ActionListener{
	//적의 기본 스텟
	int MaxHealth = 100;
	int Health;
	int attackPower = 10;
	int attackStep = 0;
	int MaxAttackStep = 100;
	
	//텍스트 또는 이미지를 출력할 JLabel
	JLabel NameText;
	JLabel Step;
	JLabel Sheild;
	JLabel Score;
	JLabel enemyI = new JLabel();
	JLabel backI = new JLabel();
	
	//Escape메뉴 버튼
	JButton esc;
	
	//적 모습 이미지 정의
	ImageIcon level1 = new ImageIcon("./asset/Enemy/Mushmom.png");
	ImageIcon level2 = new ImageIcon("./asset/Enemy/Colossus.png");
	ImageIcon level3 = new ImageIcon("./asset/Enemy/Boss.png");
	
	//적 HP바 정의
	JProgressBar HPbar = new JProgressBar(JProgressBar.HORIZONTAL,0,MaxHealth);
	
	//객체 내 사용될 판넬 정의
	JPanel Back, topMenu, P;
	public Enemy() {
		//GM에 객체 연결
		GameManager.GM.enemy = this;
		setLayout(null);
		
		//적 이름 UI 설정
		NameText = new JLabel();
		NameText.setHorizontalAlignment(JLabel.CENTER);
		NameText.setBounds(300,40,200,100);
		NameText.setFont(new Font("Dialog", Font.BOLD, 30));
		
		//현제 점수 UI설정
		Score = new JLabel();
		Score.setText("현재 점수 : "+String.format("%010d", GameManager.GM.score));
		Score.setFont(new Font("Dialog", Font.BOLD, 25));
		Score.setBounds(30, 10, 400, 40);
		
		//적의 HP바 UI설정
		HPbar.setForeground(Color.red);
		HPbar.setBounds(200, 120, 400, 15);
		HPbar.setStringPainted(true);
		
		//적의 모습판넬 설정
		P = new JPanel();
		P.add(enemyI);
		P.setBounds(250, 140, 300, 300);
		P.setOpaque(true);
		P.setBackground(new Color(0,0,0,0));
		
		//적의 배경 판넬 설정
		Back = new JPanel();
		Back.add(backI);
		Back.setSize(800,500);
		
		//화면 맨 위의 UI설정
		topMenu = new JPanel();
		topMenu.setSize(800, 60);
		
		//적 공격 카운트 스텝 UI설정
		Step = new JLabel(Integer.toString(attackStep));
		Step.setBounds(550, 230, 100, 100);
		Step.setBorder(BorderFactory.createLineBorder(Color.black));
		Step.setHorizontalAlignment(JLabel.CENTER);
		Step.setFont(new Font("Dialog", Font.BOLD,25));
		
		//방어 카운트 UI설정
		Sheild = new JLabel(Integer.toString(GameManager.GM.Player.Sheild));
		Sheild.setForeground(Color.green);
		Sheild.setBounds(685, 340,100,100);
		Sheild.setBorder(BorderFactory.createLineBorder(Color.green));
		Sheild.setHorizontalAlignment(JLabel.CENTER);
		Sheild.setFont(new Font("Dialog", Font.BOLD, 25));
		Sheild.setVisible(false);
		
		//esc메뉴 버튼 설정
		esc = new JButton();
		esc.setBounds(730, 0, 60, 60);
		esc.setIcon(new ImageIcon("./asset/UI/escIcon.png"));
		esc.setOpaque(false);
		esc.setBorderPainted(false);
		esc.setContentAreaFilled(false);
		esc.addActionListener(this);
		esc.setModel(new BModel());
		
		//GM의 stageLevel조정
		setLevel(GameManager.GM.stageLevel);
		
		//판넬에 모든 컴포넌트 축가
		add(NameText);
		add(HPbar);
		add(Step);
		add(P);
		add(Sheild);
		add(Score);
		add(esc);
		add(topMenu);
		add(Back);
	}
	
	//적의 체력 조정
	public void setHPbar() {
		//적 처치 시
		if(Health <= 0) {
			HPbar.setValue(0);
			HPbar.setString(0 + " / " +MaxHealth);
			GameManager.GM.clearLavel();
		}
		//적 공격 시
		else {
			HPbar.setValue(Health);
			HPbar.setString(Health + " / " +MaxHealth);
		}
    }
	
	//적의 공격 스텝 카운트 조정
	public void setStep() {
		attackStep--;
		Step.setText(Integer.toString(attackStep));
		Sheild.setText(Integer.toString(GameManager.GM.Player.Sheild));
		
		//스텝 카운트가 0일시 플레이어 공격
		if(Step.getText().equals("0")) {
			GameManager.GM.BeAttecked();
			attackStep = MaxAttackStep;
			Step.setText(Integer.toString(attackStep));
			if(GameManager.GM.Player.Health <= 0) {
				GameManager.GM.endGame(0);
			}
		}
	}
	
	//스테이지 레벨에 따라 Enemy객체의 정보 변경
	public void setLevel(int level) {
		if(level == 1) {
			MaxHealth = 50;
			Health = MaxHealth; 
			HPbar.setMaximum(MaxHealth);
			attackPower = 10;
			MaxAttackStep = 80*(GameManager.GM.col-2+GameManager.GM.col-3);
			attackStep = MaxAttackStep;
			NameText.setText("머쉬맘");
			Step.setText(Integer.toString(attackStep));
			enemyI.setIcon(level1);
			backI.setIcon(new ImageIcon("./asset/Enemy/MushmomBack.png"));
			setHPbar();
		}
		if(level == 2) {
			MaxHealth = 70;
			Health = MaxHealth; 
			HPbar.setMaximum(MaxHealth);
			attackPower = 20;
			MaxAttackStep = 60*(GameManager.GM.col-2+GameManager.GM.col-3); 
			attackStep = MaxAttackStep;
			NameText.setText("거상");
			Step.setText(Integer.toString(attackStep));
			enemyI.setIcon(level2);
			backI.setIcon(new ImageIcon("./asset/Enemy/ColossusBack.png"));
			setHPbar();
		}
		if(level == 3) {
			MaxHealth = 100;
			Health = MaxHealth; 
			HPbar.setMaximum(MaxHealth);
			attackPower = 25;
			MaxAttackStep = 50*(GameManager.GM.col-2+GameManager.GM.col-3); 
			attackStep = MaxAttackStep;
			NameText.setText("보스");
			Step.setText(Integer.toString(attackStep));
			enemyI.setIcon(level3);
			backI.setIcon(new ImageIcon("./asset/Enemy/BossBack.png"));
			setHPbar();
		}
	}
	
	//Escape 메뉴 출력 액션 리스너
	public void actionPerformed(ActionEvent e) {
		GameManager.GM.escOn();
	}
}
