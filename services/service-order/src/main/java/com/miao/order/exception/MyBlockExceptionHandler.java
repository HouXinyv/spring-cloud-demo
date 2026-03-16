package com.miao.order.exception;


import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miao.common.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, String resourceName, BlockException e) throws Exception {
        response.setStatus(429); //too many request
        response.setContentType("application/json;charset=utf-8");

        PrintWriter writer = response.getWriter();
        R error = R.error(500, resourceName + "被Sentinel限制了" + e.getCause());
        String s = objectMapper.writeValueAsString(error);
        writer.write(s);
        writer.flush();
        writer.close();
    }
}
