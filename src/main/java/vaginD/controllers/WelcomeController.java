package vaginD.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeon on 10.06.2017.
 */
@Controller
@SessionAttributes("person")
public class WelcomeController {


    private Map<String,Integer> visitorCount = new HashMap<>();

    @RequestMapping("/index.html")
    public String index(Model model,HttpSession httpSession) {

      if(!visitorCount.containsKey(httpSession.getId()))
      {
          visitorCount.put( (httpSession.getId().toString()),visitorCount.size()+1);
          model.addAttribute("visitorCount",visitorCount.size());
      }
      else
      {model.addAttribute("visitorCount",visitorCount.get(httpSession.getId().toString()));

      }
        return "/WEB-INF/index.jsp";
    }

}