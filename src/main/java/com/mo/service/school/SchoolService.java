package com.mo.service.school;

import com.mo.domain.dto.school.res.SchoolListRes;
import com.mo.enums.school.Fond;
import com.mo.enums.school.FondType;
import com.mo.enums.school.SchoolKind;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SchoolService {
    void storeSchool();

    void StoreGenderSum();

    @Transactional(readOnly = true)
    List<SchoolListRes> getSchoolList(Pageable pageable);

    @Transactional(readOnly = true)
    List<SchoolListRes> getSchoolListForSchoolKind(SchoolKind schoolKind, Pageable pageable);

    @Transactional(readOnly = true)
    List<SchoolListRes> getSchoolListForFondType(FondType fondType, Pageable pageable);

    @Transactional(readOnly = true)
    List<SchoolListRes> getSchoolListForFond(Fond fond, Pageable pageable);

    @Transactional(readOnly = true)
    List<SchoolListRes> searchSchoolListByName(String q, Pageable pageable);

    @Transactional(readOnly = true)
    List<SchoolListRes> searchSchoolListByRoadNameAddress(String q, Pageable pageable);
}
