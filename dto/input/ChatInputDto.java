package com.ferramentas.ferramentasbackend.dto.input;

public class ChatInputDto {
    private final Integer user1;
    private final Integer user2;
    private final Integer actualUserId;

    public ChatInputDto(Integer user1, Integer user2, Integer actualUserId) {
        this.user1 = user1;
        this.user2 = user2;
        this.actualUserId = actualUserId;
    }

    public Integer getUser1() {
        return user1;
    }

    public Integer getUser2() {
        return user2;
    }

    public Integer getActualUserId() {
        return actualUserId;
    }
}
