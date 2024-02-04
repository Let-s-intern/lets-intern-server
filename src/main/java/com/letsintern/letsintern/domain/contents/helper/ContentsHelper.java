package com.letsintern.letsintern.domain.contents.helper;

import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import com.letsintern.letsintern.domain.contents.mapper.ContentsMapper;
import com.letsintern.letsintern.domain.contents.repository.ContentsRepository;
import com.letsintern.letsintern.domain.file.helper.S3Helper;
import com.letsintern.letsintern.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentsHelper {

    private final ContentsRepository contentsRepository;
    private final ContentsMapper contentsMapper;
    private final S3Helper s3Helper;
    private final FileService fileService;

    public Long createContents(ContentsCreateDTO contentsCreateDTO, List<MultipartFile> files) throws IOException {
        List<Long> fileIdList = new ArrayList<>();
        for(MultipartFile multipartFile : files) {
            fileIdList.add(fileService.saveFile(s3Helper.saveFile(multipartFile), contentsCreateDTO.getTopic()));
        }

        if(fileIdList.isEmpty()) {
            return contentsRepository.save(contentsMapper.toEntity(contentsCreateDTO, null)).getId();
        }

        return contentsRepository.save(contentsMapper.toEntity(contentsCreateDTO, fileIdList)).getId();
    }
}
