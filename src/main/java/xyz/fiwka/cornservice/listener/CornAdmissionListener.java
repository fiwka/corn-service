package xyz.fiwka.cornservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xyz.fiwka.cornservice.data.CornAdmissionDto;
import xyz.fiwka.cornservice.service.CornAdmissionService;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class CornAdmissionListener {

    private static final long CORN_BAG_WEIGHT = 60000;

    private final CornAdmissionService cornAdmissionService;

    @KafkaListener(
            id = "onCornAdmission",
            topics = "corn.admission",
            containerFactory = "cornAdmissionMessageConcurrentKafkaListenerContainerFactory"
    )
    public void onCornAdmission(CornAdmissionDto message) {
        cornAdmissionService.changeCornAdmission(message, CORN_BAG_WEIGHT);
    }
}
