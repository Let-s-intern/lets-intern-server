package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program {

    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private ProgramType type;

    @NotNull
    private Integer th;

    @Builder
    private Program(ProgramType type, Integer th) {
        this.type = type;
        this.th = th;
    }

    public static Program of(ProgramCreateRequestDTO programCreateRequestDTO, Integer th) {
        return Program.builder()
                .type(programCreateRequestDTO.getType())
                .th(th)
                .build();
    }
}
