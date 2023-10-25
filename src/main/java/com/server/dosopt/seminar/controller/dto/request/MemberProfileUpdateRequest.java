package com.server.dosopt.seminar.controller.dto.request;

import com.server.dosopt.seminar.domain.Part;
import lombok.Data;

@Data
public class MemberProfileUpdateRequest {
    private int generation;
    private Part part;
}
