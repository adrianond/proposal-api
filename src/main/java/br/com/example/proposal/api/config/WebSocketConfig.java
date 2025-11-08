package br.com.example.proposal.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${websocket.session-timeout-ms}")
    private long inactiveTime;

    // Armazena última atividade de cada sessão
    private final Map<String, Long> lastActivity = new ConcurrentHashMap<>();

    /**
     * Habilita broker simples com heartbeat (servidor envia e recebe a cada 10s)
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableSimpleBroker("/topic"); // tópico de saída
        //.setHeartbeatValue(new long[]{10000, 10000}); // {servidor->cliente, cliente->servidor}
        config.setApplicationDestinationPrefixes("/app"); // prefixo de envio do front
    }


    /**
     * Iniciar conexão
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-propostas")
                .setAllowedOriginPatterns("*");

        log.info("Conexão executada!");
    }


    /**
     * Limita o tamanho da mensagem e o tempo de envio, evitando travar threads com clientes lentos
     * @param registration
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setSendTimeLimit(15 * 1000);      // 15s máx para envio de msg
        registration.setSendBufferSizeLimit(512 * 1024); // 512KB
        registration.setMessageSizeLimit(128 * 1024);    // 128KB por mensagem
    }

    /**
     * Quando um cliente conecta, gravamos o sessionId e a hora.
     * Sempre que ele envia algo (SEND, SUBSCRIBE, etc.), atualizamos o tempo da última atividade.
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                String sessionId = accessor.getSessionId();

                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    lastActivity.put(sessionId, System.currentTimeMillis());
                } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
                    lastActivity.remove(sessionId);
                } else {
                    // Atualiza timestamp sempre que há mensagem
                    lastActivity.put(sessionId, System.currentTimeMillis());
                }
                return message;
            }
        });
    }

    /**
     * Agendado para rodar a cada minuto e desconectar sessões inativas (>5min sem atividade).
     */
    @Scheduled(fixedRate = 60000)
    public void closeInactiveSessions() {
        long now = System.currentTimeMillis();
        long timeout = inactiveTime; // 5 minutos

        lastActivity.forEach((sessionId, last) -> {
            if (now - last > timeout) {
                System.out.println("Encerrando sessão inativa: " + sessionId);
                // remove da lista -> broker fecha automaticamente no próximo heartbeat
                lastActivity.remove(sessionId);
            }
        });
    }
}
