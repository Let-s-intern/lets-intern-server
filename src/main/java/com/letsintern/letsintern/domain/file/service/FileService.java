package com.letsintern.letsintern.domain.file.service;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.file.helper.FileHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileHelper fileHelper;

    @Transactional
    public Long saveFile(String s3Url, ContentsTopic contentsTopic) {
        return fileHelper.saveFile(s3Url, contentsTopic);
    }
}
