package com.yequ.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhaost
 * @create: 2022-09-02
 **/
public class WebResponseUtils {

    public static void renderResponse(HttpServletResponse response,String message) throws IOException {
        response.setStatus(CommonConstant.STATUS_SUCCESS);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(message);
    }

}
