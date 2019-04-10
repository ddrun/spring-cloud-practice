package com.example.djran.gateway.config;

import com.alibaba.fastjson.JSON;
import com.example.djran.gateway.dao.RouteDefineDao;
import com.example.djran.gateway.model.RouteDefine;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RouteConfig  implements RouteDefinitionRepository {

    @Autowired
    private RouteDefineDao routeDefineDao;
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        List<RouteDefine> routeDefines=routeDefineDao.findAll();
        if(routeDefines!=null&&routeDefines.size()>0){
            for(RouteDefine gwRouteConf:routeDefines){
                RouteDefinition definition = new RouteDefinition();
                definition.setId(gwRouteConf.getRouteId());
//                URI uri = UriComponentsBuilder.fromHttpUrl(gwRouteConf.getUri()).build().toUri();
                try {
                    definition.setUri(new URI(gwRouteConf.getUri()));
                }catch (URISyntaxException e){
                    e.printStackTrace();
                }
                List<PredicateDefinition> predicateDefinitionList = JSON.parseArray(
                       gwRouteConf.getPredicates(),PredicateDefinition.class);
                definition.setPredicates(predicateDefinitionList);
                routeDefinitions.add(definition);
            }
        }
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
