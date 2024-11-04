package com.nicolas.cliente.service.messaging;

import com.nicolas.cliente.service.model.Cliente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange = "cliente-exchange";  // Nombre del exchange
    private final String routingKey = "cliente.created";  // Routing key para el evento de cliente creado

    public ClienteEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Publica un evento a RabbitMQ después de que un cliente ha sido creado.
     * @param cliente El cliente recién creado
     */
    public void publish(Cliente cliente) {
        rabbitTemplate.convertAndSend(exchange, routingKey, cliente);
    }
}
