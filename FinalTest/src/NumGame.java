import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NumGame extends JPanel implements ActionListener, MouseListener{
	int col = 0;
	ImageIcon[] image;
	Toolkit toolkit = getToolkit();
	NumGameButton Buttons[];
	public NumGame(int n) {
		col = n;
		GameManager.GM.col = col;
		image = new ImageIcon[col*col];
		GameManager.GM.map = n*n-1;
		GameManager.GM.NG = this;
		Buttons = new NumGameButton[n*n];
		GridLayout grid = new GridLayout(n, n);
		setLayout(grid);
		for(int i=0; i < n*n; ++i) {
			image[i] = new ImageIcon("./asset/"+(n)+"-"+i+".png");
		}
		for(int i = 0; i<n*n-1;i++) {
			Buttons[i] = new NumGameButton(i);
			int offset = Buttons[i].getInsets().left;
			//int offset = 1;
			//Buttons[i].setContentAreaFilled(false);
			Buttons[i].setPreferredSize(new Dimension(100, 100));
			Buttons[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Buttons[i].setMargin(new Insets(0,0,0,0));
			Icon img = resizeIcon(image[i], Buttons[i].getWidth()- offset, Buttons[i].getHeight() - offset);
			Buttons[i].setIcon(resizeIcon(image[i], Buttons[i].getWidth()- offset, Buttons[i].getHeight() - offset));
			//Buttons[i].setIcon(img);
			Buttons[i].setModel(new BModel());
			Buttons[i].setLayout(new BorderLayout());
			Buttons[i].setFocusPainted(false);
			add(Buttons[i]);
			Buttons[i].addActionListener(this);
			System.out.println(Buttons[i].getWidth() + "  " + Buttons[i].getHeight() + image[i].getIconHeight() + "   " + image[i].getIconWidth());		
		}
		Buttons[col*col-1] = new NumGameButton(-1);
		Buttons[col*col-1].setModel(new BModel());
		Buttons[col*col-1].setIcon(image[col*col-1]);
		add(Buttons[col*col-1]);
		Buttons[col*col-1].addActionListener(this);
		reset();
		//setSize(300, 300);
		setBorder(BorderFactory.createEmptyBorder(0 , 135 , 0 , 135));
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		NumGameButton bt = (NumGameButton)e.getSource();
		if(bt.value == -1)return;
		if((bt.index+1)% col != 1){
			if(Buttons[bt.index - 1].value == -1) {	
				Buttons[bt.index-1].value = (bt.value);
				Buttons[bt.index-1].setIcon(bt.getIcon());
				bt.value = -1;
				bt.setIcon(image[GameManager.GM.map]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				isSheild();
				GameManager.GM.countStep();
				isClear();
				
			}
		}
		if((bt.index+1)% col != 0){
			if(Buttons[bt.index + 1].value == -1) {	
				Buttons[bt.index+1].value = (bt.value);
				Buttons[bt.index+1].setIcon(bt.getIcon());
				bt.value = -1;
				bt.setIcon(image[col*col-1]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				isSheild();
				GameManager.GM.countStep();
				isClear();
			}
		}
		if((bt.index) - col >= 0) {
			if(Buttons[bt.index - col].value == -1) {
				Buttons[bt.index-col].value = (bt.value);
				Buttons[bt.index-col].setIcon(bt.getIcon());
				bt.value = -1;
				bt.setIcon(image[col*col-1]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				isSheild();
				GameManager.GM.countStep();
				isClear();
			}
		}
		if((bt.index) + col < col* col) {
			if(Buttons[bt.index + col].value == -1) {
				Buttons[bt.index+ col].value = (bt.value);
				Buttons[bt.index+ col].setIcon(bt.getIcon());
				bt.value = -1;
				bt.setIcon(image[col*col-1]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				isSheild();
				GameManager.GM.countStep();
				isClear();
			}
		}
	}
	
	public void reset() {
		while(true) {
			int in = 0;
			for(int i = 0;i < GameManager.GM.map;i++) {
				int ranIndex = (int)(Math.random()*GameManager.GM.map);
				//Buttons[i].setText(Integer.toString((int)(Math.random()*GameManager.GM.map)));
				//Buttons[i].setText(Integer.toString(ranIndex));
				Buttons[i].value = ranIndex;
				Buttons[i].setIcon(image[ranIndex]);
				for(int j=0;j<i;j++) {
					if(Buttons[i].value == Buttons[j].value) {
						i--;
					}
				}
				Buttons[GameManager.GM.map].value = -1;
				Buttons[GameManager.GM.map].setIcon(image[GameManager.GM.map]);
			}
			
			for(int C = 0;C<GameManager.GM.map ;C++) {
				for(int c = C +1;c < GameManager.GM.map ;c++) {
					if(Buttons[C].value > Buttons[c].value)
						in++;
				}
				
			}
			
			if(col %2 != 0) {
				if(in % 2 == 0) {
					break;
				}
			}
			else if(in % 2 == 0) {
				break;
			}
		}
		
	}
	public void isClear() {
			int CoCount = 0;
			int revCoCount = 0;
			for(int i = 0;i<GameManager.GM.map;i++) {
				if(Buttons[i].value == Buttons[i].index) {
					CoCount++;
				}
			}
			for(int i = 0;i<GameManager.GM.map+1;i++) {
				if(Buttons[i].value == Buttons[i].revIndex) {
					revCoCount++;
				}
			}
			if(CoCount == GameManager.GM.map) {
				reset();
				GameManager.GM.clear(0);
			}
			if(revCoCount == GameManager.GM.map) {
				reset();
				GameManager.GM.clear(1);
			}
		}
	public void isSheild() {
		if(GameManager.GM.isSheild == true) {
			GameManager.GM.playerSheild--;
			if(GameManager.GM.playerSheild <= 0) {
				GameManager.GM.isSheild = false;
				System.out.println("sheild end");
				GameManager.GM.enemy.Sheild.setVisible(false);
			}
		}
	}
	public void frozen() {
		for(int i = 0; i<col*col-1;i++) {
			Buttons[i].setEnabled(false);
			Buttons[i].setVisible(false);
		}
		Buttons[col*col-1].setEnabled(false);
		Buttons[col*col-1].setVisible(false);
	}
	public void unFrozen() {
		for(int i = 0; i<col*col-1;i++) {
			Buttons[i].setEnabled(true);
			Buttons[i].setVisible(true);
		}
		Buttons[col*col-1].setEnabled(true);
		Buttons[col*col-1].setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		NumGameButton bt = (NumGameButton)e.getSource();
		//GameManager.GM.ep.setVisible(true);
		if(bt.getText().equals("-1"))return;
		if((bt.index+1)% col != 1){
			if(Buttons[bt.index - 1].getText().equals("-1")) {	
				Buttons[bt.index-1].setText(bt.getText());
				bt.setText("-1");
				GameManager.GM.stepCount++;
				GameManager.GM.countStep();
				isClear();
				/*if(GameManager.GM.count>4) {
					reset();
					GameManager.GM.count=0;
				}*/
			}
		}
		if((bt.index+1)% col != 0){
			if(Buttons[bt.index + 1].getText().equals("-1")) {	
				Buttons[bt.index+1].setText(bt.getText());
				bt.setText("-1");
				GameManager.GM.stepCount++;
				GameManager.GM.countStep();
				isClear();
				/*if(GameManager.GM.count>4) {
					reset();
					GameManager.GM.count=0;
				}*/
			}
		}
		if((bt.index) - col >= 0) {
			if(Buttons[bt.index - col].getText().equals("-1")) {
				Buttons[bt.index-col].setText(bt.getText());
				bt.setText("-1");
				GameManager.GM.stepCount++;
				GameManager.GM.countStep();
				isClear();
				/*if(GameManager.GM.count>4) {
					reset();
					GameManager.GM.count=0;
				}*/
			}
		}
		if((bt.index) + col < col* col) {
			if(Buttons[bt.index + col].getText().equals("-1")) {
				Buttons[bt.index+ col].setText(bt.getText());
				bt.setText("-1");
				GameManager.GM.stepCount++;
				GameManager.GM.countStep();
				isClear();
				/*if(GameManager.GM.count>4) {
					reset();
					GameManager.GM.count=0;
				}*/
			}
		}
		
		
	}
	private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight)  {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		}
}


