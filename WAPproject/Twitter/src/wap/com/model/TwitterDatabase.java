package wap.com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wap.controller.TwitterServlet;

import twitter4j.Location;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;

// Demo
public class TwitterDatabase {
	
	static public List<TwitterItem> getTwitterItems(String category) throws TwitterException {
		List <TwitterItem> twitterList = new ArrayList<TwitterItem> ();
		Map <String, TwitterItem> twitterMap = new HashMap<String, TwitterItem>();
		
		// base on category to update and return the list of twitter to servlet
		Twitter twitter = TwitterServlet.twitter;
		System.out.println(category);
		Query q = new Query(category);	
		q.setCount(100);				
		q.resultType(Query.RECENT);					
		q.setLang("en");				

		QueryResult r = twitter.search(q);
		
		for (Status s: r.getTweets())		
		{
		
			
			MediaEntity[] medias = s.getMediaEntities();
			if (medias.length != 0)		 {		  
				// Create the TwitterItem from Twitter status
				
				//item.setImage()
				// item.setHeader()
				if (twitterMap.get(s.getText()) == null) { // filter all the duplicate tweet 
					TwitterItem item = new TwitterItem();
					int pos = s.getText().toLowerCase().lastIndexOf("https://");
					if (pos >= 0) {
						String link = s.getText().substring(pos);
						item.setLink(link);
						
						item.setContent(s.getText());
					} else {
						item.setContent(s.getText());
					}
					
					item.setImage(medias[0].getMediaURL());
					
					twitterList.add(item);
					twitterMap.put(s.getText(), item);					
				}
			} else {
				if (twitterMap.get(s.getText()) == null) { // filter all the duplicate tweet 
					TwitterItem item = new TwitterItem();
					int pos = s.getText().toLowerCase().lastIndexOf("https://");
					if (pos >= 0) {
						String link = s.getText().substring(pos);
						item.setLink(link);
						//item.setContent(s.getText().substring(0, pos));
						item.setContent(s.getText());
					} else {
						item.setContent(s.getText());
					}
					item.setImage("images/tweetitem.jpg");
					
					twitterList.add(item);
					twitterMap.put(s.getText(), item);					
				}
			}
		}
		
		
		
		return twitterList;		
	}
	
	static public List<TwitterTrend> getTwitterTrends() throws TwitterException {
		List <TwitterTrend> trendList = new ArrayList<TwitterTrend> ();
				
		// base on category to update and return the list of twitter to servlet
		Twitter twitter = TwitterServlet.twitter;
		
		
		System.out.println("Showing available trends for Fairfield Iowa, woeid = 12780630");
        Trends tmp = twitter.getPlaceTrends(12780630);
    	for (int i = 0; i < tmp.getTrends().length; i++) {
    		System.out.println("query: " + tmp.getTrends()[i].getQuery());
    		System.out.println("name: " + tmp.getTrends()[i].getName());
    		System.out.println("url: " + tmp.getTrends()[i].getURL());
    		
    		TwitterTrend item = new TwitterTrend();
    		item.setName(tmp.getTrends()[i].getName());
    		item.setQuery(tmp.getTrends()[i].getQuery());
    		item.setUrl(tmp.getTrends()[i].getURL());
    		
    	}
		return trendList;		
		
	}

}
