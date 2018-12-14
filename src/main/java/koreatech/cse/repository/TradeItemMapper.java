package koreatech.cse.repository;


import koreatech.cse.domain.building.trade.TradeItem;
import koreatech.cse.domain.region.ReSearchable;
import koreatech.cse.domain.region.RegionCode;
import koreatech.cse.repository.provider.RegionSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeItemMapper {
    @Insert("INSERT INTO wsc.trade_items (deal_amount, building_area, building_use, build_year, classification, deal_year, plottage, dong, sigungu, land_use, deal_month, building_type, deal_day, regional_code, floor) " +
            "VALUES (#{dealAmount}, #{buildingArea}, #{buildingUse}, #{buildYear}, #{classification}, #{dealYear}, #{plottage}, #{dong}, #{sigungu}, #{landUse}, #{dealMonth}, #{buildingType}, #{dealDay}, #{regionalCode}, #{floor})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(TradeItem tradeItem);

    @Select("SELECT * FROM wsc.trade_items WHERE DEAL_YEAR = #{dealYear} AND DEAL_MONTH = #{dealMonth} AND REGIONAL_CODE = #{regionalCode}")
    List<TradeItem> getList(@Param("dealYear") int dealYear, @Param("dealMonth") int dealMonth, @Param("regionalCode") String regionalCode);

    @Select("SELECT * FROM wsc.trade_items WHERE ID = #{id}")
    TradeItem findOne(@Param("id") int id);

    @Select("SELECT * FROM wsc.trade_items WHERE DEAL_YEAR = #{dealYear} AND DEAL_MONTH = #{dealMonth} AND REGIONAL_CODE = #{regionalCode}")
    TradeItem findOneByCondition(@Param("dealYear") int dealYear, @Param("dealMonth") int dealMonth, @Param("regionalCode") String regionalCode);
}
