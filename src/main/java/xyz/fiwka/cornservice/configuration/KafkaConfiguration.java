package xyz.fiwka.cornservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import xyz.fiwka.cornservice.data.CornAdmissionDto;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Profile("!test")
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public NewTopic cornAdmissionTopic() {
        return TopicBuilder
                .name("corn.admission")
                .build();
    }

    @Bean
    public ConsumerFactory<String, CornAdmissionDto> cornAdmissionMessageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(cornAdmissionMessageConsumerConfiguration());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CornAdmissionDto> cornAdmissionMessageConcurrentKafkaListenerContainerFactory() {
        var cornAdmissionMessageConcurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, CornAdmissionDto>();

        cornAdmissionMessageConcurrentKafkaListenerContainerFactory.setConsumerFactory(cornAdmissionMessageConsumerFactory());

        return cornAdmissionMessageConcurrentKafkaListenerContainerFactory;
    }

    @Bean
    public Map<String, Object> cornAdmissionMessageConsumerConfiguration() {
        var properties = new HashMap<String, Object>();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        properties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, CornAdmissionDto.class.getName());

        return properties;
    }
}
