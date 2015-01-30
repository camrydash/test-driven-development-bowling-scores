import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestGame {
	
	@Test
	public void testCreateGame() {
		Game g = new Game();
		assertEquals(0, g.getFramesCompleted());
		assertEquals(0, g.getScore());
	}

	@Test
	public void testSingleOpenFrame() {
		Game g = new Game();
		g.addThrow(2);
		g.addThrow(3);
		assertEquals(1, g.getFramesCompleted());
		assertEquals(5, g.getFrameScore(0));
		assertEquals(5, g.getScore());
	}
	
	@Test
	public void testSingleSpareFrame() {
		Game g = new Game();
		g.addThrow(4);
		g.addThrow(6);
		assertEquals(1, g.getFramesCompleted());
		assertEquals(10, g.getFrameScore(0));
		assertEquals(10, g.getScore());
		
		g.addThrow(4);
		assertEquals(1, g.getFramesCompleted());
		assertEquals(14, g.getFrameScore(0));
		assertEquals(4, g.getFrameScore(1));
		assertEquals(18, g.getScore());
	}
	
	@Test
	public void testSingleStrikeFrame() {
		Game g = new Game();
		g.addThrow(10);
		assertEquals(1, g.getFramesCompleted());
		assertEquals(10, g.getFrameScore(0));
		assertEquals(10, g.getScore());
		
		g.addThrow(4);
		g.addThrow(5);
		
		assertEquals(2, g.getFramesCompleted());
		assertEquals(19, g.getFrameScore(0));
		assertEquals(9, g.getFrameScore(1));
		assertEquals(28, g.getScore());
	}
	
	@Test
	public void testSampleGame() {
		Game g = new Game();
		//frame 1:strike
		g.addThrow(10);
		assertEquals(1, g.getFramesCompleted());
		assertEquals(10, g.getFrameScore(0));

		//frame 2:spare
		g.addThrow(3);
		assertEquals(1, g.getFramesCompleted());
		assertEquals(13, g.getFrameScore(0));
		assertEquals(3, g.getFrameScore(1));
		
		g.addThrow(7);
		assertEquals(2, g.getFramesCompleted());
		assertEquals(20, g.getFrameScore(0));
		assertEquals(10, g.getFrameScore(1));
		
		//frame 3:strike
		g.addThrow(10);
		assertEquals(3, g.getFramesCompleted());
		assertEquals(20, g.getFrameScore(1));
		assertEquals(10, g.getFrameScore(2));
		
		//frame 4:strike
		g.addThrow(10);
		assertEquals(4, g.getFramesCompleted());
		assertEquals(20, g.getFrameScore(2));
		assertEquals(10, g.getFrameScore(3));
		
		
		//frame 5:strike
		g.addThrow(10);
		assertEquals(5, g.getFramesCompleted());
		assertEquals(30, g.getFrameScore(2));
		assertEquals(20, g.getFrameScore(3));
		assertEquals(10, g.getFrameScore(4));
	
		//frame 6:open
		g.addThrow(4);
		assertEquals(5, g.getFramesCompleted());
		assertEquals(24, g.getFrameScore(3));
		assertEquals(14, g.getFrameScore(4));
		assertEquals(4, g.getFrameScore(5));
		
		g.addThrow(3);
		assertEquals(6, g.getFramesCompleted());
		assertEquals(17, g.getFrameScore(4));
		assertEquals(7, g.getFrameScore(5));
		
		//frame 7:open
		g.addThrow(6);
		assertEquals(6, g.getFramesCompleted());
		assertEquals(6, g.getFrameScore(6));
		
		g.addThrow(2);
		assertEquals(7, g.getFramesCompleted());
		assertEquals(8, g.getFrameScore(6));
		
		//frame 8:strike
		g.addThrow(10);
		assertEquals(9, g.getFramesCompleted());
		assertEquals(10, g.getFrameScore(7));
		
		System.out.println(g.getScore());
		
		//frame 9:strike
		g.addThrow(10);
		//assertEquals(9, g.getFramesCompleted());
		//assertEquals(20, g.getFrameScore(8));
		//assertEquals(10, g.getFrameScore(8));
		

		//frame 10:strike
		g.addThrow(10);
		//assertEquals(10, g.getFramesCompleted());
		//assertEquals(30, g.getFrameScore(7));
		//assertEquals(20, g.getFrameScore(8));
		//assertEquals(10, g.getFrameScore(9));
		
		//frame 11: BONUS + strike
		g.addThrow(10);
		//assertEquals(11, g.getFramesCompleted());
		//assertEquals(30, g.getFrameScore(8));
		//assertEquals(20, g.getFrameScore(9));
		
		
		//frame 12: BONUS + strike
		g.addThrow(10);
		//assertEquals(12, g.getFramesCompleted());
		//assertEquals(30, g.getFrameScore(9));
		
		//final game score is
		// 20 + 20 + 30 + 14 + 17 + 7 + 8 + 30 + 30 + 30
		// ==> 160

		assertEquals(216, g.getScore());		
	}

}
