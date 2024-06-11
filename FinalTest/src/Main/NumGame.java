package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NumGame extends JPanel implements ActionListener{
	int col = 0;						//퍼즐의 열 개수
	ImageIcon[] image;					//퍼즐 그림의 이미지 배열
	NumGameButton Buttons[];			//퍼즐 그림의 버튼 배열
	
	// 생성자
	public NumGame(int n) {
		col = n;	//열 개수 초기화
		GameManager.GM.col = col;	//GM의 열 개수 초기화
		image = new ImageIcon[col*col];	//이미지 배열 개수 초기화
		GameManager.GM.map = n*n-1;		//GM의 퍼즐의 마지막 그림 조각 인덱스 초기화
		GameManager.GM.NG = this;		//GM에 객체 연결
		
		
		//퍼즐의 각 행과 열의 개수만큼 그리드레이아웃 설정
		GridLayout grid = new GridLayout(n, n);
		setLayout(grid);
		
		//퍼즐의 각 그림 경로 초기화
		for(int i=0; i < n*n; ++i) {
			image[i] = new ImageIcon("./asset/"+(n)+"-"+i+".png");
		}
		
		//버튼 배열의 개수 초기화
		Buttons = new NumGameButton[n*n];
		
		//마지막 버튼의 인덱스까지 버튼 성생 후 초기화하여 패널에 삽입
		for(int i = 0; i<n*n-1;i++) {
			Buttons[i] = new NumGameButton(i);
			Buttons[i].setPreferredSize(new Dimension(100, 100));
			Buttons[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Buttons[i].setMargin(new Insets(0,0,0,0));
			Buttons[i].setModel(new BModel());
			Buttons[i].setLayout(new BorderLayout());
			Buttons[i].setFocusPainted(false);
			add(Buttons[i]);
			Buttons[i].addActionListener(this);
		}
		
		//마지막 인덱스의 버튼인 공백 버튼 생성 후 초기화하여 패널에 삽입
		Buttons[GameManager.GM.map] = new NumGameButton(-1);
		Buttons[GameManager.GM.map].setModel(new BModel());
		Buttons[GameManager.GM.map].setIcon(image[GameManager.GM.map]);
		Buttons[GameManager.GM.map].addActionListener(this);
		add(Buttons[GameManager.GM.map]);
		
		//그림의 랜덤 배치
		reset();
		
		//패널의 크기, 볼더, 출력 여부 초기화
		setPreferredSize(new Dimension(800, 480));
		setBorder(BorderFactory.createEmptyBorder(0 , 160 , 0 , 160));
		setVisible(true);
	}
	
	//각 퍼즐의 조각들의 이벤트리스너 정의
	public void actionPerformed(ActionEvent e) {
		NumGameButton bt = (NumGameButton)e.getSource();
		
		//클릭한 버튼이 공백그림일 경우
		if(bt.value == -1)return;
		
		//클릭한 버튼이 왼쪽 구석이 아닐 경우
		if((bt.index+1)% col != 1){
			//클릭한 버튼의 왼쪽 버튼이 공백 이미지 버튼일 경우 이미지 스왑
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
		
		//클릭한 버튼이 오른쪽 구석이 아닐 경우
		if((bt.index+1)% col != 0){
			//클릭한 버튼의 오른쪽 버튼이 공백 이미지 버튼일 경우 이미지 스왑
			if(Buttons[bt.index + 1].value == -1) {	
				Buttons[bt.index+1].value = (bt.value);
				Buttons[bt.index+1].setIcon(bt.getIcon());
				bt.value = -1;
				bt.setIcon(image[GameManager.GM.map]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				isSheild();
				GameManager.GM.countStep();
				isClear();
			}
		}
		
		//클릭한 맨 위가 아닐 경우
		if((bt.index) - col >= 0) {
			//클릭한 버튼의 윗쪽 버튼이 공백 이미지 버튼일 경우 이미지 스왑
			if(Buttons[bt.index - col].value == -1) {
				Buttons[bt.index-col].value = (bt.value);
				Buttons[bt.index-col].setIcon(bt.getIcon());
				bt.value = -1;
				bt.setIcon(image[GameManager.GM.map]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				isSheild();
				GameManager.GM.countStep();
				isClear();
			}
		}
		
		//클릭한 맨 아래가 아닐 경우
		if((bt.index) + col < col* col) {
			//클릭한 버튼의 아랫쪽 버튼이 공백 이미지 버튼일 경우 이미지 스왑
			if(Buttons[bt.index + col].value == -1) {
				Buttons[bt.index+ col].value = (bt.value);
				Buttons[bt.index+ col].setIcon(bt.getIcon());
				bt.value = -1;
				bt.setIcon(image[GameManager.GM.map]);
				GameManager.GM.stepCount++;
				GameManager.GM.tmpStep++;
				isSheild();
				GameManager.GM.countStep();
				isClear();
			}
		}
	}
	
	//퍼즐의 이미지들을 랜덤 배치하는 메소드
	public void reset() {
		while(true) {
			int in = 0;
			
			//랜덤 배치(중복X)
			for(int i = 0;i < GameManager.GM.map;i++) {
				int ranIndex = (int)(Math.random()*GameManager.GM.map);
				Buttons[i].value = ranIndex;
				Buttons[i].setIcon(image[ranIndex]);
				for(int j=0;j<i;j++) {
					if(Buttons[i].value == Buttons[j].value) {
						i--;
					}
				}
				//마지막 버튼의 이미지 초기화
				Buttons[GameManager.GM.map].value = -1;
				Buttons[GameManager.GM.map].setIcon(image[GameManager.GM.map]);
			}
			//배치한 퍼즐의 완성 가능 여부확인을 위해 inversion측정
			for(int C = 0;C<GameManager.GM.map ;C++) {
				for(int c = C +1;c < GameManager.GM.map ;c++) {
					if(Buttons[C].value > Buttons[c].value)
						in++;
				}
				
			}
			
			//열의 개수가 홀수 이고 inversion이 짝수일 경우 완성 가능(공백 이미지의 인덱스가 고정이므로)
			if(col %2 != 0) {
				if(in % 2 == 0) {
					break;
				}
			}
			//열의 개수가 짝수 이고 inversion이 짝수일 경우 완성 가능(공백 이미지의 인덱스가 고정이므로)
			else if(in % 2 == 0) {
				break;
			}
		}
		
	}
	
	//퍼즐의 완성 여부 확인
	public void isClear() {
			int CoCount = 0;		//정해진 자리와 이미지의 대응 개수
			int revCoCount = 0;		//역순인 자리와 이미지의 대응 개수
			//CoCount 계산
			for(int i = 0;i<GameManager.GM.map;i++) {
				if(Buttons[i].value == Buttons[i].index) {
					CoCount++;
				}
			}
			//revCoCount 계산
			for(int i = 0;i<GameManager.GM.map+1;i++) {
				if(Buttons[i].value == Buttons[i].revIndex) {
					revCoCount++;
				}
			}
			//정순 완성일 경우 공격
			if(CoCount == GameManager.GM.map) {
				reset();
				GameManager.GM.attack(0);
			}
			//역순 완성일 경우 방어
			if(revCoCount == GameManager.GM.map) {
				reset();
				GameManager.GM.attack(1);
			}
		}
	
	//플레이어의 방어 상태여부 확인과 카운트 감소
	public void isSheild() {
		if(GameManager.GM.isSheild == true) {
			GameManager.GM.Player.Sheild--;
			if(GameManager.GM.Player.Sheild <= 0) {
				GameManager.GM.isSheild = false;
				System.out.println("sheild end");
				GameManager.GM.enemy.Sheild.setVisible(false);
			}
		}
	}
	
	//퍼즐 이미지 버튼의 사용 비활성화 및 안보이게 함
	public void frozen() {
		for(int i = 0; i<GameManager.GM.map;i++) {
			Buttons[i].setEnabled(false);
			Buttons[i].setVisible(false);
		}
		Buttons[col*col-1].setEnabled(false);
		Buttons[col*col-1].setVisible(false);
	}
	
	//퍼즐 이미지 버튼의 사용 활성화 및 보이게 함
	public void unFrozen() {
		for(int i = 0; i<GameManager.GM.map;i++) {
			Buttons[i].setEnabled(true);
			Buttons[i].setVisible(true);
		}
		Buttons[col*col-1].setEnabled(true);
		Buttons[col*col-1].setVisible(true);
	}
	
}


