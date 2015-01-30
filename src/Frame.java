import java.util.ArrayList;


public class Frame {
	private FrameType type;
	private ArrayList<Integer> frameThrows;
	private int throwsNeeded;
	
	public Frame() {
		this(false);
	}
	
	public Frame(boolean isLastFrame) {
		this.type = FrameType.INCOMPLETE;
		this.frameThrows = new ArrayList<Integer>();
		this.throwsNeeded = 2;
	}
	
	public void addThrow(int pins) {
		frameThrows.add(pins);
		throwsNeeded--;
		updateFrameType();	
	}
	
	private void updateFrameType() {
		if(frameThrows.size() == 1 && this.frameThrows.get(0) == 10) {
			this.type = FrameType.STRIKE;
			this.throwsNeeded = 2;
		} else if(frameThrows.size() == 2) {
			int score = this.getScore();
			if (score == 10) {
				this.type = FrameType.SPARE;
				this.throwsNeeded = 1;
			} else if (score < 10) {
				this.type = FrameType.OPEN;
				this.throwsNeeded = 0; //redundant but added for clarity
			}
		}
	}
	
	public int getScore() {
		int score = 0;
		for(Integer t : this.frameThrows) {
			score += t;
		}
		return score;
	}

	public FrameType getType() {
		return type;
	}
	
	public int getThrowsNeeded() {
		return throwsNeeded;
	}
	
	public boolean isScoreFinalized() {
		return this.throwsNeeded == 0;
	}
	
	public boolean isComplete() {
		return type != FrameType.INCOMPLETE;
	}
}
