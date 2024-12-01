    package com.Academy.Task_Tool.Services;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.mail.SimpleMailMessage;
    import org.springframework.mail.javamail.JavaMailSender;
    import org.springframework.scheduling.annotation.Async;
    import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
    import org.springframework.stereotype.Service;

    import java.util.concurrent.Executor;

    @Service
    public class EmailService {

        @Autowired
        private JavaMailSender mailSender;


        @Async("async")
        public void sendEmail(String to, String subject, String body) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("mostafa.adly1999@gmail.com");
            mailSender.send(message);
        }
    }





