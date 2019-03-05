import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.sun.jdi.event.Event;







public class Main {
	
	static JFrame f = new JFrame("Meu primeiro video musical");
	static MyDrawPanel ml;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main mini = new Main();
		mini.go();
		
}
	
	
	public void setUpGui() {
		ml = new MyDrawPanel();
		f.setContentPane(ml);
		f.setBounds(30,30,300,300);
		f.setVisible(true);
		
	}
	
	
	public void go() {
		setUpGui();
		try {
			
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addControllerEventListener(ml, new int[] {127});
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			
			int r = 0;
			
			for (int i=0; i<60; i+=4) {
				
				r = (int) ((Math.random() * 50)  );
				
				track.add(makeEvent(144, 1, r, 100, i));
				track.add(makeEvent(176, 1, 127, 0, i));
				track.add(makeEvent(128, 1, r, 100, i + 2));
			}
			
			sequencer.setSequence(seq);
			sequencer.start();
			sequencer.setTempoInBPM(120);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		
		MidiEvent event = null;
		
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return event;
	}
	
	
	

	

	
}
