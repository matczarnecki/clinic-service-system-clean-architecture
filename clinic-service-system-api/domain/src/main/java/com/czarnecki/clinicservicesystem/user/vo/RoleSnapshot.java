package com.czarnecki.clinicservicesystem.user.vo;

import com.czarnecki.clinicservicesystem.vo.EntitySnapshot;
import java.util.Set;

public record RoleSnapshot(String code,
                           String name,
                           Set<AuthoritySnapshot> authorities) implements EntitySnapshot<String> {
    @Override public String id() {
        return code;
    }
}
