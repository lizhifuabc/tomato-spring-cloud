//package com.tomato.gateway.security;
//
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.ReactiveAuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.server.authorization.AuthorizationContext;
//import reactor.core.publisher.Mono;
//
///**
// * 网关自定义鉴权管理器
// *
// * @author lizhifu
// * @date 2022/6/4
// */
//public class ResourceServerManager implements ReactiveAuthorizationManager<AuthorizationContext> {
//    @Override
//    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
//        return null;
//    }
//
//    @Override
//    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
//        return ReactiveAuthorizationManager.super.verify(authentication, object);
//    }
//}
