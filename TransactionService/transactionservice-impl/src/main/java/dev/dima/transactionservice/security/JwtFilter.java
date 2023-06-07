package dev.dima.transactionservice.security;

import dev.dima.transactionservice.security.authentication.JwtAuthentication;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    public void doFilterInternal(@NotNull HttpServletRequest request,
                                 @NotNull HttpServletResponse response,
                                 @NotNull FilterChain fc
    ) throws IOException, ServletException {

        final Optional<String> token = getTokenFromRequest(request);
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
