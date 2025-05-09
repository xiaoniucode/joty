package cn.xilio.leopard.core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class JwtUtils {
    private static final RSAPublicKey PUBLIC_KEY;
    private static final RSAPrivateKey PRIVATE_KEY;

    static {
        try {
            // 密钥对生成（静态代码块中执行，类加载时触发）
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // 使用2048位密钥（安全推荐[1,5,8](@ref)）
            KeyPair keyPair = keyGen.generateKeyPair();

            PUBLIC_KEY = (RSAPublicKey) keyPair.getPublic();
            PRIVATE_KEY = (RSAPrivateKey) keyPair.getPrivate();
        } catch (Exception e) {
            throw new RuntimeException("密钥对初始化失败", e);
        }
    }

    public static String createToken(String issuer) {
        try {
            SecurityProperties properties = SpringHelper.getBean(SecurityProperties.class);

            Algorithm algorithm = Algorithm.RSA256(PUBLIC_KEY, PRIVATE_KEY);
            return JWT.create()
                    .withIssuer(issuer)
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Token生成失败", e);
        }
    }

    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(PUBLIC_KEY, PRIVATE_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Token验证失败", e);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String token = JwtUtils.createToken("xilio");
        System.out.println(token);
        System.out.println(JwtUtils.verifyToken(token));
    }
}
