package com.mo;

import com.mo.service.school.SchoolServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoApplicationTests {
    @Autowired
    private SchoolServiceImpl schoolService;

    @Test
    void getSchool() {
        schoolService.storeSchool();
    }

    @Test
    void setGenderSum() {
        schoolService.StoreGenderSum();
    }

    @Test
    void setAddress() {
        schoolService.storeAddress();
    }

    @Test
    void setMiddleSchool() {
        schoolService.storeMiddleSchool();
    }

}
