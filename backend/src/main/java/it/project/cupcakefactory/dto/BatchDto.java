package it.project.cupcakefactory.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import it.project.cupcakefactory.entity.Batch;

public class BatchDto {

    private Batch batch;
    private double priceModifier;
    private boolean expired;

    public BatchDto(Batch batch, double priceModifier, boolean expired) {
        this.batch = batch;
        this.priceModifier = priceModifier;
        this.expired = expired;
    }

    public double getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(double priceModifier) {
        this.priceModifier = priceModifier;
    }

    @JsonUnwrapped
    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
