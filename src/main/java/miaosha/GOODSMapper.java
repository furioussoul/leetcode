package miaosha;

import java.util.List;
import miaosha.GoodsDO;
import miaosha.GoodsDOExample;
import org.apache.ibatis.annotations.Param;

public interface GOODSMapper {

    int decCount(@Param("goodId") int goodId);

    long countByExample(GoodsDOExample example);

    int deleteByExample(GoodsDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    List<GoodsDO> selectByExample(GoodsDOExample example);

    GoodsDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsDO record, @Param("example") GoodsDOExample example);

    int updateByExample(@Param("record") GoodsDO record, @Param("example") GoodsDOExample example);

    int updateByPrimaryKeySelective(GoodsDO record);

    int updateByPrimaryKey(GoodsDO record);
}