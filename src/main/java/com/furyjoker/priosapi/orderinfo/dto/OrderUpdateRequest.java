package com.furyjoker.priosapi.orderinfo.dto;

import java.util.List;

public record OrderUpdateRequest(List<Long> orderedTargetIds) {}
