package pl.lolapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(name = "/test")
    public String getHomepage()
    {
        return "home";
    }
}
