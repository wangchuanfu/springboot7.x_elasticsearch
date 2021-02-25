package com.j1.result;

import io.netty.util.internal.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by wangchuanfu on 20/8/7.
 */
public class JsonpBaseAction {
    protected JsonResult _result = new JsonResult();

    /**
     * Automatic set result as failure<br />
     * easy to judge the result status from outer calling
     *
     * @param request
     * @param response
     */
    public void writeError(HttpServletRequest request,
                           HttpServletResponse response) {
        _result.setFailure();
        this.write(request, response);
    }

    /**
     * Automatic set result as success, same as
     * {@link #writeError(HttpServletRequest, HttpServletResponse)}
     *
     * @param request
     * @param response
     */
    public void writeSuccess(HttpServletRequest request,
                             HttpServletResponse response) {
        _result.setSuccess();
        this.write(request, response);
    }

    /**
     * Using request parameter <code>callback</code> as default jsonp callback
     * function name
     *
     * @param request
     * @param response
     */
    public void write(HttpServletRequest request, HttpServletResponse response) {
        String callback = request.getParameter("callback");
        this.write(request, response, callback);
    }

    /**
     * Re-initialize result after write
     *
     * @param request
     * @param response
     * @param callback
     */
    public void write(HttpServletRequest request, HttpServletResponse response,
                      String callback) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String result = null;
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            if (callback != null)
                result = _result.toString();
            else
                result = callback + "(" + _result.toString() + ")";
        } catch (Exception e) {

        } finally {
            if (writer != null) {
                writer.write(result);
                writer.close();
            }
            if (result != null)
                _result = new JsonResult();
        }
    }
}
