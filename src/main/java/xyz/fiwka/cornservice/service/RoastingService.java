package xyz.fiwka.cornservice.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.transaction.annotation.Transactional;
import xyz.fiwka.cornservice.data.CornAdmissionDto;
import xyz.fiwka.cornservice.entity.RoastingRecord;
import xyz.fiwka.cornservice.repository.RoastingRecordRepository;
import xyz.fiwka.cornservice.repository.TeamRepository;
import xyz.fiwka.cornservice.stub.RoastingRequest;
import xyz.fiwka.cornservice.stub.RoastingResponse;
import xyz.fiwka.cornservice.stub.RoastingServiceGrpc;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class RoastingService extends RoastingServiceGrpc.RoastingServiceImplBase {

    private final CornAdmissionService cornAdmissionService;
    private final RoastingRecordRepository roastingRecordRepository;
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public void sendRoastingData(RoastingRequest request, StreamObserver<RoastingResponse> responseObserver) {
        var cornAdmissionDto = CornAdmissionDto.builder()
                .country(request.getCornCountry())
                .cornType(request.getCornType())
                .build();
        cornAdmissionService.changeCornAdmissionCornTypeAndCountry(cornAdmissionDto, -request.getCornWeight());
        var team = teamRepository.getTeamByUuid(UUID.fromString(request.getTeamId()));
        roastingRecordRepository.save(
                new RoastingRecord(
                        team,
                        request.getCornCountry(),
                        request.getCornType(),
                        request.getCornWeight(),
                        request.getCornOutputWeight()
                )
        );
        responseObserver.onNext(RoastingResponse.newBuilder()
                .setMessage("ok")
                .build()
        );
        responseObserver.onCompleted();
    }

    @Transactional(readOnly = true)
    public double getLossPercentByTeamId(UUID teamId) {
        return roastingRecordRepository.getLossPercentByTeamId(teamId);
    }

    @Transactional(readOnly = true)
    public double getLossPercentByTeamIdAndCountry(UUID teamId, String country) {
        return roastingRecordRepository.getLossPercentByTeamIdAndCountry(teamId, country);
    }
}
