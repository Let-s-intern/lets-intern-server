package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.program.domain.converter.MailStatusConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@DiscriminatorValue("lets_chat")
@Entity
public class LetsChat extends Program {
    @Id
    @Column(name = "lets_chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10)
    @Convert(converter = MailStatusConverter.class)
    private MailStatus mailStatus;
    private String mentorPassword;
    private String link;
    @Column(length = 8)
    private String linkPassword;
}
