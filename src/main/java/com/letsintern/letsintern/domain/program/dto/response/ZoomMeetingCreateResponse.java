package com.letsintern.letsintern.domain.program.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class ZoomMeetingCreateResponse implements Serializable {

    private String host_email;

    private String id;

    private String created_at;

    private String join_url;

    private Boolean pre_schedule;

    private String start_time;

    private String password;

    private String agenda;

    private Integer duration;

    private String timezone;

    private String topic;

    private Integer type;
}
