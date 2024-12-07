package top.wpaint.marketplus.controller.category;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.controller.BaseController;
import top.wpaint.marketplus.entity.dto.TagDTO;
import top.wpaint.marketplus.entity.vo.TagVO;

import java.util.List;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/api/tag")
public class TagController extends BaseController {

    @SaIgnore
    @GetMapping("list")
    public Result<List<TagVO>> getTagList() {
        log.info("获得所有标签信息");
        return Result.success(tagService.doGetTagList());
    }

    @PutMapping("batch")
    public Result<String> addTagBatch(@RequestBody List<TagDTO> tags) {
        log.info("批量添加 Tag -- {}", tags.size());
        return Result.success(tagService.doAddTagBatch(tags));
    }

    @DeleteMapping("batch")
    public Result<String> deleteTagBatch(@RequestBody List<String> ids) {
        log.info("批量删除 Tag -- {}", ids.size());
        tagService.removeByIds(ids);
        return Result.success();
    }

    @PostMapping("batch")
    public Result<String> updateTagBatch(@RequestBody List<TagDTO> tags) {
        log.info("批量修改 Tag -- {}", tags.size());
        return Result.success(tagService.doUpdateTagBatch(tags));
    }
}
