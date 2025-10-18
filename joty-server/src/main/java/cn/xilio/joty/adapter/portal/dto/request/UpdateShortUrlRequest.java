package cn.xilio.joty.adapter.portal.dto.request;

public record UpdateShortUrlRequest (
        /** 短链接ID */
        String id,
        /** 标题 */
        String title,
        /** 状态 */
        Integer status,
        /** 备注 */
        String remark
){

}
