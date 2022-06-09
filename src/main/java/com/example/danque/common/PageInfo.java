package com.example.danque.common;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PageInfo<T> {

    private Integer pageSize;

    private Integer pageIndex;

    private Long dataCount;
}
