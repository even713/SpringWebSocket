package org.cara.com;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker //enables WebSocket message handling, backed by a message broker.
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	static final String MESSAGE_BROKER = "/topic";
	static final String DESTINATION_PREFIX = "/app";

	// configure the message broker
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// enable a simple memory-based message broker to carry the greeting messages back to the client on destinations prefixed with "/topic".
		config.enableSimpleBroker(MESSAGE_BROKER);
		// designates the "/app" prefix for messages that are bound for @MessageMapping-annotated methods
		config.setApplicationDestinationPrefixes(DESTINATION_PREFIX);
		
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// registers the "/gs-guide-websocket" endpoint, enabling SockJS fallback options 
		// so that alternate transports may be used if WebSocket is not available. 
		// The SockJS client will attempt to connect to "/gs-guide-websocket" and use the 
		// best transport available (websocket, xhr-streaming, xhr-polling, etc).
		registry.addEndpoint("/gs-guide-websocket").withSockJS();
		
	}

}
