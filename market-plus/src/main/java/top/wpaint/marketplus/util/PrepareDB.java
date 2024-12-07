package top.wpaint.marketplus.util;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.wpaint.marketplus.common.Result;
import top.wpaint.marketplus.common.constant.LogicConst;
import top.wpaint.marketplus.common.constant.RoleConst;
import top.wpaint.marketplus.entity.Role;
import top.wpaint.marketplus.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PrepareDB {

    @Resource
    private RoleService roleService;

    public Result<String> prepareRole() {
        List<RoleConst> rcList = RoleConst.getRoleList();
        List<Role> rList = new ArrayList<>(rcList.size());

        rcList.forEach(it -> rList.add(Role.builder()
                .name(it.getRoleName())
                .description(it.getDescription())
                .isEnabled(LogicConst.ENABLE)
                .build())
        );

        roleService.saveBatch(rList);

        return Result.success();
    }
}
