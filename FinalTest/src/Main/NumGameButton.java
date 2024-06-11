package Main;
import javax.swing.*;
import java.awt.*;

public class NumGameButton extends JButton{
	int index = 0;
	int revIndex = 0;
	int value;
	public NumGameButton(int n) {
		//마지막 인덱스인 버튼일 경우
		if(n == -1) {
			index = GameManager.GM.map;
			revIndex = 0;
			value = -1;
		}
		//마지막 버튼이 아닐 경우
		else {
			index = n;
			revIndex = GameManager.GM.map - n;
		}
	}
}
