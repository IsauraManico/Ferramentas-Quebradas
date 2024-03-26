package com.ferramentas.ferramentasbackend.dto.input;

public class AllowClassificationDto {
    private Integer technicianAccount;
    private Integer userAccount;
    private Integer subService;

    public AllowClassificationDto(Integer technicianAccount, Integer userAccount, Integer subService) {
        this.technicianAccount = technicianAccount;
        this.userAccount = userAccount;
        this.subService = subService;
    }

    public Integer getTechnicianAccount() {
        return technicianAccount;
    }

    public void setTechnicianAccount(Integer technicianAccount) {
        this.technicianAccount = technicianAccount;
    }

    public Integer getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Integer userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getSubService() {
        return subService;
    }

    public void setSubService(Integer subService) {
        this.subService = subService;
    }
}
