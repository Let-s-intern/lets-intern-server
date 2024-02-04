package com.letsintern.letsintern.domain.file.mapper;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.file.domain.File;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public File toEntity(S3SavedFileVo s3SavedFileVo, ContentsTopic contentsTopic) {
        return File.of(s3SavedFileVo.getOriginalFileName(), s3SavedFileVo.getS3Url(), contentsTopic);
    }
}
