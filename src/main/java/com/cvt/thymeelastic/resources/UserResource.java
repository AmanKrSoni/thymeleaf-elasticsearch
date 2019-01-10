package com.cvt.thymeelastic.resources;


import com.cvt.thymeelastic.builder.SearchQueryBuilder;
import com.cvt.thymeelastic.model.Users;
import com.cvt.thymeelastic.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dusers")
public class UserResource {
    static int a=0;
    static int page=0;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    //@GetMapping(value = "/name/{text}")
    //public List<Users> searchName(@PathVariable final String text) {
      //  return usersRepository.findByName(text);
    //}

    @GetMapping(value = "/salary/{salary}")
    public List<Users> searchName(@PathVariable final Long salary) {
        return usersRepository.findBySalary(salary);
    }

    @GetMapping(value = "/all")
    public List<Users> searchAll() {
        List<Users> userlist = new ArrayList<>();
        Iterable<Users> user = usersRepository.findAll();
        user.forEach(users -> {
            userlist.add(users);
        });

        return userlist;
    }

    @GetMapping("/name/{name}")
    public List<Users> findPageByName(@PathVariable String name){

        Page<Users> u=usersRepository.findPageByName(name, new PageRequest(a,3, Sort.Direction.ASC ,"name"));
        if(a>=u.getTotalPages())
            a=0;
        else
        a+=1;

        List<Users> us=u.getContent();
        System.out.println(u);
        return  us;

    }

    @GetMapping("/alluser")
    public List<Users> findAllByName(){

        Page<Users> pu=usersRepository.findAll(new PageRequest(page,4));
        page++;
        List<Users> u=pu.getContent();
        return u;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        usersRepository.delete(id);
        return "Deleted";
    }




}
