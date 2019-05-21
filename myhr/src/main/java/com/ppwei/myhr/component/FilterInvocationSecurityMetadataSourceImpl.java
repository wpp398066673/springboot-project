package com.ppwei.myhr.component;

import com.ppwei.myhr.bean.Menu;
import com.ppwei.myhr.bean.Role;
import com.ppwei.myhr.mapper.MenuMapper;
import com.ppwei.myhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取当前请求url
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //获取所有资源和该资源对应的角色
        //Menu类有两个核心属性，一个是url pattern，即匹配规则(比如/admin/**)，
        // 还有一个是List,即这种规则的路径需要哪些角色才能访问
        List<Menu> menus = menuService.getAllMenu();
        for (Menu menu : menus){
            if(antPathMatcher.match(menu.getUrl(),requestUrl) && menu.getRoles().size()>0){
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for(int i=0;i<size;i++){
                    values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }

        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
