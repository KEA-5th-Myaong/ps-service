package myaong.popolog.psservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PsRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String position;

    @NotBlank
    private String reason;

    @NotBlank
    private String content;
}
