package ma.xproce.bshop.web;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Writer;
import ma.xproce.bshop.dao.repositories.BookRepository;
import ma.xproce.bshop.service.BookManager;
import ma.xproce.bshop.service.WriterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class WriterController {
    @Autowired
    private BookManager bookManager;


    @Autowired
    private WriterManager writerManager;


    @GetMapping("/WriterList")
    public String getallWriters(Model model){
        List<Writer> writers= writerManager.getallWriters();
        model.addAttribute("writers",writers);
        return "WriterList";
    }


    @GetMapping("/addWriter")
    public String addWriter(Model model){
        return "addWriter";
    }

    @PostMapping("addWriter")
    public String addWriterPost(Model model , @RequestParam(name="name") String name  )
    {
        Writer writer = new Writer();
        writer.setName(name);

        System.out.println(writer);
        writerManager.addWriter(writer);
        return "redirect:/WriterList";
    }



}
