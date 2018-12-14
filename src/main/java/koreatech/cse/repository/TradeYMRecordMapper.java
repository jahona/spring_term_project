package koreatech.cse.repository;


import koreatech.cse.domain.building.trade.TradeYMRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeYMRecordMapper {
    @Insert("INSERT INTO wsc.trade_ym_records (minimum_deal, maximum_deal, average_deal, minimum_trade_id, maximum_trade_id, regional_code, deal_ym, trade_count) " +
            "VALUES (#{minimumDeal}, #{maximumDeal}, #{averageDeal}, #{minimumTradeId}, #{maximumTradeId}, #{regionalCode}, #{dealYM}, #{tradeCount})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(TradeYMRecord tradeYMRecord);

    @Select("SELECT * FROM wsc.trade_ym_records WHERE DEAL_YM = #{dealYM} AND REGIONAL_CODE = #{regionalCode}")
    TradeYMRecord findOne(@Param("dealYM") String dealYM, @Param("regionalCode") String regionalCode);

    @Select("SELECT * FROM wsc.trade_ym_records WHERE DEAL_YM <= #{dealYM} AND REGIONAL_CODE = #{regionalCode} ORDER BY DEAL_YM DESC limit ${count}")
    List<TradeYMRecord> findListByTerm(@Param("dealYM") String dealYM, @Param("regionalCode") String regionalCode, @Param("count") int count);
}
