package com.jeecg.p3.system.web.back;

import com.jeecg.p3.system.entity.JwSystemUser;
import com.jeecg.p3.system.service.JwSystemUserService;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/weixin/message")
public class MessageSearchController {
    @Autowired
    private JwSystemUserService jwSystemUserService;

    @GetMapping("phone/search")
    public void phoneSearch(HttpServletResponse response, HttpServletRequest request) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("signature", "test");
        String viewName = "weixin/search/phone.vm";
        ViewVelocity.view(request,response,viewName,velocityContext);
    }

    @GetMapping("phone/list")
    public void phoneList(@ModelAttribute JwSystemUser query,HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
                          @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception {
        PageQuery<JwSystemUser> pageQuery = new PageQuery<JwSystemUser>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        VelocityContext velocityContext = new VelocityContext();
        pageQuery.setQuery(query);
        velocityContext.put("jwSystemUser",query);
        velocityContext.put("pageInfos", SystemTools.convertPaginatedList(jwSystemUserService.queryPageList(pageQuery)));
        String viewName = "search/phone-list.vm";
        ViewVelocity.view(request,response,viewName,velocityContext);
    }
}
