package dao;

import model.Section;

import java.util.List;

public interface SectionDao {

    List<Section> getSectionListByChapterId(int chapterId);

    Section getSectionById(int id);

    void save(Section section);

    void update(Section section);

    void deleteSectionById(int id);

}
