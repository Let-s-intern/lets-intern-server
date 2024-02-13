package com.letsintern.letsintern.domain.file.dto.response;

import com.letsintern.letsintern.domain.file.domain.File;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class FileAdminListResponse {

    private List<File> fileList;
    private PageInfo pageInfo;

    @Builder
    private FileAdminListResponse(Page<File> fileList) {
        if(fileList.hasContent()) this.fileList = fileList.getContent();
        this.pageInfo = PageInfo.of(fileList);
    }

    public static FileAdminListResponse from(Page<File> fileList) {
        return FileAdminListResponse.builder()
                .fileList(fileList)
                .build();
    }
}
