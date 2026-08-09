package se.iths.erikthorell.nexusnewdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.nexusnewdemo.config.RabbitMqConfig;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftBookedMessage;


@Service
@RequiredArgsConstructor
public class ShiftBookedConsumer {
    private final JavaMailSender mailSender;

    @RabbitListener(queues = RabbitMqConfig.SHIFT_BOOKED_QUEUE)
    public void sendMail(ShiftBookedMessage message){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(message.email());
        mail.setSubject("Shift booked");
        mail.setText(
                        "Hi " + message.name() +
                                ", your shift at " + message.location() +
                                " on " + message.shiftTime() +
                                " is booked."
                );
        mailSender.send(mail);
    }
}
