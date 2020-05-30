package scraping;

public class RunApp {
	public static void main(String[] args) {
		ScrapService scs = new RuliwebScraping();
		scs.scrapingRun();
	}
}
