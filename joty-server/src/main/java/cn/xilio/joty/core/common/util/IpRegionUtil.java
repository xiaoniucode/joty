//package cn.xilio.joty.core.common.util;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import lombok.extern.slf4j.Slf4j;
//import org.lionsoul.ip2region.xdb.Searcher;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
///**
// * IP 地址查询工具类，支持高并发和线程安全
// */
//@Slf4j
//@Component
//public class IpRegionUtil {
//
//    @Value("classpath:ip2region.xdb")
//    private Resource ip2RegionResource;
//
//    private byte[] vectorIndex;
//    private String dbPath;
//    private final ThreadLocal<Searcher> searcherThreadLocal = new ThreadLocal<>();
//
//    /**
//     * 初始化 VectorIndex 缓存
//     */
//    @PostConstruct
//    public void init() {
//        try {
//            // 加载 ip2region.xdb 文件
//            if (!ip2RegionResource.exists()) {
//                throw new IllegalStateException("ip2region.xdb file not found in classpath");
//            }
//
//            // 获取数据库文件路径
//            dbPath = ip2RegionResource.getFile().getAbsolutePath();
//
//            // 从文件中加载 VectorIndex 缓存
//            try (var inputStream = ip2RegionResource.getInputStream()) {
//                vectorIndex = Searcher.loadVectorIndex(inputStream);
//            }
//
//            log.info("IpRegionUtil initialized successfully with vectorIndex cache");
//        } catch (IOException e) {
//            log.error("Failed to initialize IpRegionUtil: {}", e.getMessage(), e);
//            throw new IllegalStateException("Failed to initialize IpRegionUtil", e);
//        }
//    }
//
//    /**
//     * 获取当前线程的 Searcher 实例
//     */
//    private Searcher getSearcher() throws IOException {
//        Searcher searcher = searcherThreadLocal.get();
//        if (searcher == null) {
//            try {
//                searcher = Searcher.newWithVectorIndex(dbPath, vectorIndex);
//                searcherThreadLocal.set(searcher);
//                log.debug("Created new Searcher instance for thread: {}", Thread.currentThread().getName());
//            } catch (Exception e) {
//                log.error("Failed to create Searcher: {}", e.getMessage(), e);
//                throw new IOException("Failed to create Searcher", e);
//            }
//        }
//        return searcher;
//    }
//
//    /**
//     * 根据 IP 查询地区信息
//     *
//     * @param ip IP 地址
//     * @return 地区信息，格式如：国家|区域|省份|城市|ISP
//     * @throws IllegalArgumentException 如果 IP 格式不合法
//     * @throws IOException 如果查询失败
//     */
//    public String getRegion(String ip) throws IOException {
//        if (ip == null || ip.trim().isEmpty()) {
//            throw new IllegalArgumentException("IP address cannot be null or empty");
//        }
//
//        Searcher searcher = getSearcher();
//        try {
//            long startTime = System.nanoTime();
//            String region = searcher.search(ip);
//            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
//            log.debug("IP: {}, Region: {}, IO Count: {}, Took: {} μs", ip, region, searcher.getIOCount(), cost);
//            return region;
//        } catch (Exception e) {
//            log.error("Failed to search region for IP {}: {}", ip, e.getMessage(), e);
//            throw new IOException("Failed to search region for IP: " + ip, e);
//        }
//    }
//
//    /**
//     * 清理当前线程的 Searcher 资源
//     */
//    private void closeSearcher() {
//        Searcher searcher = searcherThreadLocal.get();
//        if (searcher != null) {
//            try {
//                searcher.close();
//                log.debug("Closed Searcher for thread: {}", Thread.currentThread().getName());
//            } catch (IOException e) {
//                log.error("Failed to close Searcher: {}", e.getMessage(), e);
//            } finally {
//                searcherThreadLocal.remove();
//            }
//        }
//    }
//
//    /**
//     * 销毁全局资源
//     */
//    @PreDestroy
//    public void destroy() {
//        // 清理所有线程的 Searcher 实例
//        searcherThreadLocal.remove();
//        log.info("IpRegionUtil resources cleaned up successfully");
//    }
//}
