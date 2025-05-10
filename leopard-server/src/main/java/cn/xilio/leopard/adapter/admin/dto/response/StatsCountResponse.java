package cn.xilio.leopard.adapter.admin.dto.response;

public record StatsCountResponse(
        /**
         * 系统中总用户数量
         */
        long totalUser,
        /**
         * 今日注册用户总数
         */
        long todayRegisterUser,
        /**
         * 系统中总短链接数量
         */
        long totalUrls,
        /**
         * 在线用户总数
         */
        long onlineUser,
        /**
         * 过期短链接总数
         */
        long expiredUrls,
        /**
         * 注销用户总数
         */
        long bannedUser
) {
}
