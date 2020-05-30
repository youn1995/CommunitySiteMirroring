package scraping;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import database.CommityDAO;

public class RuliwebScraping implements ScrapService {

	public String scrapingBody(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements bodyContents = doc.select("div[class=\"view_content\"]");
			List<Element> bodies = new ArrayList<>();
			for (Element body : bodyContents) {
				bodies.addAll(body.select("p"));

			}
			String body = "";
			int j = 0;
			String[] bodyAndImg = new String[bodies.size()];
			for (Element bod : bodies) {
				if (bod.select("video") != null ) {
					bodyAndImg[j] = bod.getElementsByTag("video").attr("src").trim();
				}
				if (bod.hasText()) {
					if (bod.text().equals("GIF")) {
						
					} else {
						bodyAndImg[j] = bod.text().trim();
					}
				} else if (bod.select("img[scr]") != null) {
						bodyAndImg[j] = bod.getElementsByTag("img").attr("src").trim();
					
				}
				j++;
			}
			for (int k = 0; k < bodyAndImg.length; k++) {
				if (bodyAndImg[k].equals("")) {

				} else if(Pattern.matches("//*.*.*", bodyAndImg[k])) {
					body = body + (("https:" +bodyAndImg[k]) + "\n");
				} else {
					body = body + (bodyAndImg[k] + "\n");
				}
			}
			return body;
		} catch (IOException e) {
			return null;
		}

	}

	public void scrapingRun() {
		String mainUrl = "https://bbs.ruliweb.com/community/board/300143?view_best=1";
		try {
			Document doc = Jsoup.connect(mainUrl).get();
//			String html = doc.html();
//			Elements bestPosts = doc.select("table[class=\"board_list_table\"]");
			Elements listPosts = doc.select("tr[class=\"table_body\"]");
//			List<Map<String, String>> listOfPost = new ArrayList<Map<String, String>>();
			List<Board> listOfPost = new ArrayList<>();

			for (Element ele : listPosts) {
//				Map<String, String> dataOfPost = new HashMap<>();
				Board board = new Board();
				int id = Integer.valueOf(ele.select("td[class=\"id\"]").text().trim());
				board.setId(id);
				String division = ele.select("td[class=\"divsn\"]").text().trim();
				board.setDivision(division);
				String title = ele.select("td[class=\"subject\"]").text().trim();
				board.setTitle(title);
				String writer = ele.select("td[class=\"writer text_over\"]").text().trim();
				board.setWriter(writer);
				int hits = Integer.valueOf(ele.select("td[class=\"hit\"]").text().trim());
				board.setHits(hits);
				int recommend = Integer.valueOf(ele.select("td[class=\"recomd\"]").text().trim());
				board.setRecommend(recommend);
				String time = ele.select("td[class=\"time\"]").text().trim();
				board.setTime(time);
				String url = ele.select("a[class=\"deco\"]").attr("href").trim();
				board.setUrl(url);
				String body = scrapingBody(url);
				board.setBody(body);
				listOfPost.add(board);
//				dataOfPost.put("id", ele.select("td[class=\"id\"]").text());
//				dataOfPost.put("divsn", ele.select("td[class=\"divsn\"]").text());
//				dataOfPost.put("title", ele.select("td[class=\"subject\"]").text());
//				dataOfPost.put("body", scrapingBody(ele.select("a[class=\"deco\"]").attr("href")));
//				dataOfPost.put("writer", ele.select("td[class=\"writer text_over\"]").text());
//				dataOfPost.put("hit", ele.select("td[class=\"hit\"]").text());
//				dataOfPost.put("recomd", ele.select("td[class=\"recomd\"]").text());
//				dataOfPost.put("time", ele.select("td[class=\"time\"]").text());
//				dataOfPost.put("url", ele.select("a[class=\"deco\"]").attr("href"));
//				listOfPost.add(dataOfPost);
				
			}

			for (int i = 0; i < listOfPost.size(); i++) {
				System.out.println(listOfPost.get(i).toString());
//				System.out.println(listOfPost.get(i).get("id"));
//				System.out.println(listOfPost.get(i).get("divsn"));
//				System.out.println(listOfPost.get(i).get("title"));
//				System.out.println(listOfPost.get(i).get("body"));
//				System.out.println(listOfPost.get(i).get("writer"));
//				System.out.println(listOfPost.get(i).get("hit"));
//				System.out.println(listOfPost.get(i).get("recomd"));
//				System.out.println(listOfPost.get(i).get("time"));
//				System.out.println(listOfPost.get(i).get("url"));


				System.out.println("-----------------");

			}
			CommityDAO cDAO = new CommityDAO();
			cDAO.insertBoard(listOfPost);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
