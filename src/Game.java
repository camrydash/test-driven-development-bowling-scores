import java.util.ArrayList;

public class Game {
	private ArrayList<Frame> frames;
	private Frame currentFrame;
	private int framesPlayed;
	private boolean gameEnded;
	
	public Game() {
		frames = new ArrayList<Frame>();
		//initialize the game with 10 empty frames.
		for(int i = 0; i < 10; i++){
			frames.add(new Frame());
		}
		//initialize the current frame to be the first one.
		framesPlayed = 1;
		currentFrame = frames.get(0);
		gameEnded = false;
	}
	
	public void addThrow(int pins) {
		//if the game has ended return.
		if(gameEnded){
			return;
		}		
		//add the player's throw to the current frame.
		//also add this throw to all previous spares and
		//strikes that have unfinished scores.
		for(int i = framesPlayed-1; i >= 0; i--){
			Frame f = frames.get(i);
			if(!f.isScoreFinalized()){
				f.addThrow(pins);
			} 
		}
		//if the current frame is complete 
		//(its type has been determined)
		//we decide whether we 
		//(1) end the game, or,
		//(2) move on the next frame, or, 
		//(3) (edge cases) extend the game:
		//(3a) if the 10th frame is a spare, we need to add an 11th frame
		//(3b) if the 10th frame is a strike, we need to add an 11th frame
		//initially, and if it turns out to be a strike too, we need to add 
		//a 12th frame.
		if(currentFrame.isComplete()){
			if(framesPlayed == 10){
				if(currentFrame.getType() == FrameType.OPEN){
					//case(1): 10 frames have been played and last frame is OPEN
					gameEnded = true; 
				} else if(currentFrame.getType() == FrameType.SPARE ||
						currentFrame.getType() == FrameType.STRIKE){
					//case(3a) and (3b)
					framesPlayed++;
					currentFrame = new Frame();
					frames.add(currentFrame);
				} 
			} else if(framesPlayed == 11 && 
					currentFrame.getType() == FrameType.STRIKE){
				//case(3b) extended to 12th frame
				framesPlayed++;
				currentFrame = new Frame();
				frames.add(currentFrame);
			}
			if(!gameEnded){
				framesPlayed++;
				if(framesPlayed < frames.size()-1){
					//case(2)
					currentFrame = frames.get(framesPlayed-1);
				} else {
					//case(1): extended game of 11 or 12 frames has ended
					gameEnded = true;
				}
			}
		}	
	}	
	
	public int getScore(){
		int score = 0;
		for(Frame f : frames.subList(0, 10)) {
			score += f.getScore();
		}
		return score;
	}
	
	public int getFramesCompleted(){
		return ((currentFrame.isComplete()) ? framesPlayed : framesPlayed - 1) ;
	}
	
	public int getFrameScore(int index){
		return frames.get(index).getScore();
	}
}
