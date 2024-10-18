package xyz.fiwka.cornservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fiwka.cornservice.data.TeamLossPercentDataDto;
import xyz.fiwka.cornservice.service.RoastingService;

import java.util.UUID;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final RoastingService roastingService;

    @GetMapping("/{teamId}")
    @Operation(summary = "Возвращает процент потерь бригады")
    public TeamLossPercentDataDto teamLossPercentData(@PathVariable UUID teamId) {
        return TeamLossPercentDataDto.builder()
                .teamId(teamId)
                .lossPercent(roastingService.getLossPercentByTeamId(teamId))
                .build();
    }

    @GetMapping("/{teamId}/{country}")
    @Operation(summary = "Возвращает процент потерь бригады для определённой страны происхождения")
    public TeamLossPercentDataDto teamLossPercentDataByCountry(@PathVariable UUID teamId, @PathVariable String country) {
        return TeamLossPercentDataDto.builder()
                .teamId(teamId)
                .lossPercent(roastingService.getLossPercentByTeamIdAndCountry(teamId, country))
                .build();
    }
}
