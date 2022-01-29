package com.ruoyi.web.controller.doc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模态窗口
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/doc/provider")
public class DocController
{
    private String prefix = "doc/provider";

    /**
     * test
     */
    @GetMapping("/test")
    public String dialog()
    {
        return prefix + "/test";
    }


}
