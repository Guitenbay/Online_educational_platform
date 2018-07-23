package dao.impl;

import dao.BarrageDao;
import dao.Dao;
import model.Barrage;

import java.util.List;

public class BarrageDaoImpl extends Dao<Barrage> implements BarrageDao {
    @Override
    public List<Barrage> getBarrageListByValuePath(String videoPath) {
        String sql = "SELECT id, context, section_id sectionId, show_time showTime " +
                "FROM barrages WHERE video_path=?";
        return getForList(sql, videoPath);
    }

    @Override
    public void save(Barrage barrage, String videoPath) {
        String sql = "INSERT INTO barrages (context, section_id, show_time, video_path) " +
                "VALUES (?, ?, ?, ?)";
        update(sql, barrage.getContext(), barrage.getSectionId(),
                barrage.getShowTime(), videoPath);
    }

    @Override
    public void deleteBarrageById(int id) {
        String sql = "DELETE FROM barrages WHERE id=?";
        update(sql, id);
    }
}
