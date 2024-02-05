package com.letsintern.letsintern.domain.attendance.dto.response;

import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AttendanceAdminListResponse {

    private List<AttendanceAdminVo> attendanceList = new ArrayList<>();
    private PageInfo pageInfo;

    @Builder
    private AttendanceAdminListResponse(Page<AttendanceAdminVo> attendanceList) {
        if(attendanceList.hasContent()) this.attendanceList = attendanceList.getContent();
        this.pageInfo = PageInfo.of(attendanceList);
    }

    public static AttendanceAdminListResponse from(Page<AttendanceAdminVo> attendanceList) {
        return AttendanceAdminListResponse.builder()
                .attendanceList(attendanceList)
                .build();
    }
}
