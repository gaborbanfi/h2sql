package com.garage.sql.h2sql.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class QuestionAspect {
    private final static Logger logger = LoggerFactory.getLogger(QuestionAspect.class);

    @After("execution(* com.garage.sql.h2sql.dao.QuestionDao.*(..))")
    public void afterQuestioning(JoinPoint joinPoint){
        logger.info("Another foolish hooman asked thy whether {}", encode(joinPoint.getArgs()[0]));
    }

    private String encode(Object message) {
        String msg = (String) message;
        msg = msg.replace('\n', '_')
                .replace('\r', '_')
                .replace('\t', '_');
        return msg;
    }
}
