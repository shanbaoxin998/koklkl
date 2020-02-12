package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2020-02-11
 */
public interface EduChapterService extends IService<EduChapter> {


    List<ChapterVo> getChapterVideoById(String courseId);

    void removeChapterById(String id);
}
