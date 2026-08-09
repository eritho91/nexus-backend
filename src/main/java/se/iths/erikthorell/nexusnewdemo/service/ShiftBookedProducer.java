package se.iths.erikthorell.nexusnewdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.nexusnewdemo.config.RabbitMqConfig;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftBookedMessage;

@Service
@RequiredArgsConstructor
public class ShiftBookedProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendShiftBookedMessage(ShiftBookedMessage message) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.SHIFT_BOOKED_QUEUE, message);
    }
}
