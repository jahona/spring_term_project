package koreatech.cse.repository;


import koreatech.cse.domain.region.ReSearchable;
import koreatech.cse.domain.region.RegionCode;
import koreatech.cse.repository.provider.RegionSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeMapper {
    @Insert("INSERT INTO wsc.trade (CODE, STATE, CITY, SUB1, SUB2, CREATED_AT) VALUES (#{code},#{state}, #{city}, #{sub1}, #{sub2}, #{created_at})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(RegionCode regionCode);
}
