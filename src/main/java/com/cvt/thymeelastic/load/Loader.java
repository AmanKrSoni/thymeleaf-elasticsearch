package com.cvt.thymeelastic.load;


import com.cvt.thymeelastic.model.Users;
import com.cvt.thymeelastic.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loader {
    ElasticsearchOperations operations;

    @Autowired
    public Loader(ElasticsearchOperations operations) {
        this.operations = operations;
    }


    @Autowired
    UsersRepository usersRepository;

    @PostConstruct
    @Transactional
    public void loadAll() {
        operations.putMapping(Users.class);
        System.out.println("Loading Data");
        usersRepository.save(getData());//loads data into elasticsearch

        System.out.println("Loading Completed");
    }

    private List<Users> getData() {
        List<Users> userdata = new ArrayList<>();
        userdata.add(new Users("Ajay", 1123L, "Accounting", 1200L));
        userdata.add(new Users("Ajay", 1123L, "Accounting", 1200L));
        userdata.add(new Users("Ajay", 123L, "Accounting", 1200L));
        userdata.add(new Users("Ajay", 111123L, "Accounting", 1200L));
        userdata.add(new Users("Ajay", 1231L, "Accounting", 1200L));
        userdata.add(new Users("Vijay", 1234L, "Finance", 2200L));
        userdata.add(new Users("Raja", 12345L, "Accounts", 3200L));
        userdata.add(new Users("Ram", 123456L, "Teaching", 4200L));
        userdata.add(new Users("Accountie", 212L, "Finance", 2200L));
        userdata.add(new Users("Accountie1", 213L, "Finance", 2200L));
        userdata.add(new Users("Accountie2", 214L, "Finance", 2200L));
        userdata.add(new Users("Accountie3", 215L, "Finance", 2200L));
        userdata.add(new Users("Accountie4", 216L, "Finance", 2200L));

        return userdata;

    }
}
