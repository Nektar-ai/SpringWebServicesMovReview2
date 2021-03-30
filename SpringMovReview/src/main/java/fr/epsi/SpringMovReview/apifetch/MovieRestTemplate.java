package fr.epsi.SpringMovReview.apifetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRestTemplate {

	private String page;
	private Results[] results;
	private String rez = "";

		
	
	private String resultsJsonArrayToString()
	{
		
		for (Results res : results)
		{
			rez += res.toString()+",";
		}
		if(rez.endsWith(","))
		{
		  rez = rez.substring(0,rez.length() - 1);
		  rez += "]";
		}
		return rez;
	}
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Results[] getResults() {
		return results;
	}
	public void setResults(Results[] results) {
		this.results = results;
	}
	
	@Override
	public String toString() {

	this.resultsJsonArrayToString()	;
		
		return "{" +
				"\"page\":" + page +
				", \"results\":[" + 
					rez +				
				'}';
	  }	
}
