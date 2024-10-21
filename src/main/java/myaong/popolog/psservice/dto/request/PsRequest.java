package myaong.popolog.psservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PsRequest {


    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "지원직무를 입력해주세요")
    private String position;

    @NotBlank(message = "지원사유를 입력해주세요")
    private String reason;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;
}
