package Main;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
public class AudioManager {
	File[] BGM = new File[3];
	Clip bgm = null;
	
	//배경음악 초기화
	public AudioManager() {
		BGM[0] = new File("./asset/sound/EnemyTheme1.wav");
		BGM[1] = new File("./asset/sound/EnemyTheme2.wav");
		BGM[2] = new File("./asset/sound/BossTheme.wav");
	}
	
	//배경음악 실행
	public void startBGM() {
		try {
			bgm = AudioSystem.getClip();
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(BGM[GameManager.GM.stageLevel - 1]);
			bgm.open(audioInputStream);
		}catch(LineUnavailableException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		bgm.loop(Clip.LOOP_CONTINUOUSLY);;
	}
	
	//배경음악 종료
	public void stopBGM() {
		bgm.stop();
	}
	
	//공격 효과음 출력
	public void attackSound() {
		Clip effSound = null;
		try {
			effSound = AudioSystem.getClip();
			File file = new File("./asset/sound/effect/attack_.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			effSound.open(audioInputStream);
		}catch(LineUnavailableException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		effSound.start();
	}
	
	//공격받는 효과음 출력
	public void beAttackedSound() {
		Clip effSound = null;
		try {
			effSound = AudioSystem.getClip();
			File file = new File("./asset/sound/effect/beAttacked_.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			effSound.open(audioInputStream);
		}catch(LineUnavailableException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		effSound.start();
	}
	
	//방어 효과음 출력
	public void SheildSound() {
		Clip effSound = null;
		try {
			effSound = AudioSystem.getClip();
			File file = new File("./asset/sound/effect/SheildSound.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			effSound.open(audioInputStream);
		}catch(LineUnavailableException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		effSound.start();
	}
}
