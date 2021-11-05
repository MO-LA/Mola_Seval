package com.mo.service.pick;

import com.mo.domain.entity.Pick;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import com.mo.domain.repository.PickRepo;
import com.mo.domain.repository.SchoolRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class PickServiceImpl implements PickService {

    private final PickRepo pickRepo;
    private final SchoolRepo schoolRepo;

    @Override
    @Transactional
    public void patchPick(Long schoolIdx, User user) {
        School school = schoolRepo.findById(schoolIdx).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 학교입니다.")
        );
        Pick pick = pickRepo.findBySchoolAndUser(school, user).orElse(
                new Pick(false, school, user)
        );

        pick.setState(!pick.getState());
        pickRepo.save(pick);
        user.getPicks().add(pick);
        school.getPicks().add(pick);
    }
}
