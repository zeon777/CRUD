package vaginD.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import vaginD.dao.CRUDdao;
import vaginD.model.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by zeon on 12.06.2017.
 */

@Controller
public class UserController {
    @Autowired
    private CRUDdao userDao;
    private static final int MAX_ROWS_PER_PAGE = 10;



    @RequestMapping("/users.html")                                              //main programm page (gen all users + paging + search)
    public ModelAndView listUsers(@RequestParam(required = false) Integer page,@RequestParam(required = false) String searchName) {


        ModelAndView modelAndView = new ModelAndView("WEB-INF/users.jsp");
if(searchName==null)
{searchName="";}


        List<UserEntity> users = userDao.getAllUsers(searchName);
        PagedListHolder<UserEntity> pagedListHolder = new PagedListHolder<>(users);
        pagedListHolder.setPageSize(MAX_ROWS_PER_PAGE);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

        modelAndView.addObject("searchName",searchName);


        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            page=1;
        }
        modelAndView.addObject("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addObject("users", pagedListHolder.getPageList());


        }

        return modelAndView;

    }


    @RequestMapping(value = "/addUser.html", method = RequestMethod.GET)   // Entry to new user edit form
    public String showCreateUser(Model model) {
        UserEntity user = new UserEntity();
        user.setIsAdmin(false);
        model.addAttribute("user", user);
        return "WEB-INF/addEditUser.jsp";
    }

    @RequestMapping(value = "/editUser.html", method = RequestMethod.GET)    // Entry to new user edit form
    public String showEditUser(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));

        return "WEB-INF/addEditUser.jsp";
    }

    @RequestMapping(value = "/deleteUser.html", method = RequestMethod.GET)     //delete user from DAO
    public ModelAndView deleteUser(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("user", userDao.deleteUser(id));
        return new ModelAndView("redirect:/users.html");
    }

    @RequestMapping(value = "/addUser.html", method = RequestMethod.POST)     //Add new user to DAO
    public ModelAndView postAdd(@RequestParam("name") String name, @RequestParam("age") Integer age,  Boolean isAdmin){
UserEntity user = new UserEntity();
        user.setIsAdmin( isAdmin!=null?true:false);
        user.setAge(age);
        user.setName(name);

userDao.saveUser(user);
        return new ModelAndView("redirect:/users.html");
    }

    @RequestMapping(value = "/editUser.html", method = RequestMethod.POST)    //Edit selected user in DAO
    public ModelAndView postEdit(@RequestParam("id") Integer[] id,@RequestParam("name") String name, @RequestParam("age") Integer age, Boolean isAdmin){


UserEntity user = userDao.getUserById(id[0]);
user.setIsAdmin( isAdmin!=null?true:false);
user.setAge(age);
user.setName(name);
userDao.saveUser(user);

        return new ModelAndView("redirect:/users.html");
    }




}
