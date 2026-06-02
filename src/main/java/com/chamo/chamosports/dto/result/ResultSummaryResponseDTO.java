package com.chamo.chamosports.dto.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultSummaryResponseDTO {
    private Long teamId;
    private String teamName;
    private List<ResultListResponseDTO> resultList;
}
