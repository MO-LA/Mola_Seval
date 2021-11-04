package com.mo.domain.repository.page;

import com.mo.domain.entity.School;
import com.mo.enums.school.Fond;
import com.mo.enums.school.FondType;
import com.mo.enums.school.SchoolKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SchoolPageRepo extends PagingAndSortingRepository<School, Long> {
    Page<School> findAllBySchoolKind(Pageable pageable, SchoolKind schoolKind);
    Page<School> findAllByFondType(Pageable pageable, FondType fondType);
    Page<School> findAllByFond(Pageable pageable, Fond fond);
    Page<School> findAllByNameContaining(Pageable pageable, String name);
    Page<School> findAllByRoadNameAddressContaining(Pageable pageable, String roadNameAddress);
}
