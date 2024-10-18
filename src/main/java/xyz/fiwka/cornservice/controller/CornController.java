package xyz.fiwka.cornservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fiwka.cornservice.entity.CornAdmission;
import xyz.fiwka.cornservice.service.CornAdmissionService;

import java.util.List;

@RestController
@RequestMapping("/corn")
@RequiredArgsConstructor
public class CornController {

    private final CornAdmissionService cornAdmissionService;

    @GetMapping("/remains")
    @Operation(summary = "Возвращает список остатков всех сортов")
    public List<CornAdmission> remains(@ParameterObject Pageable pageable) {
        return cornAdmissionService.listCornAdmissions(pageable);
    }

    @GetMapping("/remains/{cornType}/{country}")
    @Operation(summary = "Возвращает список остатков конкретного сорта конкретной страны происхождения")
    public List<CornAdmission> remains(@PathVariable String cornType, @PathVariable String country, @ParameterObject Pageable pageable) {
        return cornAdmissionService.listCornAdmissionsByCornTypeAndCountry(cornType, country, pageable);
    }
}
