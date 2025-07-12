package cn.xilio.joty.core.common.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class RegionService {
    @Value("classpath:ip2region.xdb")
    private Resource xdb;
    private String dbPath;
    private final ThreadLocal<Searcher> searcherThreadLocal = new ThreadLocal<>();

    @PostConstruct
    public void init() {
        try {
            if (!xdb.exists()) {
                throw new IllegalStateException("ip2region.xdb file not found in classpath");
            }
            dbPath = xdb.getFile().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前线程的 Searcher 实例
     */
    private Searcher getSearcher() throws IOException {
        Searcher searcher = searcherThreadLocal.get();
        if (searcher == null) {
            try {
                searcher = Searcher.newWithFileOnly(dbPath);
                searcherThreadLocal.set(searcher);
            } catch (IOException e) {
                throw new IOException("Failed to create Searcher", e);
            }
        }
        return searcher;
    }

    public String getRegion(String ip) {
        if (!StringUtils.hasText(ip)) {
            throw new IllegalArgumentException("IP address cannot be empty");
        }
        try {
            Searcher searcher = getSearcher();
            return searcher.search(ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 清理当前线程的 Searcher 资源
     */
    public void close() {
        Searcher searcher = searcherThreadLocal.get();
        if (searcher != null) {
            try {
                searcher.close();
            } catch (IOException ignored) {
            } finally {
                searcherThreadLocal.remove();
            }
        }
    }

    /**
     * 销毁全局资源
     */
    @PreDestroy
    public void destroy() {
        // 清理所有线程的 Searcher 实例
        searcherThreadLocal.remove();
    }
}
