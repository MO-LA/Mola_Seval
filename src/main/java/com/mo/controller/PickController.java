package com.mo.controller;

import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.domain.response.ResponseData;
import com.mo.service.pick.PickService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/pick")
@RestController
public class PickController {

    private final PickService pickService;

    @ApiOperation("찜 수정/저장")
    @PatchMapping
    public Response patchPick(@RequestParam Long schoolIdx,
                              HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        pickService.patchPick(schoolIdx, user);

        return new Response(HttpStatus.OK.value(), "성공");
    }

    @ApiOperation("찜 상태 확인")
    @GetMapping
    public ResponseData<Boolean> checkIsPicked(@RequestParam Long schoolIdx,
                                               HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        Boolean data = pickService.checkIsPicked(schoolIdx, user);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

}
