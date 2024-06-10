import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
public class AudioManager {
	File[] BGM = new File[3];
	Clip bgm = null;
	public AudioManager() {
		BGM[0] = new File("");
		BGM[1] = new File("");
		BGM[2] = new File("");
	}
	
	public void startBGM() {
		try {
			bgm = AudioSystem.getClip();
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(BGM[GameManager.GM.stageLavel - 1]);
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
	public void stopBGM() {
		bgm.stop();
	}
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
