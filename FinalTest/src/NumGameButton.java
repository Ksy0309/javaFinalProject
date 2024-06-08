import javax.swing.*;
import java.awt.*;

public class NumGameButton extends JButton{
	int index = 0;
	int revIndex = 0;
	int value;
	public NumGameButton(int n) {
		if(n == -1) {
			index = GameManager.GM.map;
			revIndex = 0;
			value = GameManager.GM.map;
		}
		else {
			index = n;
			revIndex = GameManager.GM.map - n;
		}
	}
}
