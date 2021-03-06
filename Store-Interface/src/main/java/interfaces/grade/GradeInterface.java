package interfaces.grade;

import pojo.Grade;

import java.util.List;

public interface GradeInterface {
    //添加
    int insertGrade(Grade grade);

    //删除根据id
    int deleteById(int gradeId);

    //修改根据id
    int updateById(Grade grade);

    //查询所有
    List<Grade> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Grade selectGradeById(int id);
}
