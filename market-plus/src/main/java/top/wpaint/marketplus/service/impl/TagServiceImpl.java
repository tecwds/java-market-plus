package top.wpaint.marketplus.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import top.wpaint.marketplus.common.Status;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.entity.Tag;
import top.wpaint.marketplus.entity.dto.TagDTO;
import top.wpaint.marketplus.entity.vo.TagVO;
import top.wpaint.marketplus.mapper.TagMapper;
import top.wpaint.marketplus.service.TagService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务层实现。
 *
 * @author tecwds
 * @since 2024-11-04
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<TagVO> doGetTagList() {
        List<Tag> tagList = this.list();
        List<TagVO> voList = new ArrayList<>(tagList.size());

        tagList.forEach(it -> {
            TagVO vo = new TagVO();
            BeanUtils.copyProperties(it, vo);
            voList.add(vo);
        });

        return voList;
    }

    @Override
    public String doAddTagBatch(List<TagDTO> tags) {
        List<Tag> tagList = new ArrayList<>(tags.size());

        tags.forEach(it -> tagList.add(Tag.builder()
                .name(it.getName())
                .description(it.getDescription())
                .isEnabled(LogicConst.ENABLE)
                .build())
        );

        tagMapper.insertBatch(tagList);
        return Status.SUCCESS.getMessage();
    }

    @Override
    public String doUpdateTagBatch(List<TagDTO> tags) {
        List<Tag> tagList = new ArrayList<>(tags.size());

        tags.forEach(it -> tagList.add(Tag.builder()
                .id(new BigInteger(it.getId()))
                .name(it.getName())
                .description(it.getDescription())
                .build()));

        this.updateBatch(tagList);
        return Status.SUCCESS.getMessage();
    }
}
