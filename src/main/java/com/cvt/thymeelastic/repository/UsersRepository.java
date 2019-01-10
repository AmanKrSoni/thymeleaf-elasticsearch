package com.cvt.thymeelastic.repository;


import com.cvt.thymeelastic.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends ElasticsearchRepository<Users, Long> {
    List<Users> findByName(String text);
    List<Users> findBySalary(Long salary);
    Page<Users> findPageByName(String name, PageRequest pageRequest);

}
