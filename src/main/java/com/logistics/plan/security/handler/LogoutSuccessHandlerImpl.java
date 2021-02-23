package com.logistics.plan.security.handler;

import com.alibaba.fastjson.JSON;
import com.logistics.plan.constant.HttpStatus;
import com.logistics.plan.domain.module.LoginUser;
import com.logistics.plan.service.TokenService;
import com.logistics.plan.utils.ComUtil;
import com.logistics.plan.utils.R;
import com.logistics.plan.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)

    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (!ComUtil.isEmpty(loginUser))
        {
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response, JSON.toJSONString(R.failed(HttpStatus.SUCCESS, "退出成功")));
    }
}
