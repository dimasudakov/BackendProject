package dev.dima.authservice.filters;

import dev.dima.authservice.models.JwtAuthentication;
import dev.dima.authservice.services.JwtProvider;
import dev.dima.authservice.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
            throws IOException, ServletException {

        final Optional<String> token = getTokenFromRequest((HttpServletRequest) request);
        if (token.isPresent() && jwtProvider.validateAccessToken(token.get())) {
            final Claims claims = jwtProvider.getAccessClaims(token.get());
            final JwtAuthentication jwtInfoToken = JwtUtils.generate(claims);
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        }
        fc.doFilter(request, response);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {

        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith(BEARER)) {
            return Optional.of(bearer.substring(BEARER.length()));
        }
        return Optional.empty();
    }

}
