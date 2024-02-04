package com.letsintern.letsintern.domain.contents.repository;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.vo.ContentsAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContentsRepositoryCustom {

    Page<ContentsAdminVo> getContentsAdminVoList(ContentsTopic contentsTopic, Pageable pageable);

    Optional<ContentsAdminVo> findContentsAdminVo(Long contentsId);
}
