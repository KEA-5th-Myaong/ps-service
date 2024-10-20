package myaong.popolog.psservice.controller;

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

    @GetMapping
    public ResponseEntity<ApiResponse<PsListResponse>> getPsList() {
        PsListResponse response = psService.getPsList();
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @GetMapping("/{psId}")
    public ResponseEntity<ApiResponse<PsResponse>> getPs(@PathVariable Long psId) {
        PsResponse response = psService.getPs(psId);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PsIdResponse>> createPs(@Valid @RequestBody PsRequest psRequest) {
        PsIdResponse response = psService.createPs(psRequest);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @PutMapping("/{psId}")
    public ResponseEntity<ApiResponse<Object>> updatePs(@PathVariable Long psId, @Valid @RequestBody PsRequest psRequest) {
        psService.updatePs(psId, psRequest);
        return ResponseEntity.ok(ApiResponse.onSuccess(null));
    }

    @DeleteMapping("/{psId}")
    public ResponseEntity<ApiResponse<Object>> deletePs(@PathVariable Long psId) {
        psService.deletePs(psId);
        return ResponseEntity.ok(ApiResponse.onSuccess(null));
    }

    @GetMapping("/{psId}/editing")
    public ResponseEntity<ApiResponse<PsResponse>> editPs(@PathVariable Long psId) {
        PsResponse psResponse = psService.editPs(psId);
        return ResponseEntity.ok(ApiResponse.onSuccess(psResponse));
    }
}
