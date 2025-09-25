package com.user_service.core;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@Order(1) // A anotação @Order(1) garante que este filtro execute ANTES de outros filtros do Spring
public class SecretHeaderFilter implements Filter {

    // Injeta o valor da chave secreta do seu arquivo application.properties
    // É uma prática melhor do que deixar a chave "hardcoded" no código.
    @Value("${api.internal.secret}")
    private String expectedSecret;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Verifica se a URL da requisição começa com o nosso path interno
        if (httpRequest.getRequestURI().startsWith("/userservice/internal/")) {

            String secretHeader = httpRequest.getHeader("X-Internal-Secret");

            // Se o header não existir ou a chave estiver errada, bloqueia a requisição
            if (expectedSecret == null || !expectedSecret.equals(secretHeader)) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN); // Retorna 403 Proibido
                httpResponse.getWriter().write("Acesso Negado");
                return; // IMPORTANTE: Para a execução aqui e não continua o fluxo
            }
        }

        // Se a rota não for interna OU se o segredo estiver correto, deixa a requisição continuar
        chain.doFilter(request, response);
    }
}