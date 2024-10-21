package myaong.popolog.psservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import myaong.popolog.psservice.common.exception.ApiResponse;
import myaong.popolog.psservice.dto.request.PsRequest;
import myaong.popolog.psservice.dto.response.PsIdResponse;
import myaong.popolog.psservice.dto.response.PsResponse;
import myaong.popolog.psservice.dto.response.PsListResponse;
import myaong.popolog.psservice.service.PsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ps")
@RequiredArgsConstructor
public class PsController {

    private final PsService psService;

    @Operation(summary = "API 명세서 v0.3 line 65", description = "자기소개서 목록 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<PsListResponse>> getPsList() {
        PsListResponse response = psService.getPsList();
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @Operation(summary = "API 명세서 v0.3 line 66", description = "자기소개서 조회")
    @GetMapping("/{psId}")
    public ResponseEntity<ApiResponse<PsResponse>> getPs(@PathVariable Long psId) {
        PsResponse response = psService.getPs(psId);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @Operation(summary = "API 명세서 v0.3 line 67", description = "자기소개서 작성")
    @PostMapping
    public ResponseEntity<ApiResponse<PsIdResponse>> createPs(@Valid @RequestBody PsRequest psRequest) {
        PsIdResponse response = psService.createPs(psRequest);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @Operation(summary = "API 명세서 v0.3 line 68", description = "자기소개서 수정")
    @PutMapping("/{psId}")
    public ResponseEntity<ApiResponse<Object>> updatePs(@PathVariable Long psId, @Valid @RequestBody PsRequest psRequest) {
        psService.updatePs(psId, psRequest);
        return ResponseEntity.ok(ApiResponse.onSuccess(null));
    }

    @Operation(summary = "API 명세서 v0.3 line 69", description = "자기소개서 목록 삭제")
    @DeleteMapping("/{psId}")
    public ResponseEntity<ApiResponse<Object>> deletePs(@PathVariable Long psId) {
        psService.deletePs(psId);
        return ResponseEntity.ok(ApiResponse.onSuccess(null));
    }

    @Operation(summary = "API 명세서 v0.3 line 70", description = "자기소개서 목록 첨삭")
    @GetMapping("/{psId}/editing")
    public ResponseEntity<ApiResponse<PsResponse>> editPs(@PathVariable Long psId) {
        PsResponse psResponse = psService.editPs(psId);
        return ResponseEntity.ok(ApiResponse.onSuccess(psResponse));
    }
}
