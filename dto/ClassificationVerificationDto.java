package com.ferramentas.ferramentasbackend.dto;

public class ClassificationVerificationDto {
    private Integer account;
    private Integer subService;

    public ClassificationVerificationDto(Integer account, Integer subService) {
        this.account = account;
        this.subService = subService;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getSubService() {
        return subService;
    }

    public void setSubService(Integer subService) {
        this.subService = subService;
    }
}
