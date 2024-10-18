package xyz.fiwka.cornservice.data;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TeamLossPercentDataDto(UUID teamId, double lossPercent) {
}
