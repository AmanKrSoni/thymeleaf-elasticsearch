package com.cvt.thymeelastic.resources;


import com.cvt.thymeelastic.builder.SearchQueryBuilder;
import com.cvt.thymeelastic.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/msearch")
public class ManualSearch {

    @Autowired
    private SearchQueryBuilder searchQueryBuilder;

    @GetMapping(value = "/{text}")
    public List<Users> getAll(@PathVariable String text) {
        return searchQueryBuilder.getAll(text);

    }
}
