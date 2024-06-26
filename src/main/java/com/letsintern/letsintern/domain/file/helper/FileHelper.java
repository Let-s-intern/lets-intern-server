package com.letsintern.letsintern.domain.file.helper;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.file.domain.File;
import com.letsintern.letsintern.domain.file.dto.response.FileAdminListResponse;
import com.letsintern.letsintern.domain.file.exception.FileNotFound;
import com.letsintern.letsintern.domain.file.mapper.FileMapper;
import com.letsintern.letsintern.domain.file.repository.FileRepository;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileHelper {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final S3Helper s3Helper;

    public Long saveFile(S3SavedFileVo s3SavedFileVo, ContentsTopic contentsTopic) {
        File file = fileMapper.toEntity(s3SavedFileVo, contentsTopic);
        return fileRepository.save(file).getId();
    }

    public FileAdminListResponse getFileAdminList(ContentsTopic contentsTopic, Pageable pageable) {
        if(contentsTopic == null)
            return FileAdminListResponse.from(fileRepository.findAllBy(pageable));
        else
            return FileAdminListResponse.from(fileRepository.findAllByContentsTopic(contentsTopic, pageable));
    }

    public ResponseEntity<UrlResource> downloadFile(Long fileId) {
        final File file = fileRepository.findById(fileId).orElseThrow(() -> FileNotFound.EXCEPTION);
        return s3Helper.downloadFile(file.getOriginalFilename());
    }

    public void deleteFile(Long fileId) {
        File file = fileRepository.findById(fileId).orElseThrow(() -> FileNotFound.EXCEPTION);
        s3Helper.deleteFile(file.getOriginalFilename());
        fileRepository.delete(file);
    }
}
