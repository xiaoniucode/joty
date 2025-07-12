package cn.xilio.joty.repository.dao;

import cn.xilio.joty.domain.dataobject.AccessRecord;
import cn.xilio.joty.domain.model.DailyStatsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccessRecordEntityRepository extends JpaRepository<AccessRecord, String>, JpaSpecificationExecutor<AccessRecord> {
    // 查询总访问次数和总访问人数
    @Query(nativeQuery = true, value = """
            SELECT 
                short_code,
                COUNT(id) AS total_visits,
                COUNT(DISTINCT ip_address) AS total_visitors
            FROM access_record
            WHERE short_code IN :shortCodes
            GROUP BY short_code
            """)
    List<Object[]> findTotalStatsByShortCodes(@Param("shortCodes") List<String> shortCodes);

    // 查询今日访问次数和今日访问人数
    @Query(nativeQuery = true, value = """
            SELECT 
                short_code,
                COUNT(id) AS today_visits,
                COUNT(DISTINCT ip_address) AS today_visitors
            FROM access_record
            WHERE short_code IN :shortCodes
            AND DATE(access_time) = :today
            GROUP BY short_code
            """)
    List<Object[]> findTodayStatsByShortCodes(@Param("shortCodes") List<String> shortCodes, @Param("today") LocalDate today);

    boolean existsByIpAddressAndUserAgentAndShortCode(String shortCode, String ipAddress, String userAgent);

    @Query(nativeQuery = true, value = """
    WITH RECURSIVE date_range AS (
        SELECT CAST(:startDate AS DATE) AS date
        UNION ALL
        SELECT DATE_ADD(date, INTERVAL 1 DAY)
        FROM date_range
        WHERE date < :endDate
    )
    SELECT 
        dr.date,
        COUNT(DISTINCT ar.ip_address) AS unique_visitors,
        COUNT(ar.id) AS total_visits
    FROM date_range dr
    LEFT JOIN access_record ar ON 
        DATE(ar.access_time) = dr.date AND
        ar.short_code = :shortCode
    GROUP BY dr.date
    ORDER BY dr.date""")
    List<Object[]> getRawDailyStats(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("shortCode") String shortCode);


}
