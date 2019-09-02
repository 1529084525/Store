package service.face;

import interfaces.face.FaceInterface;
import mapper.face.FaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Face;

import java.util.List;

@Component
public class FaceService implements FaceInterface {
    @Autowired
    private FaceMapper faceMapper;

    @Override
    public int insertFace(Face face) {
        return faceMapper.insertFace(face);
    }

    @Override
    public int deleteById(int faceId) {
        return faceMapper.deleteById(faceId);
    }

    @Override
    public int updateById(Face face) {
        return faceMapper.updateById(face);
    }

    @Override
    public List<Face> selectAll() {
        return faceMapper.selectAll();
    }

    @Override
    public Face selectFaceByPhone(String phone) {
        return faceMapper.selectFaceByPhone(phone);
    }

    @Override
    public int deleteByPhone(String phone) {
        return faceMapper.deleteByPhone(phone);
    }
}
