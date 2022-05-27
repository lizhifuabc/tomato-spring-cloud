package com.tomato.oauth.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * jwt 配置
 *
 * @author lizhifu
 * @date 2022/5/17
 */
@Configuration(proxyBeanMethods = false)
public class JwtConfiguration {
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws KeyStoreException, IOException, JOSEException, CertificateException, NoSuchAlgorithmException {
        String path = "jose.jks";
        String alias = "jose";
        String pass = "felord.cn";

        ClassPathResource resource = new ClassPathResource(path);
        KeyStore jks = KeyStore.getInstance("jks");
        char[] pin = pass.toCharArray();
        jks.load(resource.getInputStream(), pin);
        RSAKey rsaKey = RSAKey.load(jks, alias, pin);
        return (jwkSelector, securityContext) -> jwkSelector.select(new JWKSet(rsaKey));
    }
}
