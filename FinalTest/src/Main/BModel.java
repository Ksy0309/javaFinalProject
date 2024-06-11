package Main;
import javax.swing.DefaultButtonModel;

public class BModel extends DefaultButtonModel{
		//클릭 시 효과X
		@Override
	    public boolean isPressed() {
	        return false;
	    }
		
		//마우스 커서가 올라올 시 효과X
	    @Override
	    public boolean isRollover() {
	        return false;
	    }
	    @Override
	    public void setRollover(boolean b) {
	        //NOOP
	    }
}

