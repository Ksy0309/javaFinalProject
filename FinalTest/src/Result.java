import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Result extends JPanel implements ActionListener{
	JLabel resultMassage;
	JPanel MP;
	JButton endGame;
	ScoreResult score;
	ImageIcon endHome = new ImageIcon("./asset/UI/resultHomeB.png");
	public Result() {
		setSize(900, 1260);
		setLayout(null);
		setBackground(new Color(0,0,0,128));
		resultMassage = new JLabel("점수판");
		MP = new JPanel();
		MP.add(resultMassage);
		MP.setBounds(200, 50, 500, 120);
		MP.setBackground(new Color(0,0,0,0));
		score = new ScoreResult();
		resultMassage.setForeground(Color.white);
		resultMassage.setFont(new Font("Dialog", Font.BOLD,60));
		resultMassage.setHorizontalAlignment(JLabel.CENTER);
		endGame = new JButton("메인 메뉴로 돌아가기");
		endGame.setBounds(300, 1010, 300, 150);
		endGame.setIcon(endHome);
		endGame.setModel(new BModel());
		endGame.setOpaque(false);
		endGame.setBorderPainted(false);
		endGame.setContentAreaFilled(false);
		endGame.addActionListener(this);
		add(MP);
		add(score);
		add(endGame);
		setVisible(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("!!!!!!");
		GameManager.GM.exitGame();
		}
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
