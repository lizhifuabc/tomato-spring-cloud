package com.tomato.auth.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.security.KeyStore;
/**
 * jwt配置
 * @author lizhifu
 */
@Configuration(proxyBeanMethods = false)
public class JwtConfiguration {
    @Value("${jwt.path}")
    private String path;
    @Value("${jwt.alias}")
    private String alias;
    @Value("${jwt.pass}")
    private String pass;
    /**
     * jwt配置
     * keytool -genkey -alias jwt -keyalg RSA -keysize 2048 -keystore jwt.jks -validity 3650
     * 正在为以下对象生成 2,048 位RSA密钥对和自签名证书 (SHA256withRSA) (有效期为 3,650 天):
     * 	 CN=l, OU=l, O=l, L=l, ST=l, C=l
     *
     * JWK：既然涉及到签名，就涉及到签名算法，对称加密还是非对称加密，那么就需要加密的 密钥或者公私钥对。
     * 此处我们将 JWT的密钥或者公私钥对统一称为 JSON WEB KEY，即 JWK。
     *
     * JWS：指的是签过名的JWT，即拥有签名的JWT
     *
     * JWT：指的是 JSON Web Token，不存在签名的JWT是不安全的，存在签名的JWT是不可窜改的
     * @return
     */
    @SneakyThrows
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        ClassPathResource resource = new ClassPathResource(path);
        KeyStore jks = KeyStore.getInstance("jks");
        char[] pin = pass.toCharArray();
        jks.load(resource.getInputStream(), pin);
        RSAKey rsaKey = RSAKey.load(jks, alias, pin);
        return (jwkSelector, securityContext) -> jwkSelector.select(new JWKSet(rsaKey));
    }
}
