package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program {

    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private ProgramType type;

    @NotNull
    private Integer th;

    @NotNull
    private Date dueDate;

    @NotNull
    private String announcementDate;

    @NotNull
    private String startDate;

    @NotNull
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private ProgramStatus status = ProgramStatus.CLOSED;

    @Builder
    private Program(ProgramType type, Integer th, Date dueDate, Date announcementDate, Date startDate) {
        this.type = type;
        this.th = th;
        this.dueDate = dueDate;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.announcementDate = simpleDateFormat.format(announcementDate);

        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        this.startDate = simpleDateFormatWithTime.format(startDate);
    }

    public static Program of(ProgramCreateRequestDTO programCreateRequestDTO, Integer th) {
        return Program.builder()
                .type(programCreateRequestDTO.getType())
                .th(th)
                .dueDate(programCreateRequestDTO.getDueDate())
                .announcementDate(programCreateRequestDTO.getAnnouncementDate())
                .startDate(programCreateRequestDTO.getStartDate())
                .build();
    }
}
