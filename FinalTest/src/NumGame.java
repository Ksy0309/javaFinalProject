import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultButtonModel;

public class NumGame extends JPanel implements ActionListener, MouseListener{
	int map = 0;
	ImageIcon[] image;
	Toolkit toolkit = getToolkit();
	NumGameButton Buttons[];
	public NumGame(int n) {
		map = n;
		image = new ImageIcon[map*map];
		GameManager.GM.map = n*n-1;
		GameManager.GM.NG = this;
		Buttons = new NumGameButton[n*n];
		GridLayout grid = new GridLayout(n, n);
		//JPanel NumPuz = new JPanel();
		//NumPuz.setLayout(grid);
		setLayout(grid);
		for(int i=0; i < n*n; ++i) {
			image[i] = new ImageIcon("./num"+(n)+"-"+i+".png");
		}
		for(int i = 0; i<n*n-1;i++) {
			Buttons[i] = new NumGameButton(i);
			Buttons[i].setIcon(image[i]);
			Buttons[i].setModel(new BModel());
			Buttons[i].setFocusPainted(false);
			add(Buttons[i]);
			Buttons[i].addActionListener(this);
			//Buttons[i].addMouseListener(this);
		}
		Buttons[map*map-1] = new NumGameButton(-1);
		Buttons[map*map-1].setModel(new BModel());
		Buttons[map*map-1].setIcon(image[map*map-1]);
		add(Buttons[map*map-1]);
		Buttons[map*map-1].addActionListener(this);
		reset();
		//setPreferredSize(new Dimension(300,300));
		setSize(300, 300);
		//pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		NumGameButton bt = (NumGameButton)e.getSource();
		//GameManager.GM.ep.setVisible(true);
		if(bt.getText().equals("-1"))return;
		if((bt.index+1)% map != 1){
			if(Buttons[bt.index - 1].getText().equals("-1")) {	
				Buttons[bt.index-1].setText(bt.getText());
				Buttons[bt.index-1].setIcon(bt.getIcon());
				bt.setText("-1");
				bt.setIcon(image[8]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				GameManager.GM.countStep();
				isClear();
			}
		}
		if((bt.index+1)% map != 0){
			if(Buttons[bt.index + 1].getText().equals("-1")) {	
				Buttons[bt.index+1].setText(bt.getText());
				Buttons[bt.index+1].setIcon(bt.getIcon());
				bt.setText("-1");
				bt.setIcon(image[map*map-1]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				GameManager.GM.countStep();
				isClear();
			}
		}
		if((bt.index) - map >= 0) {
			if(Buttons[bt.index - map].getText().equals("-1")) {
				Buttons[bt.index-map].setText(bt.getText());
				Buttons[bt.index-map].setIcon(bt.getIcon());
				bt.setText("-1");
				bt.setIcon(image[map*map-1]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				GameManager.GM.countStep();
				isClear();
			}
		}
		if((bt.index) + map < map* map) {
			if(Buttons[bt.index + map].getText().equals("-1")) {
				Buttons[bt.index+ map].setText(bt.getText());
				Buttons[bt.index+ map].setIcon(bt.getIcon());
				bt.setText("-1");
				bt.setIcon(image[map*map-1]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
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
				Buttons[i].setText(Integer.toString(ranIndex));
				Buttons[i].setIcon(image[ranIndex]);
				for(int j=0;j<i;j++) {
					if(Buttons[i].getText().equals(Buttons[j].getText())) {
						i--;
					}
				}
				Buttons[GameManager.GM.map].setText("-1");
			}
			
			for(int C = 0;C<GameManager.GM.map ;C++) {
				for(int c = C +1;c < GameManager.GM.map ;c++) {
					if(Integer.valueOf(Buttons[C].getText()) > Integer.valueOf(Buttons[c].getText()))
						in++;
				}
				
			}
			
			if(map %2 != 0) {
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
			for(int i = 0;i<GameManager.GM.map;i++) {
				if(Buttons[i].getText().equals(Integer.toString(Buttons[i].index)) == true) {
					CoCount++;
				}
			}
			if(CoCount == GameManager.GM.map) {
				reset();
				GameManager.GM.clear();
			}
		}
	public void frozen() {
		for(int i = 0; i<map*map-1;i++) {
			Buttons[i].setEnabled(false);
			Buttons[i].setVisible(false);
		}
		Buttons[map*map-1].setEnabled(false);
		Buttons[map*map-1].setVisible(false);
	}
	public void unFrozen() {
		for(int i = 0; i<map*map-1;i++) {
			Buttons[i].setEnabled(true);
			Buttons[i].setVisible(true);
		}
		Buttons[map*map-1].setEnabled(true);
		Buttons[map*map-1].setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		NumGameButton bt = (NumGameButton)e.getSource();
		//GameManager.GM.ep.setVisible(true);
		if(bt.getText().equals("-1"))return;
		if((bt.index+1)% map != 1){
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
		if((bt.index+1)% map != 0){
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
		if((bt.index) - map >= 0) {
			if(Buttons[bt.index - map].getText().equals("-1")) {
				Buttons[bt.index-map].setText(bt.getText());
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
		if((bt.index) + map < map* map) {
			if(Buttons[bt.index + map].getText().equals("-1")) {
				Buttons[bt.index+ map].setText(bt.getText());
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

class BModel extends DefaultButtonModel{
	@Override
    public boolean isPressed() {
        return false;
    }

    @Override
    public boolean isRollover() {
        return false;
    }

    @Override
    public void setRollover(boolean b) {
        //NOOP
    }
}
