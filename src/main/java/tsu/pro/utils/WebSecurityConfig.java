package tsu.pro.utils;

import org.apache.avro.data.Json;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        // addInterceptor.excludePathPatterns("/error");

        // 拦截配置
        addInterceptor.addPathPatterns("/**").excludePathPatterns("/**/**/login/**", "pro_Servers/users/login/**","/pro_Servers/error");
        super.addInterceptors(registry);
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null)
                return true;

            // 跳转登录
            response.setStatus(401);
            OutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-Type", "application/json;charset=UTF-8");

            outputStream.write("{\"errcode\":401,\"msg \":\"当前未登录\"}".getBytes("UTF-8"));
            return false;
        }
    }
}
