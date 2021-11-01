package com.mo.service.school;

import com.mo.domain.dto.school.res.SchoolListRes;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SchoolService {
    void storeSchool();

    void StoreGenderSum();

    @Transactional(readOnly = true)
    List<SchoolListRes> getSchoolList(Pageable pageable);
}
