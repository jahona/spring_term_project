package koreatech.cse.repository;


import koreatech.cse.domain.region.ReSearchable;
import koreatech.cse.domain.region.RegionCode;
import koreatech.cse.repository.provider.RegionSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionMapper {
    @Insert("INSERT INTO wsc.region (CODE, STATE, CITY, SUB1, SUB2, CREATED_AT) VALUES (#{code},#{state}, #{city}, #{sub1}, #{sub2}, #{created_at})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(RegionCode regionCode);

    @Update("UPDATE wsc.region SET STATE = #{state}, CITY = #{city}, SUB1 = #{sub1}, SUB2 = #{sub2}, CREATED_AT = #{created_at} WHERE CODE = #{code}")
    void update(RegionCode regionCode);

    @Select("SELECT * FROM wsc.region WHERE code = #{code}")
    RegionCode findOne(@Param("code") String code);

    @Delete("DELETE FROM wsc.region WHERE CODE = #{code}")
    void delete(@Param("code") String code);

    @SelectProvider(type = RegionSqlProvider.class, method = "findAllByProvider")
    List<RegionCode> findByProvider(ReSearchable searchable);

    //@formatter off
    @Select("<script>"
            + "SELECT * FROM wsc.region"
            + "<if test='state != null'> WHERE STATE = #{state}</if>"
            + "<if test='state != null and city != null'> AND CITY = #{city}</if>"
            + "<if test='state != null and city != null and sub1 != null'> AND SUB1 = #{sub1}</if>"
            + "<if test='state != null and city != null and sub1 != null and sub2 != null'> AND SUB2 = #{sub2}</if>"
            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
            + "</script>")
    //@formatter on
    List<RegionCode> findByScript(ReSearchable searchable);

}
