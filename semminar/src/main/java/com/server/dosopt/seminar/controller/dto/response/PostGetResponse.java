package com.server.dosopt.seminar.controller.dto.response;

import com.server.dosopt.seminar.domain.Post;

public record PostGetResponse(
        Long id,
        String title,
        String content
//        ,CategoryResponse category
) {
    public static PostGetResponse of(Post post){
        return new PostGetResponse(
                post.getId(),
                post.getTitle(),
                post.getContent()
//                ,CategoryResponse.of(category)
        );
    }
}
