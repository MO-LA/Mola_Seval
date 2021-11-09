package com.mo.controller;

import com.mo.domain.dto.school.res.SchoolInfoRes;
import com.mo.domain.dto.school.res.SchoolListRes;
import com.mo.domain.dto.school.res.SearchMiddleSchoolRes;
import com.mo.domain.entity.School;
import com.mo.domain.response.ResponseData;
import com.mo.enums.school.Fond;
import com.mo.enums.school.FondType;
import com.mo.enums.school.SchoolKind;
import com.mo.service.school.SchoolService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "학교유형 학교리스트")
    @GetMapping("/list/kind")
    public ResponseData<List<SchoolListRes>> getSchoolListForSchoolKind(
            @RequestParam SchoolKind schoolKind,
            Pageable pageable) {
        List<SchoolListRes> data = schoolService.getSchoolListForSchoolKind(schoolKind, pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

    @ApiOperation(value = "설립유형으로 학교 리스트")
    @GetMapping("/list/fondType")
    public ResponseData<List<SchoolListRes>> getSchoolListForFondType(
            @RequestParam FondType fondType,
            Pageable pageable
    ) {
        List<SchoolListRes> data = schoolService.getSchoolListForFondType(fondType, pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

    @ApiOperation("설립구분으로 학교 리스트")
    @GetMapping("/list/fond")
    public ResponseData<List<SchoolListRes>> getSchoolListForFond(
            @RequestParam Fond fond,
            Pageable pageable
    ) {
        List<SchoolListRes> data = schoolService.getSchoolListForFond(fond, pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

    @ApiOperation("학교 이름 검색")
    @GetMapping("/list/name")
    public ResponseData<List<SchoolListRes>> searchSchoolListByName(
            @RequestParam String q,
            Pageable pageable
    ) {
        List<SchoolListRes> data = schoolService.searchSchoolListByName(q, pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

    @ApiOperation("학교 지역 검색")
    @GetMapping("/list/ran")
    public ResponseData<List<SchoolListRes>> searchSchoolListByRoadAddressName(
            @RequestParam String q,
            Pageable pageable
    ) {
        List<SchoolListRes> data = schoolService.searchSchoolListByRoadNameAddress(q, pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

    @ApiOperation("학교 통합 검색")
    @GetMapping("/list/search")
    public ResponseData<List<SchoolListRes>> searchSchoolList (
            @RequestParam FondType fondType,
            @RequestParam Fond fond,
            @RequestParam SchoolKind schoolKind,
            @RequestParam @Nullable String q,
            Pageable pageable
    ) {
        List<SchoolListRes> data = schoolService.searchSchoolList(fondType, fond, schoolKind, q, pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

    @ApiOperation("학교 상세 정보")
    @GetMapping
    public ResponseData<SchoolInfoRes> schoolInfo(@RequestParam Long schoolIdx) {
        SchoolInfoRes data = schoolService.getSchoolInfo(schoolIdx);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

    @ApiOperation(value = "중학교 검색", notes = "q = 학교 이름")
    @GetMapping("/middle")
    public ResponseData<List<SearchMiddleSchoolRes>> searchMiddleSchool(@RequestParam String q) {
        List<SearchMiddleSchoolRes> data = schoolService.searchMiddleSchool(q);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

}
