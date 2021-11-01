package com.mo.service.school;

import com.mo.domain.dto.school.res.SchoolListRes;
import com.mo.domain.entity.School;
import com.mo.domain.repository.SchoolRepo;
import com.mo.domain.repository.page.SchoolPageRepo;
import com.mo.enums.school.Fond;
import com.mo.enums.school.FondType;
import com.mo.enums.school.GenderCheck;
import com.mo.enums.school.SchoolKind;
import lombok.RequiredArgsConstructor;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepo schoolRepo;
    private final SchoolPageRepo schoolPageRepo;

    private final JSONParser jsonParser = new JSONParser();

    // 27f55f5a5873439fbd8e09aff467bbcc
    @Override
    public void storeSchool() {
        try {
            String basicSchoolInfoUrl = "https://www.schoolinfo.go.kr/openApi.do?apiKey=27f55f5a5873439fbd8e09aff467bbcc&apiType=0&pbanYr=2021&schulKndCode=04";
            RestTemplate restTemplate = restTemplate();

            String basicSchoolInfoRes = restTemplate.getForObject(basicSchoolInfoUrl, String.class);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(basicSchoolInfoRes);

            JSONArray list = (JSONArray) jsonObject.get("list");
            for (Object o : list) {
                JSONObject object = (JSONObject) o;

                String schoolName = (String) object.get("SCHUL_NM");
                // 행정실 전화번호
                String aOfficePhoneNumber = (String) object.get("USER_TELNO_GA");
                // 교무실 전화번호
                String tOfficePhoneNumber = (String) object.get("USER_TELNO_SW");
                String homePage = (String) object.get("HMPG_ADRES");
                String sGenderCheck = (String) object.get("COEDU_SC_CODE");
                GenderCheck genderCheck = stringToGenderCheck(sGenderCheck);
                String roadNameAddress = (String) object.get("SCHUL_RDNMA");
                String sSchoolKind = (String) object.get("HS_KND_SC_NM");
                SchoolKind schoolKind = stringToSchoolKind(sSchoolKind);
                String sFondType = (String) object.get("SCHUL_FOND_TYP_CODE");
                FondType fondType = stringToFondType(sFondType);
                String sFond = (String) object.get("FOND_SC_CODE");
                Fond fond = stringToFond(sFond);
                String schoolCode = (String) object.get("SCHUL_CODE");

                School school = School.builder()
                        .name(schoolName)
                        .aOfficePhoneNumber(aOfficePhoneNumber)
                        .tOfficePhoneNumber(tOfficePhoneNumber)
                        .homePage(homePage)
                        .genderCheck(genderCheck)
                        .roadNameAddress(roadNameAddress)
                        .schoolKind(schoolKind)
                        .fondType(fondType)
                        .fond(fond)
                        .schoolCode(schoolCode)
                        .build();
                schoolRepo.save(school);
            }

        } catch (ParseException | KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void StoreGenderSum() {
        try {
            String basicSchoolInfoUrl = "https://www.schoolinfo.go.kr/openApi.do?apiKey=27f55f5a5873439fbd8e09aff467bbcc&apiType=63&pbanYr=2021&schulKndCode=04";
            RestTemplate restTemplate = restTemplate();

            String basicSchoolInfoRes = restTemplate.getForObject(basicSchoolInfoUrl, String.class);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(basicSchoolInfoRes);

            JSONArray list = (JSONArray) jsonObject.get("list");
            for (Object o : list) {
                JSONObject object = (JSONObject) o;

                Long lMaleSum = (Long) object.get("COL_MSUM");
                if (lMaleSum == null) {
                    continue;
                }
                int maleSum = lMaleSum.intValue();
                Long lFemaleSum = (Long) object.get("COL_WSUM");
                if (lFemaleSum == null) {
                    continue;
                }
                int femaleSum = lFemaleSum.intValue();
                String schoolCode = (String) object.get("SCHUL_CODE");

                School school = schoolRepo.findBySchoolCode(schoolCode).orElse(
                        new School()
                );

                school.setMaleSum(maleSum);
                school.setFemaleSum(femaleSum);
            }
        } catch (ParseException | KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SchoolListRes> getSchoolList(Pageable pageable) {
        List<SchoolListRes> result = new ArrayList<>();

        Page<School> schoolPage = schoolPageRepo.findAll(pageable);
        List<School> schoolList = schoolPage.toList();

        for (School school : schoolList) {
            SchoolListRes res = new SchoolListRes(school);

            result.add(res);
        }

        return result;
    }

    private GenderCheck stringToGenderCheck(String s) {
        if (s.equals("남")) {
            return GenderCheck.M;
        } else if (s.equals("녀")) {
            return GenderCheck.W;
        } else {
            return GenderCheck.MW;
        }
    }

    private SchoolKind stringToSchoolKind(String s) {
        switch (s) {
            case "특수목적고등학교":
                return SchoolKind.SPECIAL_PURPOSE;
            case "자율고등학교":
                return SchoolKind.AUTONOMOUS;
            case "특성화고등학교":
                return SchoolKind.SPECIALIZED;
            case "일반고등학교":
            default:
                return SchoolKind.GENERAL;
        }
    }

    private FondType stringToFondType(String s) {
        if (s.equals("단설"))
            return FondType.INDEPENDENCE;
        else if (s.equals("부속"))
            return FondType.ACCESSORIES;
        else return FondType.ESTABLISH;
    }

    private Fond stringToFond(String s) {
        if (s.equals("사립")) {
            return Fond.PRIVATE;
        } else if (s.equals("국립")) {
            return Fond.NATIONAL;
        } else return Fond.PUBLIC;
    }

    private RestTemplate restTemplate()
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }
}
