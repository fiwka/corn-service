package xyz.fiwka.cornservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.fiwka.cornservice.data.CornAdmissionDto;
import xyz.fiwka.cornservice.entity.CornAdmission;
import xyz.fiwka.cornservice.repository.CornAdmissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CornAdmissionService {

    private final CornAdmissionRepository cornAdmissionRepository;

    @Transactional
    public void changeCornAdmission(CornAdmissionDto cornAdmissionDto, long weight) {
        var cornAdmission = cornAdmissionRepository.findCornAdmissionByRobustPercentAndArabicaPercentAndCornTypeAndCountry(
                cornAdmissionDto.robustPercent(),
                cornAdmissionDto.arabicaPercent(),
                cornAdmissionDto.cornType(),
                cornAdmissionDto.country()
        ).orElse(new CornAdmission(
                cornAdmissionDto.robustPercent(),
                cornAdmissionDto.arabicaPercent(),
                cornAdmissionDto.cornType(),
                cornAdmissionDto.country()
        ));

        cornAdmission.setWeight(cornAdmission.getWeight() + weight);
        cornAdmissionRepository.save(cornAdmission);
    }

    @Transactional
    public void changeCornAdmissionCornTypeAndCountry(CornAdmissionDto cornAdmissionDto, long weight) {
        var cornAdmission = cornAdmissionRepository.findCornAdmissionByCornTypeAndCountry(
                cornAdmissionDto.cornType(),
                cornAdmissionDto.country()
        ).orElse(new CornAdmission(
                cornAdmissionDto.robustPercent(),
                cornAdmissionDto.arabicaPercent(),
                cornAdmissionDto.cornType(),
                cornAdmissionDto.country()
        ));

        cornAdmission.setWeight(cornAdmission.getWeight() + weight);
        cornAdmissionRepository.save(cornAdmission);
    }

    @Transactional(readOnly = true)
    public List<CornAdmission> listCornAdmissions(Pageable pageable) {
        return cornAdmissionRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<CornAdmission> listCornAdmissionsByCornTypeAndCountry(String cornType, String country, Pageable pageable) {
        return cornAdmissionRepository.findAllByCornTypeAndCountry(cornType, country, pageable);
    }

}
