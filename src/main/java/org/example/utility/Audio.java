// This class was inspired by internet

package org.example.utility;

import java.io.*;
import javax.sound.sampled.*;
 
public class Audio extends Thread {
     
    AudioInputStream audioInputStream = null;
    SourceDataLine line;
    String audio;
    
    public Audio(String audio) {
		super();
		this.audio = audio;	}

    @Override
    public void run() {
    	
        try {
        	audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getResource(audio)); }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace(); }
        catch (IOException e) {
        	e.printStackTrace(); }
        
        AudioFormat audioFormat = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
        
        try {
        	line = (SourceDataLine) AudioSystem.getLine(info); }
        catch (LineUnavailableException e) {
        	e.printStackTrace();
        	return; }
          
        try {
        	line.open(audioFormat); }
        catch (LineUnavailableException e) {
        	e.printStackTrace();
        	return; }
        
        line.start();

        try {
        	byte bytes[] = new byte[1024];
        	int bytesRead=0;
        	while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
        		line.write(bytes, 0, bytesRead); } }
        catch (IOException io) {
        	io.printStackTrace();
        	return; } } }