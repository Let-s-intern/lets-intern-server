package com.letsintern.letsintern.domain.file.service;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.file.helper.FileHelper;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
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

    @Transactional(readOnly = true)
    public ResponseEntity<UrlResource> downloadFile(Long fileId) {
        return fileHelper.downloadFile(fileId);
    }
}
