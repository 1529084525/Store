package mapper.face;

import org.apache.ibatis.annotations.Mapper;
import pojo.Face;

import java.util.List;

@Mapper
public interface FaceMapper {
    //添加
    int insertFace(Face face);

    //删除根据id
    int deleteById(int faceId);

    //修改根据id
    int updateById(Face face);

    //查询所有
    List<Face> selectAll();

    /**
     * 根据手机号码查询对应的信息
     * @param phone
     * @return
     */
    Face selectFaceByPhone(String phone);

    /**
     * 根据手机号码删除信息
     * @param phone
     * @return
     */
    int deleteByPhone(String phone);
}
