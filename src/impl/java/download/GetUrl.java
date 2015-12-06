package download;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetUrl {
	
	String baseUrl;
	String Uri;
	String CountPlaylistElement;
	
	public String getUri() {
		return Uri;
	}


	public void setUri(String uri) {
		Uri = uri;
	}


	public GetUrl(String baseUrl,String Uri) {
		// TODO Auto-generated constructor stub
		this.baseUrl = baseUrl;
		this.Uri = Uri;
	}
	
	
	public ArrayList<String> getUrls()    {
	    ArrayList<String> Url = new ArrayList<String>();
	    
	    
	    HttpClient httpclient = new DefaultHttpClient();
	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	    HttpGet request = new HttpGet(Uri);

	    try
	    {
	        org.apache.http.HttpResponse response = httpclient.execute(request);
	        HttpEntity resEntity = response.getEntity();
	        String _response= EntityUtils.toString(resEntity); // content will be consume only once

	      //System.out.println(_response); 
	      Document doc = Jsoup.parse(_response);
	     
	      for( Element element : doc.select("a[class*=playlist-video]") )
	      {
	    	  String linkHref = baseUrl+element.attr("href"); 
	    	  Url.add(linkHref);
	    	  
	      }
	      
	    }
	    catch(Exception e1)
	    {
	        e1.printStackTrace();
	    }

	    httpclient.getConnectionManager().shutdown();
	    
	   
	    return(Url);
	 }
	
	
	
	
	public ArrayList<String> getTitles()    {
	    ArrayList<String> Title = new ArrayList<String>();
	    
	    
	    HttpClient httpclient = new DefaultHttpClient();
	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	    HttpGet request = new HttpGet(Uri);

	    try
	    {
	        org.apache.http.HttpResponse response = httpclient.execute(request);
	        HttpEntity resEntity = response.getEntity();
	        String _response= EntityUtils.toString(resEntity); // content will be consume only once

	      //System.out.println(_response); 
	      Document doc = Jsoup.parse(_response);
	     
	      for( Element element : doc.select("h4[class*=yt-ui-ellipsis]") )
	      {
	    	  String text = element.text();
	    	  Title.add(text);
	    	  
	      }
	      
	    }
	    catch(Exception e1)
	    {
	        e1.printStackTrace();
	    }

	    httpclient.getConnectionManager().shutdown();
	    
	   
	    return(Title);
	 }
	
	public String getPlaylistTitle()    {
	    String PlayListTitle = null ;
	    
	    
	    HttpClient httpclient = new DefaultHttpClient();
	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	    HttpGet request = new HttpGet(Uri);

	    try
	    {
	        org.apache.http.HttpResponse response = httpclient.execute(request);
	        HttpEntity resEntity = response.getEntity();
	        String _response= EntityUtils.toString(resEntity); // content will be consume only once

	      //System.out.println(_response); 
	      Document doc = Jsoup.parse(_response);
	     
	      for( Element element : doc.select("h3[class=playlist-title] > a") )
	      {
	    	   PlayListTitle = element.text();
	    	  
	    	  
	      }
	      
	    }
	    catch(Exception e1)
	    {
	        e1.printStackTrace();
	    }

	    httpclient.getConnectionManager().shutdown();
	    
	   
	    return PlayListTitle;
	 }
	
	
	public ArrayList<String> getThumbPic()    {
	    ArrayList<String> picUrl = new ArrayList<String>();
	    
	    
	    HttpClient httpclient = new DefaultHttpClient();
	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	    HttpGet request = new HttpGet(Uri);

	    try
	    {
	        org.apache.http.HttpResponse response = httpclient.execute(request);
	        HttpEntity resEntity = response.getEntity();
	        String _response= EntityUtils.toString(resEntity); // content will be consume only once

	      //System.out.println(_response); 
	      Document doc = Jsoup.parse(_response);
	     
	      Elements element = doc.select("span[class=yt-thumb-clip]");
	      
	    
	      for (int i = 0; i < element.size()-1; i++) {
	    	  
	    	  
	    	  if(element.get(i).child(0).attr("data-thumb")=="")
	    		  picUrl.add(element.get(i).child(0).attr("src"));
	    	  else picUrl.add(element.get(i).child(0).attr("data-thumb"));
			
		}
	      
	    }
	    catch(Exception e1)
	    {
	        e1.printStackTrace();
	    }

	    httpclient.getConnectionManager().shutdown();
	    
	   
	    return(picUrl);
	 }
	
	
	
	
	
}


