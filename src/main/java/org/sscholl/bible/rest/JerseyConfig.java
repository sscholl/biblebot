package org.sscholl.bible.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Configuration
@ApplicationPath("/biblebot")
public class JerseyConfig extends ResourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyConfig.class);

    @Autowired
    public JerseyConfig(ObjectMapper objectMapper) {
        LOGGER.info("register endpoints");
        packages("org.sscholl.bible.common.rest");
        packages("org.sscholl.bible.biblebot.rest");
        packages("org.sscholl.bible.biblereadingplan.rest");
        // register jackson for json
        register(new ObjectMapperContextResolver(objectMapper));
        register(new JerseyEndpointLoggingListener());
    }

    @Provider
    public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        private final ObjectMapper mapper;

        public ObjectMapperContextResolver(ObjectMapper mapper) {
            this.mapper = mapper;
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return mapper;
        }
    }


    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }

}