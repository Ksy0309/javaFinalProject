import javax.swing.*;
import java.awt.*;

public class NumGameButton extends JButton{
	int index = 0;
	public NumGameButton(int n) {
		super(Integer.toString(n));
		if(n == -1) {
			index = GameManager.GM.map;
			//setText(Integer.toString((int)(Math.random()*GameManager.GM.map)));
		}
		else
			index = n;
	}
}
