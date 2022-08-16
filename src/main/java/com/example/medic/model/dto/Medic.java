package com.example.medic.model.dto;

public class Medic {
    private Long phaNo;
    private String phaName;
    private String phaType;
    private Long phaPrice;
    private Long phaStock;

    public Medic(Long phaNo, String phaName, String phaType, Long phaPrice, Long phaStock) {
        this.phaNo = phaNo;
        this.phaName = phaName;
        this.phaType = phaType;
        this.phaPrice = phaPrice;
        this.phaStock = phaStock;
    }

    @Override
    public String toString() {
        return "Medic{" +
                "phaNo=" + phaNo +
                ", phaName='" + phaName + '\'' +
                ", phaType='" + phaType + '\'' +
                ", phaPrice=" + phaPrice +
                ", phaStock=" + phaStock +
                '}';
    }

    public Long getPhaNo() {
        return phaNo;
    }

    public void setPhaNo(Long phaNo) {
        this.phaNo = phaNo;
    }

    public String getPhaName() {
        return phaName;
    }

    public void setPhaName(String phaName) {
        this.phaName = phaName;
    }

    public String getPhaType() {
        return phaType;
    }

    public void setPhaType(String phaType) {
        this.phaType = phaType;
    }

    public Long getPhaPrice() {
        return phaPrice;
    }

    public void setPhaPrice(Long phaPrice) {
        this.phaPrice = phaPrice;
    }

    public Long getPhaStock() {
        return phaStock;
    }

    public void setPhaStock(Long phaStock) {
        this.phaStock = phaStock;
    }
}
