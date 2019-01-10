package com.cvt.thymeelastic.resources;

import com.cvt.thymeelastic.model.Users;
import com.cvt.thymeelastic.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MainController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("msg","Aman");
        return "hello";
    }

    @GetMapping("/users")
    public String user(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("users",usersRepository.findAll(new PageRequest(page,4, Sort.Direction.ASC,"id")));
        model.addAttribute("currentPage",page);
        return "user";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("msg","Aman");
        return "index";
    }

    @PostMapping("/users/save")
    public String save(Users users){
        System.out.println(users);
        usersRepository.save(users);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public Users findOne(Long id){
        System.out.println(usersRepository.findOne(id));
        return usersRepository.findOne(id);
    }

    @RequestMapping(value = "/users/delete/{id}" ,method = RequestMethod.GET)
    public String deleteUser(@PathVariable long id, RedirectAttributes redirectAttributes){
       usersRepository.delete(id);
            redirectAttributes.addFlashAttribute("delete","user was deleted !");
        return "redirect:/users";
    }


}
