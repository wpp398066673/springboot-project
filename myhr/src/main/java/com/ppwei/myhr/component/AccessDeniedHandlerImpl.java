package com.ppwei.myhr.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppwei.myhr.bean.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        RespBean error = RespBean.error("权限不足，请联系管理员");
        pw.write(new ObjectMapper().writeValueAsString(error));
        pw.flush();
        pw.close();
    }
}
