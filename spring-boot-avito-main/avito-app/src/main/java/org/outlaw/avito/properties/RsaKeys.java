package org.outlaw.avito.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
@Data
@Component
public class RsaKeys {
    private RSAPublicKey publicKey;
}
