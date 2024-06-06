import javax.swing.*;
import java.awt.*;

public class NumGameButton extends JButton{
	int index = 0;
	int revIndex = 0;
	public NumGameButton(int n) {
		super(Integer.toString(n));
		if(n == -1) {
			index = GameManager.GM.map;
			revIndex = 0;
		}
		else {
			index = n;
			revIndex = GameManager.GM.map - n;
		}
	}
}
