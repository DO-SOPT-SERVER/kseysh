package com.server.dosopt.seminar.controller.dto.request;

import com.server.dosopt.seminar.domain.Part;

//@Data
//public class MemberProfileUpdateRequest {
//    private int generation;
//    private Part part;
//}

public record MemberProfileUpdateRequest(
        int generation,
        Part part

){}