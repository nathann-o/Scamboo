package com.dev.Scamboo.config.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Getter
@Setter
public class Mailer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void send(Mail message) throws MessagingException {

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        messageHelper.setFrom(String.format("Scamboo<%s>",from));
        messageHelper.setTo(message.getTo().toArray(new String[message.getTo().size()]));
        messageHelper.setSubject(message.getSubject());
        messageHelper.setText(message.getBody(),true);

        this.javaMailSender.send(mimeMessage);
    }
}
