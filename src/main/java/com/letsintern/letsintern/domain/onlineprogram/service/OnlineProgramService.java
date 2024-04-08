package com.letsintern.letsintern.domain.onlineprogram.service;

import com.letsintern.letsintern.domain.file.helper.S3Helper;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.helper.OnlineProgramHelper;
import com.letsintern.letsintern.domain.onlineprogram.mapper.OnlineProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class OnlineProgramService {

    private final OnlineProgramHelper onlineProgramHelper;
    private final OnlineProgramMapper onlineProgramMapper;
    private final S3Helper s3Helper;
    private static final String S3_ONLINE_PROGRAM_DIR = "online-program/thumbnail/";

    public void createOnlineProgram(OnlineProgramCreateDTO onlineProgramCreateDTO, MultipartFile file) {
        S3SavedFileVo s3SavedFileVo = s3Helper.saveFile(file, S3_ONLINE_PROGRAM_DIR);
        OnlineProgram newOnlineProgram = onlineProgramMapper.toEntity(onlineProgramCreateDTO, s3SavedFileVo.getS3Url());
        onlineProgramHelper.saveOnlineProgram(newOnlineProgram);
    }
}
