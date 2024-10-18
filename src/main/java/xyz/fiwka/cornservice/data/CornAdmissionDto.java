package xyz.fiwka.cornservice.data;

import lombok.Builder;

@Builder
public record CornAdmissionDto(String country, int robustPercent, int arabicaPercent, String cornType) {
}
