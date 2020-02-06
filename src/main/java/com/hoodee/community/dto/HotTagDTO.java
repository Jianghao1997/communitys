package com.hoodee.community.dto;

import lombok.Data;

/**
 * Package: com.hoodee.community.dto
 * Description：
 * Author: jianghao
 * Date:  2020.02.06 14:25
 * Modified By:
 */
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
