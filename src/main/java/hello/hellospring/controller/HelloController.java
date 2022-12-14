package hello.hellospring.controller;

import hello.hellospring.model.DataResponse;
import hello.hellospring.model.ProductInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*") // 모든 요청에 접근 허용
@RestController
public class HelloController {
    @GetMapping("/cake_lists")
    public DataResponse getData() {
        final DataResponse response = new DataResponse();
        List<ProductInfo> resList = new ArrayList<ProductInfo>();
        final String URL = "https://www.idus.com/search?word=%ED%81%AC%EB%A6%AC%EC%8A%A4%EB%A7%88%EC%8A%A4%20%EC%BC%80%EC%9D%B4%ED%81%AC&keyword_channel=hot";
        Connection connection = Jsoup.connect(URL);
        Document document = null;

        try {
            document = connection.get();
            Elements cards = document.select(".base-card.card--vertical.product");
            for (Element card : cards) {
                ProductInfo tmp = new ProductInfo();
                Element cardDetailElement = card.child(0);
                String imgURL = cardDetailElement.child(0).child(0).attr("style").split("url")[1].toString().replaceAll("[\\(\\)']","");
                System.out.println(imgURL);
                Element cardInfoElement = cardDetailElement.child(1);
                String companyName = cardInfoElement.child(0).text().toString();
                System.out.println(companyName);
                String productName = cardInfoElement.child(1).text().toString();
                System.out.println(productName);
                Element priceInfoElement = cardInfoElement.child(2);

                if (priceInfoElement.childrenSize() == 1) {
                    String saleRate = "none";
                    System.out.println(saleRate);
                    String priceSale = "none";
                    System.out.println(priceSale);
                    String priceOrigin = priceInfoElement.child(0).text().toString();
                    System.out.println(priceOrigin);
                    tmp.imgURL = imgURL;
                    tmp.compnayName = companyName;
                    tmp.productName = productName;
                    tmp.saleRate = saleRate;
                    tmp.priceSale = priceSale;
                    tmp.priceOrigin = priceOrigin;
                }
                else {
                    String saleRate = priceInfoElement.child(0).text().toString();
                    System.out.println(saleRate);
                    String priceSale = priceInfoElement.child(1).text().toString();
                    System.out.println(priceSale);
                    String priceOrigin = priceInfoElement.child(2).text().toString();
                    System.out.println(priceOrigin);
                    tmp.imgURL = imgURL;
                    tmp.compnayName = companyName;
                    tmp.productName = productName;
                    tmp.saleRate = saleRate;
                    tmp.priceSale = priceSale;
                    tmp.priceOrigin = priceOrigin;
                }

                resList.add(tmp);
            }
            response.data = resList;
            System.out.println(response.data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
