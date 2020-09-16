package com.dev.Scamboo.config.mail;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Component
public class Mail {


    private List<String> to;

    private String subject;

    private String body;
}
