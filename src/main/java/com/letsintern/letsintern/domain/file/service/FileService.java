package com.letsintern.letsintern.domain.file.service;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.file.helper.FileHelper;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileHelper fileHelper;

    @Transactional
    public Long saveFile(S3SavedFileVo s3SavedFileVo, ContentsTopic contentsTopic) {
        return fileHelper.saveFile(s3SavedFileVo, contentsTopic);
    }
}
