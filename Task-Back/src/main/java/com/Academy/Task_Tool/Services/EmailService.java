    package com.Academy.Task_Tool.Services;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.mail.SimpleMailMessage;
    import org.springframework.mail.javamail.JavaMailSender;
    import org.springframework.stereotype.Service;

    @Service
    public class EmailService {

        @Autowired
        private JavaMailSender mailSender;

        public void sendEmail(String to, String subject, String body) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("mostafa.adly1999@gmail.com");
            mailSender.send(message);
        }
    }

