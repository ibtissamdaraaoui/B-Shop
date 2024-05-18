package ma.xproce.bshop.web;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Writer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class AboutController {
    @GetMapping("/About")
    public String About(Model model) {

        return "About";
    }
}
