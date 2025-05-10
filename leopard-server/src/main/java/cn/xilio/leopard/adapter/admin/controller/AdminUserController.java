package cn.xilio.leopard.adapter.admin.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.xilio.leopard.adapter.admin.dto.request.AddUserRequest;
import cn.xilio.leopard.adapter.admin.dto.request.UpdateUserRequest;
import cn.xilio.leopard.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.leopard.core.common.exception.BizException;
import cn.xilio.leopard.service.UploadService;
import cn.xilio.leopard.service.UserService;
import cn.xilio.leopard.core.common.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AdminUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UploadService uploadService;


    @PostMapping(value = "list", name = "All user list")
    public Result list(@Validated @RequestBody UserPageQueryRequest request, HttpServletRequest request2) {
        return Result.success(userService.list(request));
    }

    @GetMapping(value = "get", name = "Get user info")
    public Result get(@RequestParam String userId) {
        return Result.success();
    }

    @PostMapping(value = "add", name = "Add a user  ")
    public Result add(@Validated @RequestBody AddUserRequest request) {
        userService.addUser(request);
        return Result.success();
    }

    @PutMapping(value = "update", name = "Update a user  ")
    public Result update(@Validated @RequestBody UpdateUserRequest request) {
        userService.updateUser(request);
        return Result.success();
    }

    @DeleteMapping(value = "del", name = "Batch delete user")
    public Result del(@RequestBody List<String> ids) {
        userService.deleteUser(ids);
        return Result.success();
    }

    @PostMapping(value = "upload-avatar", name = "Upload user avatar")
    public Result uploadAvatar(@RequestParam("file") @NotNull @Valid MultipartFile file) {
        // 1. 参数校验
        if (file.isEmpty()) {
            throw new BizException("6008");
        }
//        // 2. 文件类型校验
//        String originalFilename = file.getOriginalFilename();
//        String extension = originalFilename != null ?
//                originalFilename.substring(originalFilename.lastIndexOf(".") + 1) : "";
//        if (!Arrays.asList("jpg", "jpeg", "png", "gif").contains(extension.toLowerCase())) {
//            throw new IllegalArgumentException("仅支持jpg/jpeg/png/gif格式图片");
//        }
//
//        // 3. 文件大小校验（示例限制2MB）
//        long maxSize = 2 * 1024 * 1024;
//        if (file.getSize() > maxSize) {
//            throw new IllegalArgumentException("文件大小不能超过2MB");
//        }

        // 4. 执行上传
        String fileUrl = uploadService.upload(file);
        return Result.success(fileUrl);
    }

}
