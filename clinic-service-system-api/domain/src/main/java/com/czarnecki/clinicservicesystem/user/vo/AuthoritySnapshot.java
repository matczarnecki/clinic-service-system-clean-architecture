package com.czarnecki.clinicservicesystem.user.vo;

import com.czarnecki.clinicservicesystem.vo.EntitySnapshot;

public record AuthoritySnapshot(String code,
                                String name,
                                String description) implements EntitySnapshot<String> {
    @Override
    public String id() {
        return code;
    }
}
