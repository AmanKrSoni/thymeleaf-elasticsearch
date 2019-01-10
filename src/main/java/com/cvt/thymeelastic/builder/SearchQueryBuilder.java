package com.cvt.thymeelastic.builder;


import com.cvt.thymeelastic.model.Users;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchQueryBuilder {



    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public List<Users> getAll(String text) {

        QueryBuilder query = QueryBuilders.boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(text)
                                .lenient(true)
                                .field("name")
                                .field("teamName")
                ).should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true)
                        .field("name")
                        .field("teamName"));
        NativeSearchQuery build =
                new NativeSearchQueryBuilder()
                        .withQuery(query)
                        .build();

        List<Users> userList = elasticsearchTemplate.queryForList(build, Users.class);

        return userList;
    }



}
