package xyz.fiwka.cornservice.test;

import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import xyz.fiwka.cornservice.CornServiceApplication;
import xyz.fiwka.cornservice.entity.Team;
import xyz.fiwka.cornservice.repository.TeamRepository;
import xyz.fiwka.cornservice.service.RoastingService;
import xyz.fiwka.cornservice.stub.RoastingRequest;
import xyz.fiwka.cornservice.stub.RoastingResponse;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = CornServiceApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
public class RoastingServiceTest {

    private static final UUID MY_TEAM_ID = UUID.randomUUID();

    @Autowired
    private RoastingService roastingService;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    public void setup() {
        teamRepository.save(new Team(MY_TEAM_ID));
    }

    @Test
    public void sendRoastingDataTest() throws Exception {
        StreamRecorder<RoastingResponse> observer = StreamRecorder.create();
        roastingService.sendRoastingData(
                RoastingRequest.newBuilder()
                        .setTeamId(MY_TEAM_ID.toString())
                        .setCornCountry("Russia")
                        .setCornType("test")
                        .setCornWeight(100)
                        .setCornOutputWeight(50)
                        .build(),
                observer
        );

        if (!observer.awaitCompletion(5, TimeUnit.SECONDS)) {
            Assertions.fail("Too long");
        }

        var results = observer.getValues();

        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("ok", results.get(0).getMessage());
    }

    @Test
    public void lossPercentCalculatingTest() {
        var percent1 = roastingService.getLossPercentByTeamId(MY_TEAM_ID);
        var percent2 = roastingService.getLossPercentByTeamIdAndCountry(MY_TEAM_ID, "Russia");

        Assertions.assertEquals(50.0, percent1);
        Assertions.assertEquals(50.0, percent2);
    }

}
