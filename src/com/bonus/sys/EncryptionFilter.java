package com.bonus.sys;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author syruan
 */
@WebFilter(urlPatterns = "/backstage/xxxxxxxxxxxxxxxxxx")
public class EncryptionFilter implements Filter {

    /// 加密算法
    private static final String ALGORITHM = "AES";
    /// key密钥
    private static final String KEY = "YourSecretKey123";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter initialization
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 对请求进行解密
        //String encryptedRequestBody = getRequestBody(httpRequest);
        //String decryptedRequestBody = decrypt(encryptedRequestBody); // 你的解密逻辑

        // 使用HttpServletRequestWrapper来处理请求体
//        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
//            @Override
//            public BufferedReader getReader() {
//                return new BufferedReader(new java.io.StringReader(decryptedRequestBody));
//            }
//        };

        // 使用HttpServletResponseWrapper来捕获响应内容
        CharResponseWrapper responseWrapper = new CharResponseWrapper(httpResponse);

        // 放行处理
        filterChain.doFilter(httpRequest, responseWrapper);

        // 获取响应内容
        String responseBody = responseWrapper.toString();
        // 加密逻辑
        String encryptedResponseBody;
        try {
            encryptedResponseBody = encrypt(responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 将加密后的响应内容写回原始响应
        PrintWriter out = httpResponse.getWriter();
        out.write(encryptedResponseBody);
        out.flush();
    }

    @Override
    public void destroy() {
        // Filter destruction
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        // 获取请求体
        BufferedReader reader = request.getReader();
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    private String decrypt(String data) {
        // 实现你的解密逻辑
        System.out.println("Decrypting: " + data);
        return "Decrypted Data"; // 示例解密后的数据
    }

    /**
     * 加密操作
     * @param data 需要加密的内容
     * @return 加密后的数据
     * @throws Exception 异常捕获
     */
    private String encrypt(String data) throws Exception {
        System.out.println("Encrypting: " + data);
        // 加密逻辑
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedValue = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    // 自定义HttpServletResponseWrapper来捕获响应内容
    public static class CharResponseWrapper extends HttpServletResponseWrapper {
        private final CharArrayWriter charArrayWriter;

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);
            charArrayWriter = new CharArrayWriter();
        }

        @Override
        public PrintWriter getWriter() {
            return new PrintWriter(charArrayWriter);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new CharArrayServletOutputStream(charArrayWriter);
        }

        @Override
        public String toString() {
            return charArrayWriter.toString();
        }
    }

    // 自定义ServletOutputStream来捕获响应内容
    public static class CharArrayServletOutputStream extends ServletOutputStream {
        private final CharArrayWriter charArrayWriter;

        public CharArrayServletOutputStream(CharArrayWriter charArrayWriter) {
            this.charArrayWriter = charArrayWriter;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            // 不实现
        }

        @Override
        public void write(int b) throws IOException {
            charArrayWriter.write(b);
        }
    }
}
