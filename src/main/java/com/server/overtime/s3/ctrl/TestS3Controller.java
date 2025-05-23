package com.server.overtime.s3.ctrl;

import com.server.overtime.s3.ctrl.req.GetPresignedUrlReq;
import com.server.overtime.s3.ctrl.res.PresignedUrlRes;
import com.server.overtime.s3.sv.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/s3")
@RequiredArgsConstructor
public class TestS3Controller {
    private final FileService fileService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "테스트용 PresignedUrl API")
    public PresignedUrlRes getPresignedUrl(
            @RequestParam
            String fileName
    ) {
        return new PresignedUrlRes(fileService.getPresignedUrl(fileName));
    }

}
