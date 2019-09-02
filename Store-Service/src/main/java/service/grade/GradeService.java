package service.grade;

import interfaces.grade.GradeInterface;
import mapper.grade.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Grade;

import java.util.List;

@Component
public class GradeService implements GradeInterface {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public int insertGrade(Grade grade) {
        return gradeMapper.insertGrade(grade);
    }

    @Override
    public int deleteById(int gradeId) {
        return gradeMapper.deleteById(gradeId);
    }

    @Override
    public int updateById(Grade grade) {
        return gradeMapper.updateById(grade);
    }

    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }

    @Override
    public Grade selectGradeById(int id) {
        return gradeMapper.selectGradeById(id);
    }
}
