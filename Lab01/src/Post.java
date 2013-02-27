import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Represents an user post
 */
public class Post {
	private Link link;
	private GregorianCalendar when;
	
	/**
	 * Creates a user Post
	 * @param link Link that the user posted
	 * @param when When the user posted it
	 */
	public Post(Link link, GregorianCalendar when) {
		this.link = link;
		this.when = when;
	}

	/**
	 * Returns the link
	 * @return the link
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * Changes the link
	 * @param link new link
	 */
	public void setLink(Link link) {
		this.link = link;
	}

	/**
	 * Returns the date of the post
	 * @return the date of the post
	 */
	public GregorianCalendar getWhen() {
		return when;
	}

	/**
	 * Changes the date of the post
	 * @param when the new date of the post
	 */
	public void setWhen(GregorianCalendar when) {
		this.when = when;
	}
	
	/**
	 * Returns a String representation of the Post
	 * @return the string
	 */
	public String toString() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(when.getTime()) + ": " + link;
	}
}
