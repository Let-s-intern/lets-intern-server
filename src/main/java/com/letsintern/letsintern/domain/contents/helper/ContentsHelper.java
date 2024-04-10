package com.letsintern.letsintern.domain.contents.helper;

import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import com.letsintern.letsintern.domain.contents.dto.request.ContentsUpdateDTO;
import com.letsintern.letsintern.domain.contents.exception.AdditionalContentsNotFound;
import com.letsintern.letsintern.domain.contents.exception.ContentsNotFound;
import com.letsintern.letsintern.domain.contents.exception.EssentialContentsNotFound;
import com.letsintern.letsintern.domain.contents.mapper.ContentsMapper;
import com.letsintern.letsintern.domain.contents.repository.ContentsRepository;
import com.letsintern.letsintern.domain.contents.vo.ContentsAdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentsHelper {

    private final ContentsRepository contentsRepository;
    private final ContentsMapper contentsMapper;

    public Long createContents(ContentsCreateDTO contentsCreateDTO) {
        return contentsRepository.save(contentsMapper.toEntity(contentsCreateDTO)).getId();
    }

    public Page<ContentsAdminVo> getContentsAdminList(ContentsTopic contentsTopic, Pageable pageable) {
        return contentsRepository.getContentsAdminVoList(contentsTopic, pageable);
    }

    public ContentsAdminVo getContentsDetail(Long contentsId) {
        return contentsRepository.findContentsAdminVo(contentsId).orElseThrow(() -> ContentsNotFound.EXCEPTION);
    }

    public Long updateContents(Long contentsId, ContentsUpdateDTO contentsUpdateDTO) {
        Contents contents = contentsRepository.findById(contentsId).orElseThrow(() -> ContentsNotFound.EXCEPTION);
        if(contentsUpdateDTO.getTitle() != null)
            contents.setTitle(contentsUpdateDTO.getTitle());
        if(contentsUpdateDTO.getType() != null)
            contents.setType(contentsUpdateDTO.getType());
        if(contentsUpdateDTO.getTopic() != null)
            contents.setTopic(contentsUpdateDTO.getTopic());
        if(contentsUpdateDTO.getLink() != null)
            contents.setLink(contentsUpdateDTO.getLink());
        if(contentsUpdateDTO.getIsVisible() != null)
            contents.setIsVisible(contentsUpdateDTO.getIsVisible());

        return contents.getId();
    }

    public void deleteContents(Long contentsId) {
        Contents contents = contentsRepository.findById(contentsId).orElseThrow(() -> ContentsNotFound.EXCEPTION);
        contentsRepository.delete(contents);
    }

    public Contents findContentsByContentsTopicOrThrow(ContentsType contentsType, ContentsTopic essentialContentsTopic) {
        return contentsRepository.findOneByTypeAndTopicOrderByIdDesc(contentsType, essentialContentsTopic)
                .orElseThrow(() -> EssentialContentsNotFound.EXCEPTION);
    }
}
