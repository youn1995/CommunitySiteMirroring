package scraping;

public class Board {
	private int id;
	private String division;
	private String title;
	private String body;
	private String writer;
	private int hits;
	private int recommend;
	private String time;
	private String url;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id =id;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return id + " " + division + " " + title + " " + writer +" "
				+ hits + " " + recommend + " " + time + " "+ url + "\n"
				+ body + "\n";
	}
	
	
	
}
