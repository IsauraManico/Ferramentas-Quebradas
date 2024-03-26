package com.ferramentas.ferramentasbackend.dto.output;

public class ClassificationSubServiceDto {
    private AccountSimplePresentationDto account;
    private SubServiceSimplePresentationDto subService;
    private Boolean canClassify;
    private Boolean technicianAction;

    public ClassificationSubServiceDto(AccountSimplePresentationDto account, SubServiceSimplePresentationDto subService, Boolean canClassify, Boolean technicianAction) {
        this.account = account;
        this.subService = subService;
        this.canClassify = canClassify;
        this.technicianAction = technicianAction;
    }

    public AccountSimplePresentationDto getAccount() {
        return account;
    }

    public void setAccount(AccountSimplePresentationDto account) {
        this.account = account;
    }

    public SubServiceSimplePresentationDto getSubService() {
        return subService;
    }

    public void setSubService(SubServiceSimplePresentationDto subService) {
        this.subService = subService;
    }

    public Boolean getCanClassify() {
        return canClassify;
    }

    public void setCanClassify(Boolean canClassify) {
        this.canClassify = canClassify;
    }

    public Boolean getTechnicianAction() {
        return technicianAction;
    }

    public void setTechnicianAction(Boolean technicianAction) {
        this.technicianAction = technicianAction;
    }
}
