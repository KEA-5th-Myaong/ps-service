package myaong.popolog.psservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PsListResponse {
    private List<PsResponse> psList;
}
