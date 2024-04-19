package com.letsintern.letsintern.domain.file.helper;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Helper {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public S3SavedFileVo saveFile(MultipartFile multipartFile, String dir) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            if(dir != null) originalFilename = dir + originalFilename;

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
            return S3SavedFileVo.of(originalFilename, amazonS3.getUrl(bucket, originalFilename).toString());
        } catch (SdkClientException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<UrlResource> downloadFile(String originalFilename) {
        UrlResource urlResource = new UrlResource(amazonS3.getUrl(bucket, originalFilename));
        String contentDisposition = "attachment; filename=\"" +  originalFilename + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

    public void deleteFile(String originalFilename) {
        amazonS3.deleteObject(bucket, originalFilename);
    }

    public S3SavedFileVo changeImgFile(String dir, String deletedFileUrl, MultipartFile newFile) {
        if (newFile == null) return null;
        deleteFile(dir + deletedFileUrl.split("/")[5]);
        return saveFile(newFile, dir);
    }
}
