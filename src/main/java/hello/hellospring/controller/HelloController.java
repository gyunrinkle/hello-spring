package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class HelloController {
    @GetMapping("/cake_lists")
    public String hello(Model model) {
        String json = "{img_url: 'https://image.idus.com/image/files/efae594b68294be2b6b03849ec6aaef5_512.jpg', name: '\uD83C\uDF1F예약발송\uD83C\uDF1F☃️크리스마스 슈톨렌☃️', price: 10000}";
        return json;
    }
}
