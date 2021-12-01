package org.saikrishna.apps.mynotes.config;

import org.aspectj.lang.Aspects;
import org.saikrishna.apps.mynotes.aspects.SecurityAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    SecurityAspect securityAspect(){
        return Aspects.aspectOf(SecurityAspect.class);
    }
}
