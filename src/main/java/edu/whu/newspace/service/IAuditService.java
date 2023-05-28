package edu.whu.newspace.service;

/**
 * @author Newspace
 * @version 1.0
 * @description 博客审计服务
 * @date 2023/5/24 16:53
 */
public interface IAuditService {
    /**
     * 定时任务自动审计博客内容是否出现非法内容
     * @param id   博客id
     */
    void auditBlogs(Long id);
}
