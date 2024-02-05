package umc.th.juinjang.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;
import umc.th.juinjang.apiPayload.ExceptionHandler;
import umc.th.juinjang.apiPayload.code.status.ErrorStatus;
import umc.th.juinjang.service.JwtService;

import java.io.IOException;
import java.rmi.server.ExportException;

// Jwt 토큰으로 인증하는 필터입니다.

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter{
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private JwtService jwtService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtService =jwtService;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("여기");
        //헤더에서 토큰 가져오기
        String token = jwtService.resolveToken(request);
        String requestURI = request.getRequestURI();

        // 토큰이 존재 여부 + 토큰 검증
        if (StringUtils.isNotEmpty(token) && jwtService.validateToken(token)) {
            logger.info("토큰 검증");
            Authentication authentication = jwtService.getAuthentication(token);
            // security 세션에 등록
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}");
        } else {
            logger.info("유효한 JWT 토큰이 없습니다, uri: {} "+ requestURI);
            throw new ExceptionHandler(ErrorStatus.TOKEN_UNAUTHORIZED);
        }
        chain.doFilter(request, response);

    }
}
