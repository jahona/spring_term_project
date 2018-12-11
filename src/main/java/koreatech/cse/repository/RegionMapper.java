package koreatech.cse.repository;


import koreatech.cse.domain.region.ReSearchable;
import koreatech.cse.domain.region.RegionCode;
import koreatech.cse.repository.provider.RegionSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionMapper {
    @Insert("INSERT INTO WSC.REGION (CODE, STATE, CITY, SUB1, SUB2, CREATED_AT) VALUES (#{code},#{state}, #{city}, #{sub1}, #{sub2}, #{created_at})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(RegionCode regionCode);

    @Update("UPDATE WSC.REGION SET STATE = #{state}, CITY = #{city}, SUB1 = #{sub1}, SUB2 = #{sub2}, CREATED_AT = #{created_at} WHERE CODE = #{code}")
    void update(RegionCode regionCode);

    @Select("SELECT * FROM WSC.REGION WHERE code = #{code}")
    RegionCode findOne(@Param("code") String code);

    @Delete("DELETE FROM WSC.REGION WHERE CODE = #{code}")
    void delete(@Param("code") String code);

    @SelectProvider(type = RegionSqlProvider.class, method = "findAllByProvider")
    List<RegionCode> findByProvider(ReSearchable searchable);

    //@formatter off
    @Select("<script>"
            + "SELECT * FROM WSC.REGION"
            + "<if test='state != null'> WHERE STATE = #{state}</if>"
            + "<if test='state != null and city != null'> OR CITY = #{city}</if>"
            + "<if test='state != null and city != null and sub1 != null'> OR SUB1 = #{sub1}</if>"
            + "<if test='state != null and city != null and sub1 != null and sub2 != null'> OR SUB2 = #{sub2}</if>"
            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
            + "</script>")
    //@formatter on
    List<RegionCode> findByScript(ReSearchable searchable);

}
