import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Manages an user Timeline, containing all of its posts
 */
public class Timeline {
	/** How many Posts to see at a time */
	public static final int MANY_SHOW = 10;
	
	private String errorMessage = null;

	private List<Post> posts;
	
	/**
	 * Creates a new Timeline
	 */
	public Timeline() {
		posts = new ArrayList<Post>();
	}
	
	/**
	 * Adds a post on the timeline
	 * @param link Link of the post
	 * @param when Time of the post
	 * @throws InvalidPostOrder If tried to add a Post younger than a Post already on the list
	 */
	public void addPost(Link link, GregorianCalendar when) throws InvalidPostOrder {
		if (posts.size() > 0 && posts.get(posts.size()-1).getWhen().compareTo(when) > 0)
			throw new InvalidPostOrder();
		posts.add(new Post(link, when));
	}
	
	public void postEvent(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		String str = context.getApplication().evaluateExpressionGet(context, "#{linkCreator.str}", String.class);
		
		errorMessage = "";
		
		try {
			Link link = new Link(str);
			addPost(link, new GregorianCalendar()); 
		} catch (InvalidPostOrder e) {
			errorMessage = "An unexpected error occurred!";
		} catch (InvalidLinkException e) {
			errorMessage = "Your link is invalid, it must start with http:// or https://";
		} 
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Returns the time of the last post, or null if there's no post in the list
	 * @return the time
	 */
	public GregorianCalendar getLastPostTime() {
		if (posts.size() > 0)
			return posts.get(posts.size()-1).getWhen();
		return null;
	}
	
	/**
	 * Returns a string representation of the last post time
	 * @return the string
	 */
	public String getLastPostTimeStr() {
		GregorianCalendar result = getLastPostTime();
		if (result != null)
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(result.getTime());
		return "n/a";
	}
	
	/**
	 * Returns the average time between two posts (in seconds)
	 * @return average time (in seconds)
	 */
	public Double getAveragePostTime() {
		if (posts.size() < 2)
			return null;
		return (posts.get(posts.size()-1).getWhen().getTimeInMillis() -
				posts.get(0).getWhen().getTimeInMillis()) / (1000.0 * (posts.size()-1));
	}
	
	/**
	 * Get a string representation of the average post time
	 * @return the string
	 */
	public String getAveragePostTimeStr() {
		Double result = getAveragePostTime();
		if (result == null)
			return "n/a";
		return result.toString() + " seconds";
	}
	
	/**
	 * Returns a list of the last (at most)N posts from this Timeline
	 * @param N
	 * @return the list
	 */
	public List<Post> getLastNPosts(int N) {
		List<Post> result = new ArrayList<Post>();
		for (int i = 1; i <= N && posts.size()-i >= 0; i++)
			result.add(posts.get(posts.size()-i));
		return result;
	}
	
	/**
	 * Get a string representation of the last posts
	 * @return the string
	 */
	public String getLastPostsStr() {
		String result = "";
		List<Post> list = getLastNPosts(MANY_SHOW);
		for (int i = 0; i < list.size(); i++)
			result += list.get(i) + "<br/>";
		if (list.size() == 0)
			result += "(none)";
		return result;
	}
	
	/**
	 * Returns the most common website. If there are more than one, returns any of them.
	 * @return the most common website
	 */
	public String getMostCommonWebsite() {
		Map<String, Integer> count = new HashMap<String, Integer>();
		int maxCount = 0;
		String result = "n/a";
		
		for (Post post: posts) {
			String website = post.getLink().getWebsite();
			if (count.get(website) == null)
				count.put(website, 0);
			count.put(website, count.get(website)+1);
			if (count.get(website) > maxCount) {
				maxCount = count.get(website);
				result = website;
			}
		}
		
		return result;
	}
}
