package myaong.popolog.psservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PsResponse {
    private String title;
    private String position;
    private String reason;
    private String content;
}
