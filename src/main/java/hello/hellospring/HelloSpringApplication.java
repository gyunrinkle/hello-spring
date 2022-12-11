package hello.hellospring;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HelloSpringApplication {
	public static void getCakeList() throws IOException {

		final String cakeCrawlingURL = "https://www.idus.com/search?word=%ED%81%AC%EB%A6%AC%EC%8A%A4%EB%A7%88%EC%8A%A4%20%EC%BC%80%EC%9D%B4%ED%81%AC&keyword_channel=hot";
		Connection.Response response = Jsoup.connect(cakeCrawlingURL).method(Connection.Method.GET).execute();

		try {
			Document document = response.parse();
			String html = document.html();
			Document doc = Jsoup.parse(html, "", Parser.xmlParser());

			Elements cake_items_photo_url = document.select(".product-thumb-img");
			Elements cake_items_name = document.select(".product-info__name");
			Elements cake_items_price = document.select(".price-sale");
			StringBuilder sb = new StringBuilder();
			for (Element item : cake_items_photo_url) {
				System.out.println(item.attr("style").split("url")[1].toString());
			}
			for (Element item : cake_items_name) {
				System.out.println(item.text().toString());
			}
			for (Element item : cake_items_price) {
				System.out.println(item.text().toString());
			}

		} catch (IOException ignored) {
			System.out.println("Failed");
		}
	}

	public static void main(String[] args) throws IOException {
		getCakeList();
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
