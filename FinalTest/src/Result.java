import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Result extends JPanel implements ActionListener{
	JLabel resultMassage;
	JPanel MP;
	JButton endGame;
	ScoreResult score;
	public Result() {
		setSize(300, 600);
		setLayout(null);
		setBackground(new Color(0,0,0,128));
		resultMassage = new JLabel("점수판");
		MP = new JPanel();
		//MP.setBackground(Color.black);
		MP.add(resultMassage);
		MP.setBounds(45, 25, 200, 50);
		MP.setBackground(new Color(0,0,0,0));
		score = new ScoreResult();
		resultMassage.setForeground(Color.white);
		resultMassage.setFont(new Font("Dialog", Font.BOLD,25));
		resultMassage.setHorizontalAlignment(JLabel.CENTER);
		endGame = new JButton("메인 메뉴로 돌아가기");
		endGame.setBounds(65, 480, 150, 50);
		endGame.addActionListener(this);
		add(MP);
		add(score);
		add(endGame);
		setVisible(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		//JButton bt = (JButton)e.getSource();
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
