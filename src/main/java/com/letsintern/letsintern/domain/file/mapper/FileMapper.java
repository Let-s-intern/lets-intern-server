package com.letsintern.letsintern.domain.file.mapper;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.file.domain.File;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public File toEntity(String s3Url, ContentsTopic contentsTopic) {
        return File.of(s3Url, contentsTopic);
    }
}
