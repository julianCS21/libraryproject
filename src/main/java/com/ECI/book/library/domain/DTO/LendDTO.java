package com.ECI.book.library.domain.DTO;

import lombok.Getter;


@Getter
public class LendDTO {
    private String userId;
    private String bookId;

    public LendDTO(String bookId1, String userId1) {
    }
}
