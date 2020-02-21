package com.tb.model;

import javax.persistence.*;

import com.tb.dto.CafeOfferDto;
import com.tb.model.api.AbstractBaseEntity;
import com.tb.model.api.MergeInstance;
import com.tb.model.api.PatchInstance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cafe_services", schema = "PUBLIC")
@Cacheable
@EqualsAndHashCode(callSuper = true)
public class CafeOffer extends AbstractBaseEntity<Long> implements MergeInstance<CafeOffer, CafeOfferDto>, PatchInstance<CafeOffer, CafeOfferDto>{

    private static final long serialVersionUID = 4405998570266266751L;
    @Id
    @Column(name = "cafe_service_id", unique = true, nullable = false, updatable = false)
    @SequenceGenerator(name = "cafe_service_id_seq", sequenceName = "cafe_service_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "cafe_service_id_seq")
    @Getter
    @Setter
    private Long id;
    @Column(name = "service_name", nullable = false)
    @Getter
    @Setter
    private String serviceName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    @Getter
    @Setter
    private Cafe cafe;
    @Column(name = "cost")
    @Getter
    @Setter
    private Integer cost;
    @Override
    public CafeOffer patch(CafeOfferDto dto) {
        if (dto.getServiceName() != null) {
            this.setServiceName(dto.getServiceName());
        }
        if (dto.getCost() != null) {
            this.setCost(dto.getCost());
        }
        return this;
    }
    @Override
    public CafeOffer merge(CafeOfferDto dto) {
        this.setServiceName(dto.getServiceName());
        this.setCost(dto.getCost());
        return this;
    }
}
