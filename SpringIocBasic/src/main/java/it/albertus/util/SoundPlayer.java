package it.albertus.util;

import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine.Info;

import org.springframework.stereotype.Component;

@Component
public class SoundPlayer {

	public void play(InputStream waveInputStream) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(waveInputStream);
			AudioFormat format = stream.getFormat();
			Info info = new Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
			stream.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
