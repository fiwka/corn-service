package xyz.fiwka.cornservice.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import xyz.fiwka.cornservice.CornServiceApplication;
import xyz.fiwka.cornservice.data.CornAdmissionDto;
import xyz.fiwka.cornservice.service.CornAdmissionService;

@SpringBootTest(classes = CornServiceApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
class CornAdmissionServiceTest {

    @Autowired
    private CornAdmissionService cornAdmissionService;

    @Test
    void cornAdmissionServicePositiveWeightChangeTest() {
        cornAdmissionService.changeCornAdmission(
                CornAdmissionDto.builder()
                        .cornType("test")
                        .arabicaPercent(100)
                        .robustPercent(100)
                        .country("Russia")
                        .build(),
                60000
        );

        Assertions.assertEquals(
                60000,
                cornAdmissionService.listCornAdmissionsByCornTypeAndCountry("test", "Russia", PageRequest.of(0, 1))
                        .get(0)
                        .getWeight()
        );
    }

    @Test
    void cornAdmissionServiceNegativeWeightChangeTest() {
        cornAdmissionService.changeCornAdmission(
                CornAdmissionDto.builder()
                        .cornType("test")
                        .arabicaPercent(100)
                        .robustPercent(100)
                        .country("Russia")
                        .build(),
                -10000
        );

        Assertions.assertEquals(
                50000,
                cornAdmissionService.listCornAdmissionsByCornTypeAndCountry("test", "Russia", PageRequest.of(0, 1))
                        .get(0)
                        .getWeight()
        );
    }

}
