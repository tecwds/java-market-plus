package top.wpaint.marketplus.service;

import com.mybatisflex.core.service.IService;
import top.wpaint.marketplus.entity.Tag;
import top.wpaint.marketplus.entity.dto.TagDTO;
import top.wpaint.marketplus.entity.vo.TagVO;

import java.util.List;

/**
 *  服务层。
 *
 * @author tecwds
 * @since 2024-11-04
 */
public interface TagService extends IService<Tag> {

    List<TagVO> doGetTagList();

    String doAddTagBatch(List<TagDTO> tags);

    String doUpdateTagBatch(List<TagDTO> tags);
}
