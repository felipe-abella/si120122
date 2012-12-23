/**
 * Represents a Link that the user posts
 */
public class Link {
	private String link;
	
	/**
	 * Constructs a link
	 * @param link Link address
	 * @throws InvalidLinkException If the link is invalid, see "isLinkValid"
	 */
	public Link(String link) throws InvalidLinkException {
		if (!isLinkValid(link))
			throw new InvalidLinkException();
		this.link = link;
	}
	
	/**
	 * Returns the link
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * Changes the link
	 * @param newLink New link
	 * @throws InvalidLinkException If the link is invalid
	 */
	public void setLink(String newLink) throws InvalidLinkException {
		if (!isLinkValid(newLink))
			throw new InvalidLinkException();
		this.link = newLink;
	}
	
	/**
	 * Returns the website of a link, for example, the website of
	 * a link like "http://youtube.com/"... is "youtube.com"
	 * 
	 * @return the website
	 */
	public String getWebsite() {
		return link.split("/")[2];
	}
	
	/**
	 * Returns true if a link is valid, the link is valid if it begins
	 * with "http://" or "https://" and contains anything after that.
	 * @param link The link to check
	 * @return If it's valid
	 */
	public static boolean isLinkValid(String link) {
		return (link.startsWith("http://") && link.length() > "http://".length()) ||
				(link.startsWith("https://") && link.length() > "https://".length());
	}
	
	/**
	 * Returns a string representation of the object
	 * @return the string
	 */
	@Override
	public String toString() {
		return link;
	}
}
