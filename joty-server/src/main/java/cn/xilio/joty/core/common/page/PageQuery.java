package cn.xilio.joty.core.common.page;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private int page = 1;
    private int size = 10;

    public PageQuery(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public static PageQuery of(int page, int size) {
        return new PageQuery(page, size);
    }

}
