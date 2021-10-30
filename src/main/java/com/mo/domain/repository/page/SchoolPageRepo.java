package com.mo.domain.repository.page;

import com.mo.domain.entity.School;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SchoolPageRepo extends PagingAndSortingRepository<School, Long> {
}
