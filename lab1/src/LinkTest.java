import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests the class Link
 */
public class LinkTest {
	/**
	 * Test getters
	 */
	@Test
	public void testGetters() throws InvalidLinkException {
		Link link1 = new Link("http://youtube.com/foo");
		Assert.assertEquals(link1.getLink(), "http://youtube.com/foo");
		Assert.assertEquals(link1.getWebsite(), "youtube.com");
	}
	
	/**
	 * Tests if the constructor is correctly throwing exception
	 */
	@Test(expected=InvalidLinkException.class)
	public void testException() throws InvalidLinkException {
		new Link("ht1p://something.com");
	}
	
	/**
	 * Tests Test.isLinkValid
	 */
	public void testIsValid() {
		Assert.assertTrue(Link.isLinkValid("http://youtube.com/foo"));
		Assert.assertTrue(Link.isLinkValid("http://youtube.com/"));
		Assert.assertTrue(Link.isLinkValid("http://youtube.com"));
		Assert.assertTrue(Link.isLinkValid("https://youtube.com/foo"));
		Assert.assertTrue(Link.isLinkValid("https://youtube.com/"));
		Assert.assertTrue(Link.isLinkValid("https://youtube.com"));
		Assert.assertFalse(Link.isLinkValid("http://"));
		Assert.assertFalse(Link.isLinkValid("https://"));
		Assert.assertFalse(Link.isLinkValid("htt://test.com"));
		Assert.assertFalse(Link.isLinkValid("httl://test2.com"));
	}
}
