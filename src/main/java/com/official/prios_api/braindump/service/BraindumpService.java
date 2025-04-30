package com.official.prios_api.braindump.service;

import com.official.prios_api.braindump.domain.Braindump;
import com.official.prios_api.braindump.dto.BraindumpRequest;
import com.official.prios_api.braindump.repository.BraindumpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BraindumpService {

    private final BraindumpRepository braindumpRepository;

    public void save(BraindumpRequest request) {
        Braindump entity = Braindump.builder()
                .content(request.content())
                .build();
        braindumpRepository.save(entity);
    }

    public List<Braindump> getToday() {
        LocalDate today = LocalDate.now();
        return braindumpRepository.findAllByCreatedAtBetween(
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );
    }
}

