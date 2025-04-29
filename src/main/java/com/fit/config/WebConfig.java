package com.fit.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.fit.util.spring.interceptor.ExceptionInterceptor;
import com.fit.util.spring.interceptor.SecurityInterceptor;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @AUTO web 配置类
 * @Author AIM
 * @DATE 2019/4/24
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.mvc.view.prefix}")
    private String prefix = "/jsp/";
    @Value("${spring.mvc.view.suffix}")
    private String suffix = ".jsp";

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver(prefix, suffix);
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setCache(true);
        registry.viewResolver(resolver);
        resolver.setContentType("text/html;charset=UTF-8");
        registry.enableContentNegotiation(new FastJsonJsonView());
    }

    /**
     * 访问根路径默认跳转 index.html页面 （简化部署方案： 可以把前端打包直接放到项目的 webapp，上面的配置）
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public ExceptionInterceptor getExceptionInterceptor() {
        return new ExceptionInterceptor();
    }

    @Bean
    public SecurityInterceptor securityInterceptor() {
        SecurityInterceptor securityInterceptor = new SecurityInterceptor();
        ArrayList<String> list = new ArrayList<String>();
        list.add("/admin/downloadDir.html");
        list.add("/admin/exit.html");
        list.add("/admin/intercept.html");
        list.add("/admin/login.html");
        list.add("/admin/rollBack.html");
        list.add("/admin/verifyCode.html");
        securityInterceptor.setExcludeUrls(list);
        return securityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // 请求参数中的 lang 用于切换语言
        registry.addInterceptor(interceptor);
        registry.addInterceptor(getExceptionInterceptor());
        registry.addInterceptor(securityInterceptor()).order(2);
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
                req.setAttribute("ctx", req.getContextPath());
                if (req.getSession().getAttribute("user") != null) {
                    req.setAttribute("user", req.getSession().getAttribute("user"));
                }
                return true;
            }
        }).order(1).addPathPatterns("/**"); // 设置拦截的路径
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //解决返回字符串带双引号问题
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML));
        stringHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(stringHttpMessageConverter);
        //定义一个convert转换消息的对象
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                // 保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的null转成""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的null转成0
                SerializerFeature.WriteNullNumberAsZero,
                // 将List类型的null转成[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的null转成false
                SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //在convert中添加配置信息
        fastConverter.setFastJsonConfig(config);
        //设置支持的媒体类型
        fastConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        //设置默认字符集
        fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
        //将convert添加到converters
        converters.add(fastConverter);
    }

    /**
     * 验证码生成相关
     */
    @Bean
    public DefaultKaptcha kaptcha() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.border.color", "105,179,90");
        properties.put("kaptcha.image.width", "100");
        properties.put("kaptcha.image.height", "40");
        properties.put("kaptcha.textproducer.font.color", "blue");
        properties.put("kaptcha.textproducer.font.size", "35");
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.textproducer.font.names", "彩云,宋体,楷体,微软雅黑");
        properties.put("kaptcha.session.key", "code");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}