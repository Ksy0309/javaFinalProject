package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Result extends JPanel implements ActionListener{
	JLabel resultMassage; //게임 클리어 또는 게임 오버여부를 출력하는 라벨
	JPanel MP;				//컴포넌트들을 모아놓은 박스 패널
	JButton endGame;		//타이틀 화면으로 이동하는 버튼
	ScoreResult score;		//최종 정보 출력 객체
	ImageIcon endHome = new ImageIcon("./asset/UI/resultHomeB.png");
	public Result() {
		//패널의 사이즈, 레이아웃, 배경 설정
		setSize(800, 1000);
		setLayout(null);
		setBackground(new Color(0,0,0,128));
		
		//클리어 여부 텍스트 JLavel 초기화 
		resultMassage = new JLabel("점수판");
		resultMassage.setForeground(Color.white);
		resultMassage.setFont(new Font("Dialog", Font.BOLD,60));
		resultMassage.setHorizontalAlignment(JLabel.CENTER);
		
		//패널 초기화
		MP = new JPanel();
		MP.add(resultMassage);
		MP.setBounds(150, 50, 500, 120);
		MP.setBackground(new Color(0,0,0,0));
		
		//점수판 객체 생성
		score = new ScoreResult();
		
		
		//인게임 종료버튼 생성 및 초기화
		endGame = new JButton("메인 메뉴로 돌아가기");
		endGame.setBounds(250, 750, 300, 150);
		endGame.setIcon(endHome);
		endGame.setModel(new BModel());
		endGame.setOpaque(false);
		endGame.setBorderPainted(false);
		endGame.setContentAreaFilled(false);
		endGame.addActionListener(this);
		
		//생성한 컨포넌트 삽입
		add(MP);
		add(score);
		add(endGame);
		
		//Visible 설정
		setVisible(false);
	}
	
	//종료 버튼 이벤트 리스너
	public void actionPerformed(ActionEvent e) {
		GameManager.GM.exitGame();
		}
	
	//클리어 여부 텍스트 초기화
	public void setResultText(int R) {
		if(R == 1) {
			resultMassage.setText("Game Clear");
		}
		else if(R ==0) {
			resultMassage.setText("Game Over");
		}
		score.getScore();
	}
}
