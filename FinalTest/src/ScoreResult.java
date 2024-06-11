import javax.swing.*;
import java.awt.*;
public class ScoreResult extends JPanel{
	JPanel time , step, kill,damage, total;
	JLabel t1, t2, s1, s2, k1, k2,	d1,	d2, to1, to2;
	JLabel s3;
	JLabel s4;
	JLabel s5;
	public ScoreResult() {
		setBounds(150, 150, 500, 550);
		setBackground(new Color(0,0,0,180));
		setBorder(BorderFactory.createEmptyBorder(0 , 60 , 0 , 80));
		GridLayout grid = new GridLayout(5, 1);
		setLayout(grid);
		time = new JPanel();
		time.setBackground(new Color(0,0,0,0));
		step = new JPanel();
		step.setBackground(new Color(0,0,0,0));
		kill = new JPanel();
		kill.setBackground(new Color(0,0,0,0));
		damage = new JPanel();
		damage.setBackground(new Color(0,0,0,0));
		total = new JPanel();
		total.setBackground(new Color(0,0,0,0));
		
		time.setLayout(new BorderLayout());
		t1 = new JLabel("소요시간 : ");
		t2 = new JLabel(GameManager.GM.getMin()+ " : "+ GameManager.GM.getSec());
		t1.setForeground(Color.white);
		t1.setFont(new Font("Dialog", Font.BOLD,30));
		t2.setForeground(Color.white);
		t2.setFont(new Font("Dialog", Font.BOLD,30));
		time.add(t1, BorderLayout.WEST);
		time.add(t2, BorderLayout.EAST);
		
		step.setLayout(new BorderLayout());
		s1 = new JLabel("스텝 : ");
		s2 = new JLabel(""+GameManager.GM.stepCount);
		s1.setForeground(Color.white);
		s1.setFont(new Font("Dialog", Font.BOLD,30));
		s2.setForeground(Color.white);
		s2.setFont(new Font("Dialog", Font.BOLD,30));
		step.add(s1, BorderLayout.WEST);
		step.add(s2, BorderLayout.EAST);
		
		kill.setLayout(new BorderLayout());
		k1 = new JLabel("KILL : ");
		k2 = new JLabel(""+(GameManager.GM.stageLavel-1));
		k1.setForeground(Color.white);
		k1.setFont(new Font("Dialog", Font.BOLD,30));
		k2.setForeground(Color.white);
		k2.setFont(new Font("Dialog", Font.BOLD,30));
		kill.add(k1, BorderLayout.WEST);
		kill.add(k2, BorderLayout.EAST);
		
		damage.setLayout(new BorderLayout());
		d1 = new JLabel("총 데미지 : ");
		d2 = new JLabel(""+(GameManager.GM.totalDamage));
		d1.setForeground(Color.white);
		d1.setFont(new Font("Dialog", Font.BOLD,30));
		d2.setForeground(Color.white);
		d2.setFont(new Font("Dialog", Font.BOLD,30));
		damage.add(d1, BorderLayout.WEST);
		damage.add(d2, BorderLayout.EAST);
		
		total.setLayout(new BorderLayout());
		to1 = new JLabel("총 점수 : ");
		to2 = new JLabel(""+(GameManager.GM.stageLavel-1));
		to1.setForeground(Color.white);
		to1.setFont(new Font("Dialog", Font.BOLD,30));
		to2.setForeground(Color.white);
		to2.setFont(new Font("Dialog", Font.BOLD,30));
		total.add(to1, BorderLayout.WEST);
		total.add(to2, BorderLayout.EAST);
		
		add(time);
		add(step);
		add(kill);
		add(damage);
		add(total);
	}
	public void getScore() {
		t2.setText(GameManager.GM.getMin()+ " : "+ GameManager.GM.getSec());
		s2.setText(""+GameManager.GM.stepCount);
		k2.setText(""+(GameManager.GM.stageLavel-1));
		d2.setText(""+(GameManager.GM.totalDamage));
		to2.setText(GameManager.GM.getTotalScore());
	}
}
