package koreatech.cse.repository.provider;

import koreatech.cse.domain.ReSearchable;
import koreatech.cse.domain.Searchable;
import org.apache.ibatis.jdbc.SQL;

public class RegionSqlProvider {

    public String findAllByProvider(final ReSearchable searchable) {
        return new SQL() {
            {
                SELECT("*");
                FROM("REGION");
                if(searchable.getState()!= null) {
                    WHERE("STATE = #{state}");
                    if(searchable.getCity() != null) {
                        OR();
                        WHERE("CITY = #{city}");
                        if(searchable.getSub1() != null) {
                            OR();
                            WHERE("SUB1 = #{sub1}");
                            if (searchable.getSub2() != null) {
                                OR();
                                WHERE("SUB2 = #{sub2}");
                            }
                        }
                    }
                }
                if(searchable.getOrderParam() != null) {

                    ORDER_BY(searchable.getOrderParam() + " DESC");
                }
            }
        }.toString();
    }
}
