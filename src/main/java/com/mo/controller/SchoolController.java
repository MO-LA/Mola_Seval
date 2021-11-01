package com.mo.controller;

import com.mo.domain.dto.school.res.SchoolListRes;
import com.mo.domain.response.ResponseData;
import com.mo.service.school.SchoolService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/school")
@RestController
public class SchoolController {

    public final SchoolService schoolService;

    @ApiOperation(value = "학교 리스트 바다옥익", notes = "오타 어쩌라고")
    @GetMapping("/list")
    public ResponseData<List<SchoolListRes>> getSchoolList(Pageable pageable) {
        List<SchoolListRes> data = schoolService.getSchoolList(pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

}
