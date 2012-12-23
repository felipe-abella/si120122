import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests class Post
 */
public class PostTest {
	/**
	 * Test Post
	 */
	@Test
	public void test() throws InvalidLinkException {
		Link link = new Link("http://youtube.com");
		GregorianCalendar when = new GregorianCalendar();
		Post post = new Post(link, when);
		
		Assert.assertEquals(post.getLink(), link);
		Assert.assertEquals(post.getWhen(), when);
		
		Link link2 = new Link("http://youtube2.com");
		GregorianCalendar when2 = new GregorianCalendar();
		
		post.setLink(link2);
		post.setWhen(when2);
		
		Assert.assertEquals(post.getLink(), link2);
		Assert.assertEquals(post.getWhen(), when2);
	}
}
