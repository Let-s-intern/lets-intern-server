package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.Bootcamp;
import com.letsintern.letsintern.domain.program.repository.BootcampRepository;
import com.letsintern.letsintern.domain.program.vo.bootcamp.BootcampDetailVo;
import com.letsintern.letsintern.domain.program.dto.request.BootcampRequestDto;
import com.letsintern.letsintern.domain.program.exception.BootcampNotFound;
import com.letsintern.letsintern.domain.program.vo.program.ProgramThumbnailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BootcampHelper {
    private final BootcampRepository bootcampRepository;

    public void validateBootcampTypeProgramInput(BootcampRequestDto requestDto) {
    }

    public BootcampDetailVo findBootcampDetailVoOrThrow(Long bootcampId) {
        return bootcampRepository.findBootcampDetailVo(bootcampId)
                .orElseThrow(() -> BootcampNotFound.EXCEPTION);
    }

    public Bootcamp findBootcampOrThrow(Long bootcampId) {
        return bootcampRepository.findById(bootcampId)
                .orElseThrow(() -> BootcampNotFound.EXCEPTION);
    }

    public Page<ProgramThumbnailVo> findProgramList(Pageable pageable) {
        return bootcampRepository.findProgramThumbnailsByType(pageable);
    }

    public Bootcamp saveBootcamp(Bootcamp bootcamp) {
        return bootcampRepository.save(bootcamp);
    }

    public void deleteBootcamp(Long bootcampId) {
        bootcampRepository.deleteById(bootcampId);
    }
}
