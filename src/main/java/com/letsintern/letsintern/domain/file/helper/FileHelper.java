package com.letsintern.letsintern.domain.file.helper;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.file.domain.File;
import com.letsintern.letsintern.domain.file.mapper.FileMapper;
import com.letsintern.letsintern.domain.file.repository.FileRepository;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileHelper {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public Long saveFile(S3SavedFileVo s3SavedFileVo, ContentsTopic contentsTopic) {
        File file = fileMapper.toEntity(s3SavedFileVo, contentsTopic);
        return fileRepository.save(file).getId();
    }
}
