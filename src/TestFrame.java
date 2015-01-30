import static org.junit.Assert.*;

import org.junit.Test;


public class TestFrame {
	
	@Test
	public void testInitialFrame() {
		Frame f = new Frame();
		assertEquals(FrameType.OPEN, f.getType());
		assertFalse(f.isComplete());
	}
	
	@Test
	public void testOpenType() {
		Frame f = new Frame();
		
		f.addThrow(3);
		assertEquals(FrameType.OPEN, f.getType());
		assertFalse(f.isComplete());
		
		f.addThrow(5);
		assertEquals(FrameType.OPEN, f.getType());
		assertTrue(f.isComplete());
	}
	
	@Test
	public void testSpareType() {
		Frame f = new Frame();
		
		f.addThrow(3);
		assertEquals(FrameType.OPEN, f.getType());
		assertFalse(f.isComplete());
		
		f.addThrow(7);
		assertEquals(FrameType.SPARE, f.getType());
		assertTrue(f.isComplete());
	}
	
	@Test
	public void testStrikeType() {
		Frame f = new Frame();
		
		f.addThrow(10);
		assertEquals(FrameType.STRIKE, f.getType());
		assertTrue(f.isComplete());
	}
}
