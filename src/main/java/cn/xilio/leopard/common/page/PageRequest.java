package cn.xilio.leopard.common.page;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private int page = 1;
    private int size = 10;

    public PageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size);
    }

}
