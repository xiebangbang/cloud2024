package com.atguigu.cloud.mygateway;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.AfterRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {

        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //检查request的参数里面，usertype是否为指定的值，符号配置就通过
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");

                if(userType==null || userType.isEmpty()){
                    return false;
                }

                //参数存在，就和config的数据(即yaml文件中配置的参数值)进行比较
                if (userType.equals(config.getUserType())) {
                    return true;
                }

                return false;
            }
        };
    }

    @Validated
    public static class Config {
        @Getter
        @Setter
        @NotEmpty
        private String userType;//钻、金、银等用户等级
    }
}
