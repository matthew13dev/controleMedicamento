package com.medicamento.controle.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;

@Component
public class CokieSecurerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        chain.doFilter(request, httpServletResponse);

        Collection<String> headers = httpServletResponse.getHeaders("Set-Cookie");
        boolean first = true;
        for (String header : headers) {
            if (header.contains("JSESSIONID") && !header.contains("Secure")) {
                String updatedHeader = header + "; Secure; SameSite=None";
                if (first) {
                    httpServletResponse.setHeader("Set-Cookie", updatedHeader);
                    first = false;
                } else {
                    httpServletResponse.addHeader("Set-Cookie", updatedHeader);
                }
            }
        }
    }
}